package com.my.command;

import com.my.entity.Order;
import com.my.entity.RoomType;
import com.my.entity.User;
import com.my.exception.AppException;
import com.my.service.OrderService;
import com.my.service.RoomTypeService;
import com.my.service.impl.OrderServiceImpl;
import com.my.service.impl.RoomTypeServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;


public class CreateOrderCommand implements Command {
    private static final Logger LOG = Logger.getLogger(CreateOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null){
            return Path.COMMAND_LOGIN;
        }

        OrderService orderService;
        Order order = new Order();
        order.setPlaces(Integer.parseInt(request.getParameter("places")));
        LocalDate dateIn = LocalDate.parse(request.getParameter("dateIn"));
        LocalDate dateOut = LocalDate.parse(request.getParameter("dateOut"));


        LOG.trace(dateIn);
        if (dateIn.compareTo(dateOut) > 0) {
            throw new AppException("Arrival date cannot be less than departure date");
        }

        order.setDateIn(Date.valueOf(dateIn));
        order.setDateOut(Date.valueOf(dateOut));

        RoomTypeService typeService = new RoomTypeServiceImpl();
        RoomType type = typeService.getById(Integer.parseInt(request.getParameter("roomType")));

        order.setType(type);

        User user = (User) session.getAttribute("user");
        order.setUser(user);
        orderService = new OrderServiceImpl();
        orderService.insertOrder(order);

        return Path.COMMAND_OPEN_SUCCESS;
    }
}
