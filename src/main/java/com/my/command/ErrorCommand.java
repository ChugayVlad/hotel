package com.my.command;

import com.my.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        request.setAttribute("errorMessage", request.getParameter("errorMessage"));
        return "WEB-INF/jsp/error.jsp";
    }
}