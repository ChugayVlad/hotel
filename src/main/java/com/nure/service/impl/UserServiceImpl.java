package com.nure.service.impl;

import com.nure.dao.datasource.DatasourceType;
import com.nure.dao.factory.DaoFactory;
import com.nure.dao.UserDao;
import com.nure.entity.User;
import com.nure.exception.DAOException;
import com.nure.exception.Messages;
import com.nure.exception.ServiceException;
import com.nure.exception.ValidationException;
import com.nure.service.UserService;
import com.nure.util.PasswordHashing;
import com.nure.util.Validator;
import org.apache.log4j.Logger;

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
            String incomingPass = PasswordHashing.hash(password);
            if (user == null || !incomingPass.equals(user.getPassword())) {
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
    public void insertUser(User user) throws ServiceException, ValidationException {
        Validator.validateUser(user);

        UserDao userDao;
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            user.setPassword(PasswordHashing.hash(user.getPassword()));
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
