package com.my.service;

import com.my.entity.Order;
import com.my.exception.ServiceException;

import java.util.List;

public interface OrderService {
    void insertOrder(Order order) throws ServiceException;
    List<Order> getAllOrders() throws ServiceException;
    void setRoom(Order order) throws ServiceException;
    Order getById(Long orderId) throws ServiceException;
    List<Order> getAllOrdersByUser(Long id) throws ServiceException;
}
