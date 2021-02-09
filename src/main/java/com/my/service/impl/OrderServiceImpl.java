package com.my.service.impl;

import com.my.dao.RoomDao;
import com.my.dao.datasource.DatasourceType;
import com.my.dao.factory.DaoFactory;
import com.my.dao.OrderDao;
import com.my.entity.Order;
import com.my.entity.RoomStatus;
import com.my.exception.AppException;
import com.my.exception.DAOException;
import com.my.exception.ServiceException;
import com.my.service.OrderService;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);
    private static final DatasourceType dbType = DatasourceType.MY_SQL;
    private static DaoFactory daoFactory;

    public OrderServiceImpl() throws AppException {
        try {
            daoFactory = DaoFactory.getDaoFactory(dbType);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Override
    public void insertOrder(Order order) throws ServiceException {
        OrderDao orderDao;
        try {
            daoFactory.open();
            orderDao = daoFactory.getOrderDao();
            orderDao.insert(order);
        } catch (DAOException e) {
            LOG.error("Can not create order", e);
            throw new ServiceException("Can not create order", e);
        } finally {
            daoFactory.close();
        }
    }

    @Override
    public List<Order> getAllOrders() throws ServiceException {
        List<Order> orders;
        try {
            daoFactory.open();
            OrderDao orderDao = daoFactory.getOrderDao();
            orders = orderDao.getAll();
        } catch (DAOException e) {
            LOG.error("Can not get all orders", e);
            throw new ServiceException("Can not all orders", e);
        } finally {
            daoFactory.close();
        }
        return orders;
    }

    @Override
    public void setRoom(Order order) throws ServiceException {
        try {
            daoFactory.beginTransaction();
            OrderDao orderDao = daoFactory.getOrderDao();
            RoomDao roomDao = daoFactory.getRoomDao();
            orderDao.update(order);
            roomDao.updateStatus(RoomStatus.NOT_AVAILABLE, order.getRoomId());
            daoFactory.commitTransaction();
        } catch (DAOException e) {
            daoFactory.rollbackTransaction();
            LOG.error("Can not set room for order", e);
            throw new ServiceException("Can not set room for order", e);
        } finally {
            daoFactory.close();
        }
    }

    @Override
    public Order getById(Long orderId) throws ServiceException {
        Order order = null;
        try {
            daoFactory.open();
            OrderDao orderDao = daoFactory.getOrderDao();
            order = orderDao.get(orderId);
        } catch (DAOException e) {
            LOG.error("Can not get order by id", e);
            throw new ServiceException("Can not get order by id", e);
        } finally {
            daoFactory.close();
        }
        return order;
    }

    @Override
    public List<Order> getAllOrdersByUser(Long id) throws ServiceException {
        List<Order> orders;
        try {
            daoFactory.open();
            OrderDao orderDao = daoFactory.getOrderDao();
            orders = orderDao.getAllByUser(id);
        } catch (DAOException e) {
            LOG.error("Can not get all orders for user", e);
            throw new ServiceException("Can not all orders for user", e);
        } finally {
            daoFactory.close();
        }
        return orders;
    }
}