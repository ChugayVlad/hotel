package com.nure.service;

import com.nure.entity.User;
import com.nure.exception.DAOException;
import com.nure.exception.ServiceException;
import com.nure.exception.ValidationException;

public interface UserService {
    /**
     * Finds User by name and password
     * @param email - User email
     * @param password - User password
     * @return User with such name and password
     * @throws ServiceException if issues in dao layer
     */
    User findUser(String email, String password) throws ServiceException, ValidationException;

    /**
     * Inserts user in database
     *
     * @param user - user to insert
     * @throws ServiceException if issues in dao layer
     * @throws ValidationException if user not valid
     */
    void insertUser(User user) throws ServiceException, ValidationException;

    /**
     * Updates user in database
     *
     * @param user - user to update
     * @throws ServiceException if issues in dao layer
     * @throws ValidationException if user not valid
     */
    void update(User user) throws ServiceException, ValidationException;

    /**
     * Deletes user from database
     *
     * @param id - id of user will be deleted
     * @throws ServiceException if issues in dao layer
     */
    void delete(Long id) throws ServiceException;
}
