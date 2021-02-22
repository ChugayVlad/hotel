package com.nure.listener;

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
