package com.my.service;

import com.my.entity.RoomType;
import com.my.exception.AppException;

import java.util.List;

public interface RoomTypeService {
    List<RoomType> getAllTypes() throws AppException;
    RoomType getById(long id) throws AppException;
}
