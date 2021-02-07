package com.my.service;

import com.my.entity.User;
import com.my.exception.AppException;

public interface UserService {
    User findUser(String email, String password) throws AppException;
    void insertUser(User user) throws AppException;
}
