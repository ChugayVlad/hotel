package com.nure.dao;

import com.nure.entity.Order;
import com.nure.exception.DAOException;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    /**
     * Finds all orders
     *
     * @param page - number of page
     * @param pageSize - amount of entries
     * @return a {@link List} of orders
     * @throws DAOException if issues with connection or data source
     */
    List<Order> getAll(int page, int pageSize) throws DAOException;

    /**
     * Finds all orders by user
     *
     * @param id - user id
     * @return a {@link List} of orders
     * @throws DAOException if issues with connection or data source
     */
    List<Order> getAllByUser(Long id) throws DAOException;

    /**
     * Finds number of orders in a database
     *
     * @return a count of orders from database
     * @throws DAOException if issues with connection or data source
     */
    int getOrdersCount() throws DAOException;
}
