package com.nure.service;

import com.nure.entity.Bill;
import com.nure.exception.ServiceException;
import com.nure.exception.ValidationException;

import java.util.List;

public interface BillService {
    void insertBill(Bill bill) throws ServiceException, ValidationException;
    List<Bill> getAllBills(Long userId) throws ServiceException;
    void insertBillWithOrder(Bill bill, Long orderId) throws ServiceException;
    Bill getBillById(Long billId) throws ServiceException;
    void updateStatus(Bill bill) throws ServiceException;
    void deleteIfNotPaid(Bill bill) throws ServiceException;

    List<Bill> getBillByRoom(Long roomId) throws ServiceException;
}
