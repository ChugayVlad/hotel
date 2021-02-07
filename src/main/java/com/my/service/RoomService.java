package com.my.service;

import com.my.entity.Room;
import com.my.entity.RoomType;
import com.my.exception.AppException;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms() throws AppException;
}
