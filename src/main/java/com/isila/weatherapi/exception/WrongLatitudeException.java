package com.isila.weatherapi.exception;

public class WrongLatitudeException extends RuntimeException {

    public WrongLatitudeException(String message) {
        super(message);
    }
}
