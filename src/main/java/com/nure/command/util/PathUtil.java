package com.nure.command.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * Saving path of current page to session.
 *
 */
public class PathUtil {
    public static void saveCurrentPathToSession(HttpServletRequest request){
        HttpSession session = request.getSession();

        request.removeAttribute("message");

        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();

        if (queryString == null){
            session.setAttribute("prevPath", requestURI);
        } else {
            session.setAttribute("prevPath", requestURI + "?" + queryString);
        }

    }
}
