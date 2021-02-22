package com.nure.controller;

import com.nure.command.Command;
import com.nure.command.CommandContainer;
import com.nure.exception.AppException;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, Direction.FORWARD);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, Direction.REDIRECT);
    }

    private void process(HttpServletRequest request, HttpServletResponse response, Direction direction) throws ServletException, IOException {
        LOG.debug("Controller starts");

        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command --> " + command);
        String result = null;

        try {
            result = command.execute(request, response);
        } catch (AppException e) {
            if ("GET".equals(request.getMethod())) {
                request.setAttribute("errorMessage", e.getMessage());
            } else {
                result = Path.COMMAND_ERROR_PAGE + "&errorMessage=" + e.getMessage();
            }
        }

        LOG.trace("Direction --> " + direction);

        if (direction == Direction.FORWARD) {
            LOG.debug("Controller finished, now go to forward address --> " + result);
            request.getRequestDispatcher(result).forward(request, response);
        }
        if (direction == Direction.REDIRECT) {
            LOG.debug("Controller finished, now go to redirect address --> " + result);
            response.sendRedirect(result);
        }
    }
}
