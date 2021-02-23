package com.nure.command;

import com.nure.entity.User;
import com.nure.exception.AppException;
import com.nure.exception.ValidationException;
import com.nure.service.UserService;
import com.nure.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserCommand implements Command {
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
        try {
            userService.update(user);
        } catch (ValidationException e){
            String message = "&message=" + e.getMessage();
            return session.getAttribute("prevPath") + message;
        }
        session.setAttribute("user", user);
        request.setAttribute("to", "info");
        LOG.debug("Command finished");
        return (String) session.getAttribute("prevPath");
    }
}
