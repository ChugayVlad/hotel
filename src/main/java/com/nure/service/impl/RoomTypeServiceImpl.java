package com.nure.service.impl;

import com.nure.dao.datasource.DatasourceType;
import com.nure.dao.factory.DaoFactory;
import com.nure.dao.RoomTypeDao;
import com.nure.entity.RoomType;
import com.nure.exception.AppException;
import com.nure.exception.DAOException;
import com.nure.service.RoomTypeService;
import org.apache.log4j.Logger;

import java.util.List;

public class RoomTypeServiceImpl implements RoomTypeService {
    private static final Logger LOG = Logger.getLogger(RoomTypeServiceImpl.class);
    private static final DatasourceType dbType = DatasourceType.MY_SQL;
    private static DaoFactory daoFactory;

    public RoomTypeServiceImpl() throws AppException {
        try {
            daoFactory = DaoFactory.getDaoFactory(dbType);

        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<RoomType> getAllTypes() throws AppException {
        RoomTypeDao roomTypeDao;
        List<RoomType> roomTypes;
        try {
            daoFactory.open();
            roomTypeDao = daoFactory.getRoomTypeDao();
            roomTypes = roomTypeDao.getAll();
        } catch (DAOException e) {
            LOG.error("Can not find room types", e);
            throw new AppException("Can not find room types");
        } finally {
            daoFactory.close();
        }
        return roomTypes;
    }

    @Override
    public RoomType getById(long id) throws AppException {
        RoomTypeDao roomTypeDao;
        RoomType type = null;
        try {
            daoFactory.open();
            roomTypeDao = daoFactory.getRoomTypeDao();
            type = roomTypeDao.get(id);
        } catch (DAOException e) {
            LOG.error("Can not find room type by id", e);
            throw new AppException("Can not find room type by id");
        } finally {
            daoFactory.close();
        }
        return type;
    }
}
