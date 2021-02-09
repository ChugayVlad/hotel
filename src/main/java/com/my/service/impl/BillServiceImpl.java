package com.my.service.impl;

import com.my.dao.BillDao;
import com.my.dao.RoomDao;
import com.my.dao.datasource.DatasourceType;
import com.my.dao.factory.DaoFactory;
import com.my.entity.Bill;
import com.my.entity.RoomStatus;
import com.my.exception.DAOException;
import com.my.exception.ServiceException;
import com.my.service.BillService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService {
    private static final Logger LOG = Logger.getLogger(BillServiceImpl.class);
    private static final DatasourceType dbType = DatasourceType.MY_SQL;
    private static DaoFactory daoFactory;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(dbType);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Override
    public void insertBill(Bill bill) throws ServiceException {
        BillDao billDao;
        RoomDao roomDao;
        try {
            daoFactory.beginTransaction();
            billDao = daoFactory.getBillDao();
            roomDao = daoFactory.getRoomDao();
            billDao.insert(bill);
            roomDao.updateStatus(RoomStatus.BOOKED, bill.getRoomId());
            daoFactory.commitTransaction();
        } catch (DAOException e) {
            daoFactory.rollbackTransaction();
            LOG.error("Cannot create bill", e);
            throw new ServiceException("Cannot create bill", e);
        } finally {
            daoFactory.close();
        }
    }

    @Override
    public List<Bill> getAllBills(Long userId) throws ServiceException {
        List<Bill> bills;
        try {
            daoFactory.open();
            BillDao billDao = daoFactory.getBillDao();
            bills = billDao.getAllByUserId(userId);
        } catch (DAOException e) {
            LOG.error("Cannot get bills", e);
            throw new ServiceException("Cannot get bills", e);
        } finally {
            daoFactory.close();
        }
        return bills;
    }
}
