package com.my.command;

import com.my.entity.Bill;
import com.my.entity.BillStatus;
import com.my.entity.Order;
import com.my.entity.Room;
import com.my.entity.RoomStatus;
import com.my.entity.User;
import com.my.exception.AppException;
import com.my.exception.ServiceException;
import com.my.exception.ValidationException;
import com.my.service.BillService;
import com.my.service.OrderService;
import com.my.service.RoomService;
import com.my.service.impl.BillServiceImpl;
import com.my.service.impl.OrderServiceImpl;
import com.my.service.impl.RoomServiceImpl;
import com.my.util.DeleteBillJob;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

public class BookRoomCommand implements Command {
    private static final Logger LOG = Logger.getLogger(BookRoomCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();

        BillService billService = new BillServiceImpl();
        RoomService roomService = new RoomServiceImpl();


        User user = (User) session.getAttribute("user");

        Long roomId = Long.parseLong(request.getParameter("roomId"));

        LocalDate dateIn = LocalDate.parse(request.getParameter("dateIn"));
        LocalDate dateOut = LocalDate.parse(request.getParameter("dateOut"));


        Bill bill = new Bill();
        bill.setRoomId(roomId);
        bill.setUser(user);
        bill.setStatus(BillStatus.NOT_PAID);
        bill.setDateIn(Date.valueOf(dateIn));
        bill.setDateOut(Date.valueOf(dateOut));

        Room room = roomService.getRoomById(roomId);

        if (request.getParameter("orderId") != null) {
            Long orderId = Long.parseLong(request.getParameter("orderId"));
            OrderService orderService = new OrderServiceImpl();
            Order order = orderService.getById(orderId);
            bill.setSum(order.getSum());
            billService.insertBillWithOrder(bill, orderId);
        } else {
            try {
                billService.insertBill(bill);
            } catch (ValidationException e){
                String message = "&message=" + e.getMessage();
                return session.getAttribute("prevPath") + message;
            }
        }

        Timer timer = new Timer();
        timer.schedule(new DeleteBillJob(bill),30000);

        LOG.debug("Command finished");
        return Path.COMMAND_OPEN_PERSONAL_ACCOUNT;
    }
}
