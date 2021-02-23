package com.nure.dao;

import com.nure.entity.Entity;
import com.nure.exception.DAOException;
import com.nure.exception.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * CRUD operations interface
 */
public interface Dao<T extends Entity> {
    /**
     * Inserts entity to database
     *
     * @param entity - entity to insert
     * @throws DAOException if can not insert entity
     */
    void insert(T entity) throws DAOException;

    /**
     * Deletes entity from database
     *
     * @param id - id of entity
     * @throws DAOException if can not delete entity
     */
    void delete(Long id) throws DAOException;

    /**
     * Finds entity from database
     *
     * @return entity
     * @param id - entity id
     * @throws DAOException
     */
    T get(Long id) throws DAOException;

    /**
     * Find all entities from database
     *
     * @param entity - entity to insert
     */
    void update(T entity) throws DAOException;

    List<T> getAll() throws DAOException;


    /**
     * Closes a statement object.
     */
    default void closeStatement(Statement statement) throws DAOException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                throw new DAOException(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    default void closeResultSet(ResultSet rs) throws DAOException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DAOException(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }
}