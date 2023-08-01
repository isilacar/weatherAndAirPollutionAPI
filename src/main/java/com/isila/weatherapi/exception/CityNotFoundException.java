package com.isila.weatherapi.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String message) {
        super(message);
    }
}
