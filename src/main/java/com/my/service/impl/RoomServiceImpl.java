package com.my.service.impl;

import com.my.dao.datasource.DatasourceType;
import com.my.dao.factory.DaoFactory;
import com.my.dao.RoomDao;
import com.my.dao.mySqlDaoImpl.RoomDaoMySql;
import com.my.entity.Room;
import com.my.exception.AppException;
import com.my.exception.DAOException;
import com.my.exception.ServiceException;
import com.my.service.RoomService;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private static final DatasourceType dbType = DatasourceType.MY_SQL;
    private static DaoFactory daoFactory;

    public RoomServiceImpl() {
        try {
            daoFactory = DaoFactory.getDaoFactory(dbType);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Room> getAllRooms(int page, int pageSize, String order) throws ServiceException {
        List<Room> rooms;
        try {
            daoFactory.open();
            RoomDao roomDao = daoFactory.getRoomDao();
            rooms = roomDao.getAll(page, pageSize, order);
        } catch (DAOException e) {
            LOG.error("Can not find rooms", e);
            throw new ServiceException("Can not find rooms");
        } finally {
            daoFactory.close();
        }
        return rooms;
    }

    @Override
    public List<Room> getAllRoomsByParameters(Integer places, Long typeId, Date dateIn, Date dateOut) throws ServiceException {
        List<Room> rooms;
        try {
            daoFactory.open();
            RoomDao roomDao = daoFactory.getRoomDao();
            rooms = roomDao.findRoomsByParameters(places, typeId, dateIn, dateOut);
        } catch (DAOException e) {
            LOG.error("Can not find rooms by parameters", e);
            throw new ServiceException("Can not find rooms by parameters");
        } finally {
            daoFactory.close();
        }
        return rooms;
    }

    @Override
    public Room getRoomById(Long id) throws ServiceException {
        Room room;
        try {
            daoFactory.open();
            RoomDao roomDao = daoFactory.getRoomDao();
            room = roomDao.get(id);
        } catch (DAOException e) {
            LOG.error("Cannot get room by id", e);
            throw new ServiceException("Cannot get room by id", e);
        } finally {
            daoFactory.close();
        }
        return room;
    }

    @Override
    public int getRoomsCount() throws ServiceException {
        int roomsCount;
        try {
            daoFactory.open();
            RoomDao roomDao = daoFactory.getRoomDao();
            roomsCount = roomDao.getRoomsNumber();
        } catch (DAOException e) {
            LOG.error("Cannot get rooms count", e);
            throw new ServiceException("Cannot get rooms count", e);
        } finally {
            daoFactory.close();
        }
        return roomsCount;
    }
}
