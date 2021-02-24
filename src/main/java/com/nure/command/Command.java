package com.nure.command;

import com.nure.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Main interface for the Command pattern implementation.
 *
 */
public interface Command {

    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws AppException;
}
