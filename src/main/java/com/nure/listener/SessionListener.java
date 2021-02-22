package com.nure.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("currentLocale", DEFAULT_LOCALE);
    }
}
