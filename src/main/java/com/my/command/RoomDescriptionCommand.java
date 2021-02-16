package com.my.command;

import com.my.entity.Bill;
import com.my.entity.Role;
import com.my.entity.Room;
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
import java.util.List;

public class RoomDescriptionCommand implements Command{
    private static final Logger LOG = Logger.getLogger(RoomDescriptionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        RoomService roomService = new RoomServiceImpl();

        String roomIdReq = request.getParameter("roomId");
        request.setAttribute("roomId", roomIdReq);
        Long roomId = Long.parseLong(roomIdReq);

        if (Role.ADMIN.equals(session.getAttribute("userRole"))){
            BillService billService = new BillServiceImpl();
            List<Bill> bills = billService.getBillByRoom(roomId);
            request.setAttribute("bills", bills);
        } else {
            Room room = roomService.getRoomById(roomId);
            request.setAttribute("roomDesc", room.getDescription());
        }
        LOG.debug("Command finished");
        return Path.PAGE_ROOM_DESCRIPTION;
    }
}
