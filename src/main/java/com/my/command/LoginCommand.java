package com.my.command;

import com.my.entity.Role;
import com.my.entity.User;
import com.my.exception.AppException;
import com.my.exception.ServiceException;
import com.my.service.UserService;
import com.my.service.impl.UserServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();

        if(session.getAttribute("user") != null){
            return Path.COMMAND_SHOW_ROOMS;
        }

        if ("GET".equals(request.getMethod())){
            return Path.PAGE_LOGIN;
        }

        UserService service = new UserServiceImpl();


        String email = request.getParameter("email");
        LOG.trace("Request parameter: email --> " + email);
        String password = request.getParameter("password");

        //String forward = Path.COMMAND_ERROR_PAGE;
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
