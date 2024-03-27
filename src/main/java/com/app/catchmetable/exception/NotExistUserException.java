package com.app.catchmetable.exception;

public class NotExistUserException extends RuntimeException{
    public NotExistUserException() {
    }

    public NotExistUserException(String message) {
        super(message);
    }
}
