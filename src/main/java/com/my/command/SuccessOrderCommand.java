package com.my.command;

import com.my.exception.AppException;
import com.my.util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessOrderCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        return Path.PAGE_ORDER_DONE;
    }
}
