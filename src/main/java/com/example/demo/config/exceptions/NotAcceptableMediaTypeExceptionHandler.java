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
    public String handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
        String xmlResponse = "<Map><status>406</status><message>Not Acceptable - Unsupported Media Type</message></Map>";

        XmlToJsonConverter converter = new XmlToJsonConverter();
        return converter.convert(xmlResponse);
    }
}