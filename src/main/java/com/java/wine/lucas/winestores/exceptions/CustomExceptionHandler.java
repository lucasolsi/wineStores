package com.java.wine.lucas.winestores.exceptions;

import com.java.wine.lucas.winestores.model.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler
{
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getContextPath());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = StoreNotFoundException.class)
    public ResponseEntity<Object> handleStoreNotFoundException(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getContextPath());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FaixaAlreadyExistsException.class)
    public ResponseEntity<Object> handleFaixaAlreadyExistsException(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getContextPath());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = StoreAlreadyExistsException.class)
    public ResponseEntity<Object> handleStoreAlreadyExistsException(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getContextPath());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
