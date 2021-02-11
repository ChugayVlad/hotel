package com.my.command.admin;

import com.my.command.Command;
import com.my.entity.Order;
import com.my.entity.Role;
import com.my.entity.Room;
import com.my.exception.AppException;
import com.my.service.OrderService;
import com.my.service.RoomService;
import com.my.service.impl.OrderServiceImpl;
import com.my.service.impl.RoomServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindRoomsCommand implements Command {
    private static final Logger LOG = Logger.getLogger(FindRoomsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();

        if (!Role.ADMIN.equals(session.getAttribute("userRole"))){
            throw new AppException("You dont have permissions!!!");
        }

        Long orderId = Long.parseLong(request.getParameter("orderId"));

        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.getById(orderId);

        RoomService roomService = new RoomServiceImpl();
        List<Room> rooms = roomService.getAllRoomsByParameters(order.getPlaces(), order.getType().getId(), order.getDateIn(), order.getDateOut());
        LOG.trace("Rooms --> " + rooms);

        request.setAttribute("rooms", rooms);
        request.setAttribute("orderId", orderId);

        LOG.debug("Command finished");
        return Path.PAGE_AVAILABLE_ROOMS;
    }
}
