package com.nure.service;

import com.nure.entity.Bill;
import com.nure.exception.DAOException;
import com.nure.exception.ServiceException;
import com.nure.exception.ValidationException;

import java.util.List;

public interface BillService {
    /**
     * Creates bill
     *
     * @param bill - bill to insert
     * @throws ServiceException if issues with dao layer
     */
    void insertBill(Bill bill) throws ServiceException, ValidationException;
    List<Bill> getAllBills(Long userId) throws ServiceException;

    /**
     * Inserts bill which created with order parameters
     *
     * @param bill - bill to insert
     * @param orderId - id of order, which deletes after bill created
     * @throws ServiceException if issues with dao layer
     */
    void insertBillWithOrder(Bill bill, Long orderId) throws ServiceException;

    /**
     * Finds bill by id
     *
     * @param billId - bill id
     * @return bill from database
     * @throws ServiceException if issues with dao layer
     */
    Bill getBillById(Long billId) throws ServiceException;

    /**
     * Updates bill status
     *
     * @param bill - bill to update
     * @throws ServiceException if issues with dao layer
     */
    void updateStatus(Bill bill) throws ServiceException;

    /**
     * Deletes bill by room id
     *
     * @param bill - bill to delete
     * @throws ServiceException if issues with dao layer
     */
    void deleteIfNotPaid(Bill bill) throws ServiceException;

    /**
     * Finds all bills by room id
     *
     * @param roomId - id of room
     * @return a {@link List} of bills
     * @throws ServiceException if issues with dao layer
     */
    List<Bill> getBillByRoom(Long roomId) throws ServiceException;
}
