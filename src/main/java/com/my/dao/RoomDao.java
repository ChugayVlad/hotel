package com.my.dao;

import com.my.entity.Room;
import com.my.entity.RoomStatus;
import com.my.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface RoomDao extends Dao<Room> {
    void updateStatus(RoomStatus status, Long id) throws DAOException;

    List<Room> findRoomsByParameters(Integer places, Long typeId, Date dateIn, Date dateOut) throws DAOException;
}
