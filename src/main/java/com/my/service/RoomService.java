package com.my.service;

import com.my.entity.Room;
import com.my.entity.RoomType;
import com.my.exception.AppException;
import com.my.exception.ServiceException;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms() throws ServiceException;
    List<Room> getAllRoomsByParameters(Integer places, Integer typeId) throws ServiceException;
    Room getRoomById(Long id) throws ServiceException;
}
