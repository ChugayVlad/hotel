package com.my.command.admin;

import com.my.command.Command;
import com.my.entity.Order;
import com.my.entity.Role;
import com.my.exception.AppException;
import com.my.service.OrderService;
import com.my.service.impl.OrderServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetRoomForUserCommand implements Command {
    private static final Logger LOG = Logger.getLogger(SetRoomForUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        if (!Role.ADMIN.equals(session.getAttribute("userRole"))){
            throw new AppException("You dont have permissions!!!");
        }

        OrderService orderService = new OrderServiceImpl();/*
        Order order = orderService.getById(Long.valueOf(request.getParameter("orderId")));
        order.setRoomId(Long.valueOf(request.getParameter("roomId")));*/

        orderService.setRoom(Long.valueOf(request.getParameter("orderId")), Long.valueOf(request.getParameter("roomId")));

        request.setAttribute("message", "Request sent to user");

        LOG.debug("Command finished");
        return Path.COMMAND_SHOW_ORDERS;
    }
}
