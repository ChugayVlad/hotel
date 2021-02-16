package com.my.listener;

import com.my.dao.datasource.DataSourceFactory;
import com.my.dao.datasource.DatasourceType;
import com.my.exception.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ContextListener implements ServletContextListener {
    private static Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DataSourceFactory.getInstance(DatasourceType.MY_SQL).getDatasource();
        } catch (DAOException e) {
            LOG.error("Cannot obtain datasource", e);
        }

        ServletContext context = sce.getServletContext();
        String localesFileName = context.getInitParameter("locales");

        String localesFileRealPath = context.getRealPath(localesFileName);
        LOG.trace("localesFileName -->> " + localesFileName);
        LOG.trace("localesFileRealPath -->> " + localesFileRealPath);
        Properties locales = new Properties();
        try {
            locales.load(new FileInputStream(localesFileRealPath));
        } catch (IOException e) {
            LOG.error("Can't load resources");
        }

        context.setAttribute("locales", locales);
    }
}
