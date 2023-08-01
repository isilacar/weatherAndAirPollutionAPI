package com.isila.weatherapi.exception;

import com.isila.weatherapi.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class WeatherApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidApiKeyException.class)
    public ResponseEntity<ErrorResponse> invalidApiKey(InvalidApiKeyException invalidApiKeyException) {
        ErrorResponse errorResponse = new ErrorResponse(invalidApiKeyException.getMessage(),
                HttpStatus.UNAUTHORIZED, getDate());

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(WrongLatitudeException.class)
    public ResponseEntity<ErrorResponse> wrongLatitude(WrongLatitudeException wrongLatitudeException) {
        ErrorResponse errorResponse = new ErrorResponse(wrongLatitudeException.getMessage(),
                HttpStatus.BAD_REQUEST, getDate());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorResponse> cityNotFound(CityNotFoundException cityNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(cityNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND, getDate());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorResponse> countryNotFound(CountryNotFoundException countryNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(countryNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND, getDate());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badResponse(BadRequestException badRequest) {
        ErrorResponse errorResponse = new ErrorResponse(badRequest.getMessage(), HttpStatus.BAD_REQUEST, getDate());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    //It is for handling the Validation Exceptions Globally
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error messages", errors);
        body.put("httpStatus", status);
        body.put("date", getDate());

        return new ResponseEntity<>(body, status);

    }

    private String getDate() {
        String pattern = "HH:mm:ss dd.MM.YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());

    }
}
