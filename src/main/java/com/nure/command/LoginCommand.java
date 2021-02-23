package com.nure.command;

import com.nure.command.util.PathUtil;
import com.nure.entity.Role;
import com.nure.entity.User;
import com.nure.exception.AppException;
import com.nure.service.UserService;
import com.nure.service.impl.UserServiceImpl;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        PathUtil.saveCurrentPathToSession(request);
        HttpSession session = request.getSession();

        if ("GET".equals(request.getMethod())) {
            return Path.PAGE_LOGIN;
        }

        UserService service = new UserServiceImpl();

        String email = request.getParameter("email");
        LOG.trace("Request parameter: email --> " + email);
        String password = request.getParameter("password");

        String path = null;
        User user = service.findUser(email, password);

        Role userRole = Role.getRole(user);

        LOG.trace("userRole --> " + userRole);

        if (userRole == Role.ADMIN) {
            path = Path.COMMAND_SHOW_ORDERS;
        } else {
            path = Path.COMMAND_SHOW_ROOMS;
        }

        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        LOG.debug("Command finished");
        return path;
    }
}
