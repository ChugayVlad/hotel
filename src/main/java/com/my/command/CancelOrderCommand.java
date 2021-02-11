package com.my.command;

import com.my.exception.AppException;
import com.my.service.OrderService;
import com.my.service.impl.OrderServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelOrderCommand implements Command{
    private static final Logger LOG = Logger.getLogger(CancelOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        OrderService orderService = new OrderServiceImpl();
        orderService.delete(Long.parseLong(request.getParameter("orderId")));
        LOG.debug("Command finished");
        return Path.COMMAND_OPEN_PERSONAL_ACCOUNT;
    }
}
