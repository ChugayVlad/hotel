package com.my.command;

import com.my.command.util.PathUtil;
import com.my.controller.Direction;
import com.my.entity.Role;
import com.my.entity.User;
import com.my.exception.AppException;
import com.my.exception.ValidationException;
import com.my.service.UserService;
import com.my.service.impl.UserServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterClientCommand implements Command {
    private static final Logger LOG = Logger.getLogger(RegisterClientCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        PathUtil.saveCurrentPathToSession(request);
        if ("GET".equals(request.getMethod())) {
            return Path.PAGE_REGISTRATION;
        }

        UserService userService = new UserServiceImpl();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoleId(1);

        userService.insertUser(user);

        LOG.info("Register new user --> " + user);

        session.setAttribute("user", userService.findUser(email, password));
        LOG.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", Role.getRole(user));
        LOG.trace("Set the session attribute: userRole --> " + Role.getRole(user));

        LOG.info("User " + user + " logged as " + Role.getRole(user));
        LOG.debug("Command finished");
        return Path.COMMAND_SHOW_ROOMS;
    }
}
