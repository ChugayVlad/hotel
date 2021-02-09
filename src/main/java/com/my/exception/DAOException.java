package com.my.exception;

public class DAOException extends AppException {
    public DAOException() {
        super();
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message){
        super(message);
    }
}
