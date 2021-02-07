package com.my.service.impl;

import com.my.dao.factory.DaoFactoryMySql;
import com.my.dao.RoomDao;
import com.my.entity.Room;
import com.my.exception.AppException;
import com.my.exception.DBException;
import com.my.service.RoomService;
import org.apache.log4j.Logger;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private RoomDao roomDao;

    public RoomServiceImpl() throws AppException {
        try {
            roomDao = DaoFactoryMySql.getRoomDao();
        } catch (DBException e) {
            LOG.error("Can not get room dao", e);
            throw new AppException("Database error"); //=======|||||||||||||||||||||||||||||||||||||||
        }
    }

    @Override
    public List<Room> getAllRooms() throws AppException {
        List<Room> rooms;
        try {
            rooms = roomDao.listAll();
        } catch (DBException e) {
            LOG.error("Can not find rooms", e);
            throw new AppException("Can not find rooms");
        }
        return rooms;
    }
}
