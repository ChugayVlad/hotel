package com.my.service;

import com.my.entity.Room;
import com.my.entity.RoomType;
import com.my.exception.AppException;
import com.my.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface RoomService {
    List<Room> getAllRooms(int page, int pageSize, String order) throws ServiceException;
    List<Room> getAllRoomsByParameters(Integer places, Long typeId, Date dateIn, Date dateOut) throws ServiceException;
    Room getRoomById(Long id) throws ServiceException;
    int getRoomsCount() throws ServiceException;
}
