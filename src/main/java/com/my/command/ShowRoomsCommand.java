package com.my.command;

import com.my.command.util.PathUtil;
import com.my.entity.Room;
import com.my.entity.RoomType;
import com.my.exception.AppException;
import com.my.service.RoomService;
import com.my.service.impl.RoomServiceImpl;
import com.my.service.RoomTypeService;
import com.my.service.impl.RoomTypeServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowRoomsCommand implements Command{
    private static final Logger LOG = Logger.getLogger(ShowRoomsCommand.class);
    private static final String DEFAULT_ORDER = "ASC";
    private static final String DEFAULT_SORT = "price";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        PathUtil.saveCurrentPathToSession(request);

        RoomService roomService = new RoomServiceImpl();

        String paramPage = request.getParameter("page");
        String paramPageSize = request.getParameter("pageSize");

        int page = 1;
        if(paramPage != null && !paramPage.isEmpty()){
            page = Integer.parseInt(paramPage);
        }
        int pageSize = 4;
        if(paramPageSize != null && !paramPageSize.isEmpty()){
            pageSize = Integer.parseInt(paramPageSize);
        }

        String sortBy = request.getParameter("sortBy");
        if ("".equals(sortBy)){
            sortBy = DEFAULT_SORT;
        }
        LOG.trace("Order -->> " + sortBy);
        String status = request.getParameter("status");

        String order =  request.getParameter("order");
        if (order == null){
            order = DEFAULT_ORDER;
        }

        List<Room> rooms = roomService.getAllRooms(page, pageSize,  sortBy, order, status);

        LOG.trace("Rooms --> " + rooms);

        request.setAttribute("rooms", rooms);

        int roomsNumber = 0;
        if ("all".equals(status) || status==null || "".equals(status)){
            roomsNumber = roomService.getRoomsCount();
        } else {
            roomsNumber = roomService.getRoomsNumberByStatus(status);
        }
        LOG.trace("Status --> " + status);

        int maxPage = (int) Math.ceil((double)roomsNumber / pageSize);

        RoomTypeService typeService = new RoomTypeServiceImpl();
        List<RoomType> roomTypes = typeService.getAllTypes();
        LOG.trace("Types --> " + roomTypes);

        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("order", order);
        request.setAttribute("status", status);

        request.setAttribute("types", roomTypes);

        LOG.debug("Command finished");
        return Path.PAGE_MAIN;
    }
}
