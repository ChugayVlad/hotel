package com.nure.command.common;

import com.nure.command.Command;
import com.nure.command.util.PathUtil;
import com.nure.entity.Bill;
import com.nure.entity.Role;
import com.nure.entity.Room;
import com.nure.exception.AppException;
import com.nure.service.BillService;
import com.nure.service.RoomService;
import com.nure.service.impl.BillServiceImpl;
import com.nure.service.impl.RoomServiceImpl;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Room command with book information for client and schedule for admin.
 */
public class RoomDescriptionCommand implements Command {
    private static final Logger LOG = Logger.getLogger(RoomDescriptionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        PathUtil.saveCurrentPathToSession(request);
        HttpSession session = request.getSession();
        RoomService roomService = new RoomServiceImpl();

        String roomIdReq = request.getParameter("roomId");
        request.setAttribute("roomId", roomIdReq);
        Long roomId = Long.parseLong(roomIdReq);

        if (Role.ADMIN.equals(session.getAttribute("userRole"))) {
            BillService billService = new BillServiceImpl();
            List<Bill> bills = billService.getBillByRoom(roomId);
            request.setAttribute("bills", bills);
        } else {
            Room room = roomService.getRoomById(roomId);
            request.setAttribute("roomDesc", room.getDescription());
        }
        request.setAttribute("message", request.getParameter("message"));
        LOG.debug("Command finished");
        return Path.PAGE_ROOM_DESCRIPTION;
    }
}
