package com.my.command;

import com.my.entity.Room;
import com.my.exception.AppException;
import com.my.service.RoomService;
import com.my.service.impl.RoomServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoomDescriptionCommand implements Command{
    private static final Logger LOG = Logger.getLogger(RoomDescriptionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");

        String roomId = request.getParameter("roomId");
        LOG.trace("Room id-->" + roomId);
        request.setAttribute("roomId", roomId);

        request.setAttribute("roomStatus", request.getParameter("roomStatus"));

        LOG.debug("Command finished");
        return Path.PAGE_ROOM_DESCRIPTION;
    }
}
