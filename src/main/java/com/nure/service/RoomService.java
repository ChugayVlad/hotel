package com.nure.service;

import com.nure.entity.Room;
import com.nure.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface RoomService {
    List<Room> getAllRooms(int page, int pageSize, String sort, String order, String status) throws ServiceException;
    List<Room> getAllRoomsByParameters(Integer places, Long typeId, Date dateIn, Date dateOut) throws ServiceException;
    Room getRoomById(Long id) throws ServiceException;
    int getRoomsCount() throws ServiceException;
    int getRoomsNumberByStatus(String status) throws ServiceException;
}
