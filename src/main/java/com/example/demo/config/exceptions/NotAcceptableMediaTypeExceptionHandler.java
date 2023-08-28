package com.example.demo.config.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class NotAcceptableMediaTypeExceptionHandler {

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    ResponseEntity<ErrorResponse> handleHttpMediaTypeNotAcceptableException() {
        ErrorResponse errorResponse = new ErrorResponse("406", "Not Acceptable - Unsupported Media Type");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(errorResponse, headers, HttpStatus.NOT_ACCEPTABLE);
    }
}
