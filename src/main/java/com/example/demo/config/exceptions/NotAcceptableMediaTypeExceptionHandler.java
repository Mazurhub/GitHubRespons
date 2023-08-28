package com.example.demo.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class NotAcceptableMediaTypeExceptionHandler {

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) throws Exception {
        ErrorResponse errorResponse = new ErrorResponse("406", "Not Acceptable - Unsupported Media Type");
        XmlToJsonConverter converter = new XmlToJsonConverter();
        return converter.convertToJson(errorResponse);
    }
}




