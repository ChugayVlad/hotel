package com.my.dao;

import com.my.dao.Dao;
import com.my.entity.User;
import com.my.exception.DBException;

public interface UserDao extends Dao<User> {
    User getUserByEmail(String email) throws DBException;
}
