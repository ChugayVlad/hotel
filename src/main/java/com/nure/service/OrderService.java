package com.nure.service;

import com.nure.entity.Order;
import com.nure.exception.DAOException;
import com.nure.exception.ServiceException;
import com.nure.exception.ValidationException;

import java.util.List;

public interface OrderService {
    /**
     * Creates order
     *
     * @param order - order to insert in database
     * @throws ServiceException if issues in dao layer
     */
    void insertOrder(Order order) throws ServiceException, ValidationException;

    /**
     * Finds order by id
     *
     * @param orderId - order's id
     * @return order
     * @throws ServiceException if issues in dao layer
     */
    Order getById(Long orderId) throws ServiceException;

    /**
     * Finds all orders by user
     *
     * @param id - user's id
     * @return a {@link List} of orders
     * @throws ServiceException if issues in dao layer
     */
    List<Order> getAllOrdersByUser(Long id) throws ServiceException;

    /**
     * Deletes order from database
     *
     * @param id - order id
     * @throws ServiceException if issues in dao layer
     */
    void delete(Long id) throws ServiceException;

    /**
     * Finds all orders
     *
     * @param page - number of page
     * @param pageSize - amount of entries
     * @return a {@link List} of orders
     * @throws ServiceException if issues in dao layer
     */
    List<Order> getAllOrders(int page, int pageSize) throws ServiceException;

    /**
     * Adds room id to bill
     *
     * @param orderId - order id
     * @param roomId - room id
     * @throws ServiceException if issues in dao layer
     */
    void setRoom(Long orderId, Long roomId) throws ServiceException;

    /**
     * Finds number of orders in a database
     *
     * @return a count of orders from database
     * @throws ServiceException if issues in dao layer
     */
    int getOrdersCount() throws ServiceException;
}
