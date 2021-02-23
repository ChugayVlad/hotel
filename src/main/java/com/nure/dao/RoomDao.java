package com.nure.dao;

import com.nure.entity.Room;
import com.nure.entity.RoomStatus;
import com.nure.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface RoomDao extends Dao<Room> {
    /**
     * Finds all rooms
     *
     * @param page - a number from which entries will be returned
     * @param pageSize -amount of entries
     * @param sort - parameter of sort
     * @param order - in which order will sort
     * @param status - status of room
     * @return a count of rooms from database
     * @throws DAOException if issues with connection or data source
     */
    List<Room> getAll(int page, int pageSize, String sort, String order, String status) throws DAOException;

    /**
     * Updates room status
     *
     * @param status - room status
     * @param id - room id
     * @throws DAOException if issues with connection or data source
     */
    void updateStatus(RoomStatus status, Long id) throws DAOException;

    /**
     * Finds number of rooms in a database
     *
     * @return a count of rooms from database
     * @throws DAOException if issues with connection or data source
     */
    int getRoomsNumber() throws DAOException;

    /**
     * Finds all rooms by parameters
     *
     * @param places - room places
     * @param typeId - id type of room
     * @param dateIn - arrival date
     * @param dateOut - departure date
     * @return a count of rooms from database
     * @throws DAOException if issues with connection or data source
     */
    List<Room> findRoomsByParameters(Integer places, Long typeId, Date dateIn, Date dateOut) throws DAOException;

    /**
     * Finds number of rooms by status in a database
     *
     * @return a count of rooms by status from database
     * @throws DAOException if issues with connection or data source
     */
    int getRoomsNumberByStatus(String status) throws DAOException;
}
