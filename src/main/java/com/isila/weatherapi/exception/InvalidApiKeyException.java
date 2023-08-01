package com.isila.weatherapi.exception;

public class InvalidApiKeyException extends RuntimeException {

    public InvalidApiKeyException(String messgae) {
        super(messgae);
    }
}
