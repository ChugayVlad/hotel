package com.my.dao;

import com.my.dao.Dao;
import com.my.entity.Order;
import com.my.exception.DAOException;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> getAll(int page, int pageSize) throws DAOException;

    List<Order> getAllByUser(Long id) throws DAOException;

    int getOrdersCount() throws DAOException;
}
