package com.nure.dao.factory;

import com.nure.dao.BillDao;
import com.nure.dao.OrderDao;
import com.nure.dao.RoomDao;
import com.nure.dao.UserDao;
import com.nure.dao.datasource.DatasourceType;
import com.nure.exception.DAOException;
import org.apache.log4j.Logger;

public abstract class DaoFactory {
    private static final Logger log = Logger.getLogger(DaoFactory.class);

    /**
     * DAO Factory methods /
     *
     * Creates User DAO
     *
     * @return User DAO
     */
    public abstract UserDao getUserDao();

    /**
     * DAO Factory methods /
     *
     * Creates User DAO
     *
     * @return User DAO
     */
    public abstract OrderDao getOrderDao();

    /**
     * DAO Factory methods /
     *
     * Creates Bill DAO
     *
     * @return Bill DAO
     */
    public abstract BillDao getBillDao();

    /**
     * DAO Factory methods /
     *
     * Creates Room DAO
     *
     * @return Room DAO
     */
    public abstract RoomDao getRoomDao();

    /**
     * Opens connection to Data Source
     * @throws DAOException if unable to open connection
     */
    public abstract void open() throws DAOException;

    /**
     * Closes connection to Data Source
     */
    public abstract void close();

    /**
     * Opens DB data transaction
     * @throws DAOException if unable to open data transaction
     */
    public abstract void beginTransaction() throws DAOException;

    /**
     * Commits transaction results and closes transaction
     * @throws DAOException if unable to commit data transaction
     */
    public abstract void commitTransaction() throws DAOException;

    /**
     * Rollbacks transaction results and closes transaction
     * @throws DAOException if unable to rollback transaction
     */
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
