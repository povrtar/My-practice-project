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
    @ExceptionHandler({CustomerNotFoundException.class, IncompleteProductDetailsException.class})
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof CustomerNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            CustomerNotFoundException unfe = (CustomerNotFoundException) ex;
            return handleCustomerNotFoundException(unfe, headers, status, request);
        }
        if (ex instanceof ProductNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ProductNotFoundException pnfe = (ProductNotFoundException) ex;
            return handleProductNotFoundException(pnfe, headers, status, request);
        }
        if (ex instanceof IncompleteProductDetailsException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            IncompleteProductDetailsException ipde = (IncompleteProductDetailsException) ex;
            return handleIncompleteDetailsException(ipde, headers, status, request);
        }
        if (ex instanceof IncompleteCustomerDetailsException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            IncompleteCustomerDetailsException icde = (IncompleteCustomerDetailsException) ex;
            return handleIncompleteCustomerDetailsException(icde, headers, status, request);
        }

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    private ResponseEntity<ApiError> handleIncompleteCustomerDetailsException(IncompleteCustomerDetailsException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = Collections.singletonList(ex.getMessage());

        return handleExceptionInternal(ex, new ApiError(errorMessages), headers, status, request);
    }

    private ResponseEntity<ApiError> handleProductNotFoundException(ProductNotFoundException pnfe, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(pnfe.getMessage());
        return handleExceptionInternal(pnfe, new ApiError(errors), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleCustomerNotFoundException(CustomerNotFoundException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleIncompleteDetailsException(IncompleteProductDetailsException ex,
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