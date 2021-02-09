package com.my.dao;

import com.my.entity.User;
import com.my.exception.DAOException;

import java.sql.SQLException;

public interface UserDao extends Dao<User> {
    User getUserByEmail(String email) throws DAOException;
}
