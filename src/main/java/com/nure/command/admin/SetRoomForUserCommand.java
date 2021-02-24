package com.nure.command.admin;

import com.nure.command.Command;
import com.nure.exception.AppException;
import com.nure.service.OrderService;
import com.nure.service.impl.OrderServiceImpl;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Set available room command.
 *
 */
public class SetRoomForUserCommand implements Command {
    private static final Logger LOG = Logger.getLogger(SetRoomForUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        OrderService orderService = new OrderServiceImpl();
        orderService.setRoom(Long.valueOf(request.getParameter("orderId")), Long.valueOf(request.getParameter("roomId")));

        request.setAttribute("message", "Request sent to user");

        LOG.debug("Command finished");
        return Path.COMMAND_SHOW_ORDERS;
    }
}
