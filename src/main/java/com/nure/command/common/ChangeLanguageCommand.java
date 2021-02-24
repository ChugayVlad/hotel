package com.nure.command.common;

import com.nure.command.Command;
import com.nure.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Change language command.
 *
 */
public class ChangeLanguageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(ChangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        String locale = request.getParameter("locale");
        HttpSession session = request.getSession();
        session.setAttribute("currentLocale", locale);
        LOG.debug("Command finished");
        return (String) session.getAttribute("prevPath");
    }
}
