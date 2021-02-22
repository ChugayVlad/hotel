package com.nure.command;

import com.nure.entity.Order;
import com.nure.entity.RoomType;
import com.nure.entity.User;
import com.nure.exception.AppException;
import com.nure.exception.ValidationException;
import com.nure.service.OrderService;
import com.nure.service.RoomTypeService;
import com.nure.service.impl.OrderServiceImpl;
import com.nure.service.impl.RoomTypeServiceImpl;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;


public class CreateOrderCommand implements Command {
    private static final Logger LOG = Logger.getLogger(CreateOrderCommand.class);
    private static final String REQUEST_MESSAGE = "&message=";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        String currentLocale = (String) session.getAttribute("currentLocale");

        OrderService orderService;
        Order order = new Order();
        order.setPlaces(Integer.parseInt(request.getParameter("places")));
        LocalDate dateIn = LocalDate.parse(request.getParameter("dateIn"));
        LocalDate dateOut = LocalDate.parse(request.getParameter("dateOut"));

        order.setDateIn(Date.valueOf(dateIn));
        order.setDateOut(Date.valueOf(dateOut));

        RoomTypeService typeService = new RoomTypeServiceImpl();
        RoomType type = typeService.getById(Integer.parseInt(request.getParameter("roomType")));

        order.setType(type);

        User user = (User) session.getAttribute("user");
        order.setUser(user);
        orderService = new OrderServiceImpl();

        String message = REQUEST_MESSAGE;
        try {
            orderService.insertOrder(order);
            message += "message.success_order";
        } catch (ValidationException e) {
            message += e.getMessage();
        }
        return Path.COMMAND_SHOW_ROOMS + message;
    }
}
