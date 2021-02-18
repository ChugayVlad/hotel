package com.my.service;

import com.my.entity.Order;
import com.my.exception.ServiceException;
import com.my.exception.ValidationException;

import java.util.List;

public interface OrderService {
    void insertOrder(Order order) throws ServiceException, ValidationException;
    Order getById(Long orderId) throws ServiceException;
    List<Order> getAllOrdersByUser(Long id) throws ServiceException;
    void delete(Long id) throws ServiceException;

    List<Order> getAllOrders(int page, int pageSize) throws ServiceException;

    void setRoom(Long orderId, Long roomId) throws ServiceException;

    int getOrdersCount() throws ServiceException;
}
