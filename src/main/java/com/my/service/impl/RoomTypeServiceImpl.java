package com.my.service.impl;

import com.my.dao.factory.DaoFactoryMySql;
import com.my.dao.RoomTypeDao;
import com.my.entity.RoomType;
import com.my.exception.AppException;
import com.my.exception.DBException;
import com.my.service.RoomTypeService;
import org.apache.log4j.Logger;

import java.util.List;

public class RoomTypeServiceImpl implements RoomTypeService {
    private static final Logger LOG = Logger.getLogger(RoomTypeServiceImpl.class);
    private RoomTypeDao roomTypeDao;

    public RoomTypeServiceImpl() throws AppException {
        try {
            roomTypeDao = DaoFactoryMySql.getRoomTypeDao();
        } catch (DBException e) {
            LOG.error("Can not get room type dao", e);
            throw new AppException("Database error"); //=======|||||||||||||||||||||||||||||||||||||||
        }
    }

    @Override
    public List<RoomType> getAllTypes() throws AppException {
        List<RoomType> roomTypes;
        try {
            roomTypes = roomTypeDao.listAll();
        } catch (DBException e) {
            LOG.error("Can not find room types", e);
            throw new AppException("Can not find room types");
        }
        return roomTypes;
    }

    @Override
    public RoomType getById(long id) throws AppException {
        RoomType type = null;
        try {
            type = roomTypeDao.get(id);
        } catch (DBException e) {
            LOG.error("Can not find room type by id", e);
            throw new AppException("Can not find room type by id");
        }
        return type;
    }
}
