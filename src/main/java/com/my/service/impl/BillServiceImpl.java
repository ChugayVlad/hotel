package com.my.service.impl;

import com.my.dao.BillDao;
import com.my.dao.OrderDao;
import com.my.dao.RoomDao;
import com.my.dao.datasource.DatasourceType;
import com.my.dao.factory.DaoFactory;
import com.my.entity.Bill;
import com.my.entity.BillStatus;
import com.my.entity.Order;
import com.my.entity.Room;
import com.my.entity.RoomStatus;
import com.my.exception.DAOException;
import com.my.exception.ServiceException;
import com.my.service.BillService;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        LocalDate currentDate = LocalDate.now();
        if (bill.getDateIn().compareTo(Date.valueOf(currentDate)) < 0 || bill.getDateOut().compareTo(Date.valueOf(currentDate)) < 0) {
            throw new ServiceException("Date cannot be past!");
        }

        try {
            daoFactory.beginTransaction();
            billDao = daoFactory.getBillDao();
            List<Bill> bills = billDao.getAllByDate(bill.getRoomId(), bill.getDateIn(), bill.getDateOut());

            if (!bills.isEmpty()) {
                throw new ServiceException("Sorry, room is busy for these dates");
            }


            roomDao = daoFactory.getRoomDao();
            Room room = roomDao.get(bill.getRoomId());

            long daysStay = ChronoUnit.DAYS.between(bill.getDateIn().toLocalDate(), bill.getDateOut().toLocalDate());
            Double sum = daysStay * room.getPrice();
            bill.setSum(sum);

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

    @Override
    public void insertBillWithOrder(Bill bill, Long orderId) throws ServiceException {
        try {
            daoFactory.beginTransaction();
            BillDao billDao = daoFactory.getBillDao();

            RoomDao roomDao = daoFactory.getRoomDao();
            OrderDao orderDao = daoFactory.getOrderDao();
            billDao.insert(bill);
            roomDao.updateStatus(RoomStatus.BOOKED, bill.getRoomId());
            orderDao.delete(orderId);
            daoFactory.commitTransaction();
        } catch (DAOException e) {
            daoFactory.rollbackTransaction();
            LOG.error("Cannot insert bill or delete order", e);
            throw new ServiceException("Cannot insert bill or delete order", e);
        } finally {
            daoFactory.close();
        }
    }

    @Override
    public Bill getBillById(Long billId) throws ServiceException {
        Bill bill;
        try {
            daoFactory.open();
            BillDao billDao = daoFactory.getBillDao();
            bill = billDao.get(billId);
        } catch (DAOException e) {
            LOG.error("Cannot get bill", e);
            throw new ServiceException("Cannot get bill", e);
        } finally {
            daoFactory.close();
        }

        return bill;
    }

    @Override
    public void updateStatus(Bill bill) throws ServiceException {
        try {
            daoFactory.open();
            BillDao billDao = daoFactory.getBillDao();
            billDao.update(bill);
        } catch (DAOException e) {
            LOG.error("Cannot set new bill status", e);
            throw new ServiceException("Cannot set new bill status", e);
        } finally {
            daoFactory.close();
        }
    }

    @Override
    public void deleteIfNotPaid(Bill bill) throws ServiceException {
        try {
            daoFactory.beginTransaction();
            BillDao billDao = daoFactory.getBillDao();
            RoomDao roomDao = daoFactory.getRoomDao();
            Bill billToDelete = billDao.getBillByParams(bill);
            LOG.trace("Bill found in db -->> " + billToDelete);
            if (billToDelete.getStatus() == (BillStatus.NOT_PAID)) {
                LOG.trace("Delete bill with id -->> " + billToDelete.getId());
                billDao.delete(billToDelete.getId());
                roomDao.updateStatus(RoomStatus.VACANT, bill.getRoomId());
            }
            daoFactory.commitTransaction();
        } catch (DAOException e) {
            daoFactory.rollbackTransaction();
            LOG.error("Cannot delete bill by these parameters " + bill, e);
            throw new ServiceException("Cannot delete bill by these parameters " + bill, e);
        } finally {
            daoFactory.close();
        }
    }

    @Override
    public List<Bill> getBillByRoom(Long roomId) throws ServiceException {
        List<Bill> bills;
        try {
            daoFactory.open();
            BillDao billDao = daoFactory.getBillDao();
            bills = billDao.getBillByRoom(roomId);
        } catch (DAOException e) {
            LOG.error("Cannot get bill by room", e);
            throw new ServiceException("Cannot get bill by room", e);
        } finally {
            daoFactory.close();
        }
        return bills;
    }
}
