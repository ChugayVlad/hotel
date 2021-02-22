package com.nure.command;

import com.nure.entity.Bill;
import com.nure.entity.BillStatus;
import com.nure.exception.AppException;
import com.nure.service.BillService;
import com.nure.service.impl.BillServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PayCommand implements Command{
    private static final Logger LOG = Logger.getLogger(PayCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        BillService billService = new BillServiceImpl();
        Bill bill = billService.getBillById(Long.parseLong(request.getParameter("billId")));
        bill.setStatus(BillStatus.PAID);
        billService.updateStatus(bill);

        LOG.debug("Command finished");
        return (String) session.getAttribute("prevPath");
    }
}
