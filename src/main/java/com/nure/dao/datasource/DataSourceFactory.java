package com.nure.dao.datasource;

import com.nure.exception.DAOException;
import com.nure.exception.Messages;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceFactory {
    private static final Logger LOG = Logger.getLogger(DataSourceFactory.class);
    private static DataSourceFactory instance;
    private DataSource ds;
    private static DatasourceType currentDs;


    private DataSourceFactory(DatasourceType dsType) throws DAOException {
        switch (dsType) {
            case MY_SQL:
                try {
                    Context initContext = new InitialContext();
                    Context envContext = (Context) initContext.lookup("java:/comp/env");
                    ds = (DataSource) envContext.lookup("jdbc/hotelDB");
                    currentDs = dsType;
                    LOG.trace("Data source ==> " + ds);
                } catch (NamingException ex) {
                    LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
                    throw new IllegalStateException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
                }
                break;
            case ORACLE:
                LOG.error("Database" + dsType + "not supported yet");
                throw new DAOException("Database " + dsType + " not supported yet");

            default:
                throw new DAOException("Database " + dsType + " not supported yet");
        }
    }

    public static synchronized DataSourceFactory getInstance(DatasourceType dsType) throws DAOException {
        if (instance == null && !dsType.equals(currentDs)) {
            instance = new DataSourceFactory(dsType);
        }
        return instance;
    }

    public DataSource getDatasource(){
        return ds;
    }
}
