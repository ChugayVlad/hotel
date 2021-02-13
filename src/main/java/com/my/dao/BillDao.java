package com.my.dao;

import com.my.entity.Bill;
import com.my.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface BillDao extends Dao<Bill>{
    List<Bill> getAllByDate(Long roomId, Date dateIn, Date dateOut) throws DAOException;
    List<Bill> getAllByUserId(Long id) throws DAOException;
    Bill getBillByParams(Bill bill) throws DAOException;
}
