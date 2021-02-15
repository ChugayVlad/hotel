package com.my.service.impl;

import com.my.dao.datasource.DatasourceType;
import com.my.dao.factory.DaoFactory;
import com.my.dao.UserDao;
import com.my.entity.User;
import com.my.exception.AppException;
import com.my.exception.DAOException;
import com.my.exception.Messages;
import com.my.exception.ServiceException;
import com.my.service.UserService;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private static final DatasourceType dbType = DatasourceType.MY_SQL;
    private static DaoFactory daoFactory;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(dbType);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    @Override
    public User findUser(String email, String password) throws ServiceException {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new ServiceException("Login/password cannot be empty");
        }
        User user = new User();
        UserDao userDao;
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            user = userDao.getUserByEmail(email);
            LOG.trace("Found in DB: user --> " + user);
            if (user == null || !password.equals(user.getPassword())) {
                throw new ServiceException("Cannot find user with such login/password");
            }
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException("Can not find user", e);
        } finally {
            daoFactory.close();
        }
        return user;
    }

    @Override
    public void insertUser(User user) throws ServiceException {
        UserDao userDao;
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            userDao.insert(user);
        } catch (DAOException e) {
            LOG.error(Messages.ERR_CANNOT_CREATE_USER, e);
            throw new ServiceException(Messages.ERR_CANNOT_CREATE_USER, e);
        } finally {
            daoFactory.close();
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        UserDao userDao;
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            userDao.update(user);
        } catch (DAOException e) {
            LOG.error("Cannot update user", e);
            throw new ServiceException("Cannot update user", e);
        } finally {
            daoFactory.close();
        }
    }
}
