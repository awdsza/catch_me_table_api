package com.app.catchmetable.exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException() {
    }

    public LoginFailException(String message) {
        super(message);
    }
}
