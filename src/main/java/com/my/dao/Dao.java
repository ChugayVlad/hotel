package com.my.dao;

import com.my.entity.Entity;
import com.my.exception.DBException;

import java.util.List;

public interface Dao<T extends Entity> {
    void insert(T entity) throws DBException;
    void delete(long id);
    T get(long id) throws DBException;
    List<T> listAll() throws DBException;

    /*default Connection getConnection() throws DBException {
        return ConnectionBuilder.getInstance().getConnection();
    }*/
}