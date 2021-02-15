package com.my.command;

import com.my.entity.Bill;
import com.my.entity.Order;
import com.my.entity.User;
import com.my.exception.AppException;
import com.my.service.BillService;
import com.my.service.OrderService;
import com.my.service.impl.BillServiceImpl;
import com.my.service.impl.OrderServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OpenPersonalAccountCommand implements Command {
    private static final Logger LOG = Logger.getLogger(OpenPersonalAccountCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String to = request.getParameter("to");

        if ("bills".equals(to)) {
            BillService billService = new BillServiceImpl();
            List<Bill> bills = billService.getAllBills(user.getId());
            LOG.trace("Bills -->> " + bills);
            request.setAttribute("bills", bills);
        }

        if ("orders".equals(to)) {
            OrderService orderService = new OrderServiceImpl();
            List<Order> orders = orderService.getAllOrdersByUser(user.getId());
            request.setAttribute("orders", orders);
        }

        if (to != null){
            request.setAttribute("to", to);
        }

        LOG.debug("Command finished");
        return Path.PAGE_PERSONAL_ACCOUNT;
    }
}
