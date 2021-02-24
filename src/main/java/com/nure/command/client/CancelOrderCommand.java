package com.nure.command.client;

import com.nure.command.Command;
import com.nure.exception.AppException;
import com.nure.service.OrderService;
import com.nure.service.impl.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Cancel order command.
 *
 */
public class CancelOrderCommand implements Command {
    private static final Logger LOG = Logger.getLogger(CancelOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        OrderService orderService = new OrderServiceImpl();
        orderService.delete(Long.parseLong(request.getParameter("orderId")));
        LOG.debug("Command finished");
        return (String) session.getAttribute("prevPath");
    }
}
