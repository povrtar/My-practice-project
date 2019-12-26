package com.bosic.springboot.demo.myfirstapp.controller;

public class IncompleteCustomerDetailsException extends RuntimeException {

    private IncompleteCustomerDetailsException() {
    }

    @Override
    public String getMessage() {
        return "Incomplete Customer details !!!";
    }
}
