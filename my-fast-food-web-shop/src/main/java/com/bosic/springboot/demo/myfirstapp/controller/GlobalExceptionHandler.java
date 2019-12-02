package com.bosic.springboot.demo.myfirstapp.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
    /** Provides handling for exceptions throughout this service. */
    @ExceptionHandler({ObjectNotFoundException.class, IncompleteDetailsException.class})
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof ObjectNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ObjectNotFoundException unfe = (ObjectNotFoundException) ex;
            return handleCustomerNotFoundException(unfe, headers, status, request);
        }

        if (ex instanceof IncompleteDetailsException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            IncompleteDetailsException ide = (IncompleteDetailsException) ex;
            return handleIncompleteDetailsException(ide, headers, status, request);
        }

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<ApiError> handleCustomerNotFoundException(ObjectNotFoundException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleIncompleteDetailsException(IncompleteDetailsException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = Collections.singletonList(ex.getMessage());

        return handleExceptionInternal(ex, new ApiError(errorMessages), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}