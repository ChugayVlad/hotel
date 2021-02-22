package com.nure.dao;

import com.nure.entity.User;
import com.nure.exception.DAOException;

public interface UserDao extends Dao<User> {
    User getUserByEmail(String email) throws DAOException;
}
