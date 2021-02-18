package com.my.service;

import com.my.entity.Bill;
import com.my.exception.ServiceException;
import com.my.exception.ValidationException;

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
