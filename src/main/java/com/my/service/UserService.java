package com.my.service;

import com.my.entity.User;
import com.my.exception.ServiceException;

public interface UserService {
    User findUser(String email, String password) throws ServiceException;
    void insertUser(User user) throws ServiceException;
    void update(User user) throws ServiceException;
}
