package com.nure.dao;

import com.nure.entity.Bill;
import com.nure.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface BillDao extends Dao<Bill>{
    /**
     * Finds all bills for room with date span
     *
     * @param roomId - id of room
     * @param dateIn - arrival date
     * @param dateOut - departure date
     * @return a {@link List} of bills
     * @throws DAOException if issues with connection or data source
     */
    List<Bill> getAllByDate(Long roomId, Date dateIn, Date dateOut) throws DAOException;

    /**
     * Finds all bills by user id
     *
     * @param id - id of User
     * @return a {@link List} of bills
     * @throws DAOException if issues with connection or data source
     */
    List<Bill> getAllByUserId(Long id) throws DAOException;

    /**
     * Finds all bills for room with date span
     *
     * @param bill - bill with necessary fields
     * @return a bill containing necessary data
     * @throws DAOException if issues with connection or data source
     */
    Bill getBillByParams(Bill bill) throws DAOException;

    /**
     * Finds all bills for room by room id
     *
     * @param roomId - id of room
     * @return a {@link List} of bills
     * @throws DAOException if issues with connection or data source
     */
    List<Bill> getBillByRoom(Long roomId) throws DAOException;
}
