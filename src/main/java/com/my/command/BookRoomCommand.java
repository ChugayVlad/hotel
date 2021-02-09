package com.my.command;

import com.my.entity.Bill;
import com.my.entity.BillStatus;
import com.my.entity.Room;
import com.my.entity.User;
import com.my.exception.AppException;
import com.my.service.BillService;
import com.my.service.RoomService;
import com.my.service.impl.BillServiceImpl;
import com.my.service.impl.RoomServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookRoomCommand implements Command{
    private static final Logger LOG = Logger.getLogger(BookRoomCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        BillService billService = new BillServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        Long roomId = Long.parseLong(request.getParameter("roomId"));
        Bill bill = new Bill();
        bill.setRoomId(roomId);
        bill.setUserId(user.getId());
        bill.setStatus(BillStatus.NOT_PAID);

        Room room = roomService.getRoomById(roomId);
        bill.setSum(room.getPrice());

        billService.insertBill(bill);

        LOG.debug("Command finished");
        return Path.COMMAND_OPEN_PERSONAL_ACCOUNT;
    }
}
