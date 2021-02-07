package com.my.service.impl;

import com.my.dao.factory.DaoFactoryMySql;
import com.my.dao.OrderDao;
import com.my.entity.Order;
import com.my.exception.AppException;
import com.my.exception.DBException;
import com.my.service.OrderService;
import org.apache.log4j.Logger;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao;

    public OrderServiceImpl() throws AppException {
        try {
            orderDao = DaoFactoryMySql.getOrderDao();
        } catch (DBException e) {
            LOG.error("Can not get order dao", e);
            throw new AppException("Database error"); //=======|||||||||||||||||||||||||||||||||||||||
        }
    }

    @Override
    public void insertOrder(Order order) throws AppException {
        try {
            orderDao.insert(order);
        } catch (DBException e) {
            LOG.error("Can not create order", e);
            throw new AppException("Can not create order");
        }
    }
}
