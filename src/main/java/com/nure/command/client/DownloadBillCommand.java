package com.nure.command.client;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nure.command.Command;
import com.nure.entity.Bill;
import com.nure.exception.AppException;
import com.nure.service.BillService;
import com.nure.service.impl.BillServiceImpl;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;
/**
 * Download bill command.
 *
 */
public class DownloadBillCommand implements Command {
    private static final Logger LOG = Logger.getLogger(DownloadBillCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        Long billId = Long.parseLong(request.getParameter("billId"));
        BillService billService = new BillServiceImpl();
        Bill bill = billService.getBillById(billId);
        Document document = new Document();
        String filePath = request.getServletContext().getRealPath("/" + bill.hashCode() + ".pdf");
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            LOG.trace("Created file");
            document.open();
            PdfPTable table = new PdfPTable(4);
            Stream.of("Date in", "Date out", "Sum to pay", "Email")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        table.addCell(header);
                    });
            table.addCell(bill.getDateIn().toString());
            table.addCell(bill.getDateOut().toString());
            table.addCell(bill.getSum().toString());
            table.addCell(bill.getUser().getEmail());
            document.add(table);
        } catch (DocumentException | FileNotFoundException e) {
            LOG.error("Can not create file", e);
            throw new AppException("Can not create file", e);
        }
        document.close();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=bill.pdf");
        ServletOutputStream out = null;
        File file = new File(filePath);
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] outputByte = new byte[4096];
            while (fileIn.read(outputByte, 0, 4096) != -1) {
                out.write(outputByte, 0, 4096);
            }
            fileIn.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            LOG.error("Can not download file", e);
            throw new AppException("Can not download file", e);
        }
        file.delete();
        LOG.debug("Command finished");
        return Path.COMMAND_OPEN_PERSONAL_ACCOUNT;
    }
}
