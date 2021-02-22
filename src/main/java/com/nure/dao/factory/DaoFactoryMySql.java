package com.nure.dao.factory;

import com.nure.dao.BillDao;
import com.nure.dao.datasource.DataSourceFactory;
import com.nure.dao.datasource.DatasourceType;
import com.nure.dao.OrderDao;
import com.nure.dao.mySqlDaoImpl.BillDaoMysql;
import com.nure.dao.mySqlDaoImpl.OrderDaoMySql;
import com.nure.dao.RoomDao;
import com.nure.dao.mySqlDaoImpl.RoomDaoMySql;
import com.nure.dao.RoomTypeDao;
import com.nure.dao.mySqlDaoImpl.RoomTypeDaoMySql;
import com.nure.dao.UserDao;
import com.nure.dao.mySqlDaoImpl.UserDaoMySql;
import com.nure.exception.DAOException;
import com.nure.exception.Messages;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DaoFactoryMySql extends DaoFactory{
    private static final Logger LOG = Logger.getLogger(DaoFactoryMySql.class);
    private static DataSource dataSource;
    private Connection connection;

    DaoFactoryMySql() throws DAOException {
        try {
            dataSource = DataSourceFactory.getInstance(DatasourceType.MY_SQL).getDatasource();
        } catch (DAOException e) {
            LOG.error("Incorrect db property", e);
            throw new DAOException("Incorrect db property", e);
        }
    }

    private static Connection getConnection() throws DAOException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("Cannot create connection", e);
            throw new DAOException("Cannot create connection", e);
        }
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoMySql(connection);
    }

    @Override
    public OrderDao getOrderDao() {
        return new OrderDaoMySql(connection);
    }

    @Override
    public BillDao getBillDao() {
        return new BillDaoMysql(connection);
    }

    @Override
    public RoomDao getRoomDao() {
        return new RoomDaoMySql(connection);
    }

    @Override
    public RoomTypeDao getRoomTypeDao() {
        return new RoomTypeDaoMySql(connection);
    }

    @Override
    public void open() throws DAOException {
        try {
            connection = getConnection();
        } catch (DAOException e) {
            LOG.error("Cannot open connection", e);
            throw new DAOException("Cannot open connection", e);
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    @Override
    public void beginTransaction() throws DAOException {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOG.error("Cannot begin transaction", e);
            throw new DAOException("Cannot begin transaction", e);
        }
    }

    @Override
    public void commitTransaction() throws DAOException {
        try {
            if (connection != null){
                connection.commit();
            }
        } catch (SQLException e) {
            LOG.error("Cannot commit transaction", e);
            throw new DAOException("Cannot commit transaction", e);
        }
    }

    @Override
    public void rollbackTransaction(){
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error("Cannot rollback transaction", e);
        }
    }
}
