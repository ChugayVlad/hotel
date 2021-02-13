package com.my.listener;

import com.my.dao.datasource.DataSourceFactory;
import com.my.dao.datasource.DatasourceType;
import com.my.exception.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DataSourceFactory.getInstance(DatasourceType.MY_SQL).getDatasource();
        } catch (DAOException e) {
            LOG.error("Cannot obtain datasource", e);
        }
    }
}
