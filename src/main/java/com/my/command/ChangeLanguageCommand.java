package com.my.command;

import com.my.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command{
    private static final Logger LOG = Logger.getLogger(ChangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        String locale = request.getParameter("locale");

        HttpSession session = request.getSession();

        session.setAttribute("currentLocale", locale);
        LOG.debug("Command finished");

        String prevPath = (String) session.getAttribute("prevPath");

        return prevPath;
    }
}
