package com.nure.exception;

public class ServiceException extends AppException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message){
        super(message);
    }
}
