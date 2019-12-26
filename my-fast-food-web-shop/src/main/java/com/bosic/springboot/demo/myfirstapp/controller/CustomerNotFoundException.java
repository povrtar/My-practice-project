package com.bosic.springboot.demo.myfirstapp.controller;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
    }

    @Override
    public String getMessage() {
        return "Customer isn`t founded";
    }
}