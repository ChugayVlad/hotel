package com.nure.dao;

import com.nure.entity.Entity;
import com.nure.exception.DAOException;
import com.nure.exception.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Dao<T extends Entity> {
    void insert(T entity) throws DAOException;
    void delete(Long id) throws DAOException;
    T get(Long id) throws DAOException;
    void update(T entity) throws DAOException;
    List<T> getAll() throws DAOException;

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