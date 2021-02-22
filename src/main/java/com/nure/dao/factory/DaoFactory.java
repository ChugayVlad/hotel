package com.nure.dao.factory;

import com.nure.dao.BillDao;
import com.nure.dao.OrderDao;
import com.nure.dao.RoomDao;
import com.nure.dao.RoomTypeDao;
import com.nure.dao.UserDao;
import com.nure.dao.datasource.DatasourceType;
import com.nure.exception.DAOException;
import org.apache.log4j.Logger;

public abstract class DaoFactory {
    private static final Logger log = Logger.getLogger(DaoFactory.class);

    public abstract UserDao getUserDao();
    public abstract OrderDao getOrderDao();
    public abstract BillDao getBillDao();
    public abstract RoomDao getRoomDao();
    public abstract RoomTypeDao getRoomTypeDao();

    public abstract void open() throws DAOException;

    /**
     * Closes connection to Data Source
     */
    public abstract void close();


    public abstract void beginTransaction() throws DAOException;

    public abstract void commitTransaction() throws DAOException;

    public abstract void rollbackTransaction();

    public static DaoFactory getDaoFactory(DatasourceType dataBase) throws DAOException {
        switch (dataBase) {
            case MY_SQL:
                return new DaoFactoryMySql();
            case ORACLE:
                log.error("Database " + dataBase + " not supported yet");
                throw new DAOException("Database " + dataBase + " not supported yet");
            default:
                log.error("Database not supported yet");
                throw new DAOException("Database not supported yet");
        }
    }
}
