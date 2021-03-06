package com.nure.command.common;

import com.nure.command.Command;
import com.nure.exception.AppException;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout commands.
 *
 */
public class LogoutCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        if (session != null){
            session.invalidate();
        }
        LOG.debug("Command finished");
        return Path.COMMAND_LOGIN;
    }
}
