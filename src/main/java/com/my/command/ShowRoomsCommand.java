package com.my.command;

import com.my.entity.Room;
import com.my.entity.RoomType;
import com.my.exception.AppException;
import com.my.service.RoomService;
import com.my.service.impl.RoomServiceImpl;
import com.my.service.RoomTypeService;
import com.my.service.impl.RoomTypeServiceImpl;
import com.my.util.Path;
import com.my.util.Sort;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowRoomsCommand implements Command{
    private static final Logger LOG = Logger.getLogger(ShowRoomsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        RoomService roomService = new RoomServiceImpl();
        List<Room> rooms = roomService.getAllRooms();
        String sortBy = request.getParameter("sortBy");
        Sort.sort(sortBy, rooms);

        LOG.trace("Rooms --> " + rooms);

        request.setAttribute("rooms", rooms);

        RoomTypeService typeService = new RoomTypeServiceImpl();
        List<RoomType> roomTypes = typeService.getAllTypes();
        LOG.trace("Types --> " + roomTypes);
        request.setAttribute("types", roomTypes);

        LOG.debug("Command finished");
        return Path.PAGE_MAIN;
    }
}
