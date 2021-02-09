package com.my.dao;

import com.my.entity.Bill;
import com.my.exception.DAOException;

import java.util.List;

public interface BillDao extends Dao<Bill>{
    List<Bill> getAllByUserId(Long id) throws DAOException;
}
