package com.nure.dao;

import com.nure.entity.User;
import com.nure.exception.DAOException;

public interface UserDao extends Dao<User> {
    /**
     * Finds User by name
     * @param email - User email
     * @return User by name
     * @throws DAOException if connection is down, broken or unable to retrieve information for certain reasons
     */
    User getUserByEmail(String email) throws DAOException;
}
