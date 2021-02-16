package com.my.dao;

import com.my.entity.Room;
import com.my.entity.RoomStatus;
import com.my.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface RoomDao extends Dao<Room> {
    List<Room> getAll(int page, int pageSize, String sort, String order, String status) throws DAOException;

    void updateStatus(RoomStatus status, Long id) throws DAOException;

    int getRoomsNumber() throws DAOException;

    List<Room> findRoomsByParameters(Integer places, Long typeId, Date dateIn, Date dateOut) throws DAOException;

    int getRoomsNumberByStatus(String status) throws DAOException;
}
