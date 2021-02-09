package com.my.service;

import com.my.entity.Bill;
import com.my.exception.ServiceException;

import java.util.List;

public interface BillService {
    void insertBill(Bill bill) throws ServiceException;
    List<Bill> getAllBills(Long userId) throws ServiceException;
}
