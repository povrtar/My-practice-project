package com.bosic.springboot.demo.myfirstapp.controller;

public class IncompleteProductDetailsException extends RuntimeException {

    public IncompleteProductDetailsException() {
    }

    @Override
    public String getMessage() {
        return "Incomplete Product details !!!";
    }
}
