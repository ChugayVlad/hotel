package com.nure.service;

import com.nure.entity.User;
import com.nure.exception.ServiceException;
import com.nure.exception.ValidationException;

public interface UserService {
    User findUser(String email, String password) throws ServiceException;
    void insertUser(User user) throws ServiceException, ValidationException;
    void update(User user) throws ServiceException;
}
