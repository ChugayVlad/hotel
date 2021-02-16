package com.my.command.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PathUtil {
    public static void saveCurrentPathToSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();

        if (queryString == null){
            session.setAttribute("prevPath", requestURI);
        } else {
            session.setAttribute("prevPath", requestURI + "?" + queryString);
        }

    }
}
