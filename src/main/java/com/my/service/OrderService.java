package com.my.service;

import com.my.entity.Order;
import com.my.exception.AppException;

public interface OrderService {
    void insertOrder(Order order) throws AppException;
}
