package com.my.dao;

import com.my.dao.Dao;
import com.my.entity.Order;
import com.my.exception.DAOException;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> getAllByUser(Long id) throws DAOException;
}
