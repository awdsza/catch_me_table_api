package com.app.catchmetable.exception;

public class DuplicateRestaurantNumberException extends  RuntimeException{
    public DuplicateRestaurantNumberException(String message) {
        super(message);
    }
}
