package com.nure.command.client;

import com.nure.command.Command;
import com.nure.command.util.PathUtil;
import com.nure.entity.Bill;
import com.nure.entity.Order;
import com.nure.entity.User;
import com.nure.exception.AppException;
import com.nure.service.BillService;
import com.nure.service.OrderService;
import com.nure.service.impl.BillServiceImpl;
import com.nure.service.impl.OrderServiceImpl;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Open personal account command.
 *
 */
public class OpenPersonalAccountCommand implements Command {
    private static final Logger LOG = Logger.getLogger(OpenPersonalAccountCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        PathUtil.saveCurrentPathToSession(request);
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
