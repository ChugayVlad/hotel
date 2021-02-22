package com.nure.service;

import com.nure.entity.RoomType;
import com.nure.exception.AppException;

import java.util.List;

public interface RoomTypeService {
    List<RoomType> getAllTypes() throws AppException;
    RoomType getById(long id) throws AppException;
}
