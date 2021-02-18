package com.my.command;

import com.my.entity.Bill;
import com.my.entity.BillStatus;
import com.my.exception.AppException;
import com.my.service.BillService;
import com.my.service.impl.BillServiceImpl;
import com.my.util.Path;
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
        String money = request.getParameter("money");
        String sum = request.getParameter("sum");
        if (Double.parseDouble(sum) != Double.parseDouble(money)){
            throw new AppException("The entered amount does not match the specified.");
        }

        BillService billService = new BillServiceImpl();
        Bill bill = billService.getBillById(Long.parseLong(request.getParameter("billId")));
        bill.setStatus(BillStatus.PAID);
        billService.updateStatus(bill);

        LOG.debug("Command finished");
        return (String) session.getAttribute("prevPath");
    }
}
