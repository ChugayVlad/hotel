package com.nure.command;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nure.entity.Bill;
import com.nure.exception.AppException;
import com.nure.service.BillService;
import com.nure.service.impl.BillServiceImpl;
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

public class DownloadBillCommand implements Command {
    private static final Logger LOG = Logger.getLogger(DownloadBillCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        Long billId = Long.parseLong(request.getParameter("billId"));
        BillService billService = new BillServiceImpl();
        Bill bill = billService.getBillById(billId);

        Document document = new Document();
        String filePath = request.getServletContext().getRealPath("/" + bill.hashCode() + ".pdf");

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            LOG.trace("Created file");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
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

        try {
            document.add(table);
            LOG.trace("Add table");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=bill.pdf");

        File file = new File(filePath);
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] outputByte = new byte[4096];

        while(true)
        {
            try {
                if (fileIn.read(outputByte, 0, 4096) == -1) break;

                out.write(outputByte, 0, 4096);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileIn.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
