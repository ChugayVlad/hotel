package com.nure.command;

import com.nure.exception.AppException;
import com.nure.util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessOrderCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        return Path.PAGE_ORDER_DONE;
    }
}
