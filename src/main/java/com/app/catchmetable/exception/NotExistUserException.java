package com.app.catchmetable.exception;

public class NotExistRestaurantException extends RuntimeException{
    public NotExistRestaurantException() {
    }

    public NotExistRestaurantException(String message) {
        super(message);
    }
}
