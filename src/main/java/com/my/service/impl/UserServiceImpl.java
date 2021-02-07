package com.my.service.impl;

import com.my.dao.factory.DaoFactoryMySql;
import com.my.dao.UserDao;
import com.my.entity.User;
import com.my.exception.AppException;
import com.my.exception.DBException;
import com.my.exception.Messages;
import com.my.service.UserService;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    //==TO DO ========
    private UserDao userDao;

    public UserServiceImpl() throws AppException {
        try {
        userDao = DaoFactoryMySql.getUserDao();
        } catch (DBException e) {
            LOG.error("Can not get user dao", e);
            throw new AppException("Database error"); //=======|||||||||||||||||||||||||||||||||||||||
        }
    }
    //=================

    @Override
    public User findUser(String email, String password) throws AppException {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()){
            throw new AppException("Login/password cannot be empty");
        }
        User user = userDao.getUserByEmail(email);
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())){
            throw new AppException("Cannot find user with such login/password");
        }
        return user;
    }

    @Override
    public void insertUser(User user) throws AppException {
        try {
            userDao.insert(user);
        } catch (DBException e) {
            LOG.error(Messages.ERR_CANNOT_CREATE_USER, e);
            throw new AppException(Messages.ERR_CANNOT_CREATE_USER, e);
        }
    }
}
