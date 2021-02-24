package com.nure.command.client;

import com.nure.command.Command;
import com.nure.entity.Bill;
import com.nure.entity.BillStatus;
import com.nure.entity.Order;
import com.nure.entity.Room;
import com.nure.entity.User;
import com.nure.exception.AppException;
import com.nure.exception.ValidationException;
import com.nure.service.BillService;
import com.nure.service.OrderService;
import com.nure.service.RoomService;
import com.nure.service.impl.BillServiceImpl;
import com.nure.service.impl.OrderServiceImpl;
import com.nure.service.impl.RoomServiceImpl;
import com.nure.util.DeleteBillJob;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Timer;
/**
 * Book room command
 *
 */
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
