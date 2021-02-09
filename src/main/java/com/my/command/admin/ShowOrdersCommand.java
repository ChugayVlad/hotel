package com.my.command.admin;

import com.my.command.Command;
import com.my.entity.Order;
import com.my.entity.Role;
import com.my.entity.Room;
import com.my.entity.RoomType;
import com.my.exception.AppException;
import com.my.service.OrderService;
import com.my.service.RoomService;
import com.my.service.RoomTypeService;
import com.my.service.impl.OrderServiceImpl;
import com.my.service.impl.RoomServiceImpl;
import com.my.service.impl.RoomTypeServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowOrdersCommand implements Command {
    private static final Logger LOG = Logger.getLogger(ShowOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();

        if (!Role.ADMIN.equals(session.getAttribute("userRole"))){
            throw new AppException("You dont have permissions!!!");
        }

        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.getAllOrders();
        LOG.trace("Orders --> " + orders);

        request.setAttribute("orders", orders);

        LOG.debug("Command finished");
        return Path.PAGE_ORDERS;
    }
}
