package com.bosic.springboot.demo.myfirstapp.controller;

public class ProductNotFoundException extends RuntimeException {

    private ProductNotFoundException() {
    }

    @Override
    public String getMessage() {
        return "Product isn`t founded";
    }
}
