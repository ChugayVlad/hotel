package com.nure.dao;

import com.nure.entity.Bill;
import com.nure.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface BillDao extends Dao<Bill>{
    List<Bill> getAllByDate(Long roomId, Date dateIn, Date dateOut) throws DAOException;
    List<Bill> getAllByUserId(Long id) throws DAOException;
    Bill getBillByParams(Bill bill) throws DAOException;

    List<Bill> getBillByRoom(Long roomId) throws DAOException;
}
