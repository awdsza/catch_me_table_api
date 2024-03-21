package com.app.catchmetable.exception;

public class DuplicateUserException extends  RuntimeException{
    public DuplicateUserException(String message) {
        super(message);
    }
}
