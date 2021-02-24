package com.nure.command.admin;

import com.nure.command.Command;
import com.nure.entity.Order;
import com.nure.entity.Room;
import com.nure.exception.AppException;
import com.nure.service.OrderService;
import com.nure.service.RoomService;
import com.nure.service.impl.OrderServiceImpl;
import com.nure.service.impl.RoomServiceImpl;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Find rooms with order parameters.
 *
 */
public class FindRoomsCommand implements Command {
    private static final Logger LOG = Logger.getLogger(FindRoomsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();

        Long orderId = Long.parseLong(request.getParameter("orderId"));

        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.getById(orderId);

        RoomService roomService = new RoomServiceImpl();
        List<Room> rooms = roomService.getAllRoomsByParameters(order.getPlaces(), order.getType().name(), order.getDateIn(), order.getDateOut());
        LOG.trace("Rooms --> " + rooms);

        request.setAttribute("rooms", rooms);
        request.setAttribute("orderId", orderId);

        LOG.debug("Command finished");
        return Path.PAGE_AVAILABLE_ROOMS;
    }
}
