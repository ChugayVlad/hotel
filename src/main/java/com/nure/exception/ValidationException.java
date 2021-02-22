package com.nure.exception;


public class ValidationException extends AppException{

    public ValidationException() {
        super();
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message){
        super(message);
    }
}