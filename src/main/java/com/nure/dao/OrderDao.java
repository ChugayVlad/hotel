package com.nure.dao;

import com.nure.entity.Order;
import com.nure.exception.DAOException;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> getAll(int page, int pageSize) throws DAOException;

    List<Order> getAllByUser(Long id) throws DAOException;

    int getOrdersCount() throws DAOException;
}
