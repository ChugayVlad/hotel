package com.nure.controller.filter;

import com.nure.entity.Role;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SecurityFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(SecurityFilter.class);
    private Map<Role, List<String>> accessMap = new HashMap<>();

    private List<String> commons = new ArrayList<String>();
    private List<String> outOfControl = new ArrayList<String>();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();

        if (accessAllowed(request)) {
            LOG.debug("Filter finished");
            chain.doFilter(request, response);
        } else if (session.getAttribute("user") == null) {
            ((HttpServletResponse) response).sendRedirect(Path.COMMAND_LOGIN);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";
            request.setAttribute("errorMessage", errorMessage);
            LOG.trace("Set the request attribute: errorMessage --> " + errorMessage);

            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
                    .forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        Role userRole = (Role) session.getAttribute("userRole");
        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Security filter initialization starts");

        accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin")));
        accessMap.put(Role.CLIENT, asList(filterConfig.getInitParameter("client")));
        LOG.trace("Access map --> " + accessMap);

        commons = asList(filterConfig.getInitParameter("common"));
        LOG.trace("Common commands --> " + commons);

        outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
        LOG.trace("Out of control commands --> " + outOfControl);

        LOG.debug("Filter initialization finished");
    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
