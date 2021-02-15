package com.my.command;

import com.my.entity.User;
import com.my.exception.AppException;
import com.my.service.UserService;
import com.my.service.impl.UserServiceImpl;
import com.my.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserCommand implements Command{
    private static final Logger LOG = Logger.getLogger(EditUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        User user = (User) session.getAttribute("user");
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userService.update(user);

        session.setAttribute("user", user);
        request.setAttribute("to", "info");
        LOG.debug("Command finished");
        return Path.COMMAND_OPEN_PERSONAL_ACCOUNT;
    }
}
