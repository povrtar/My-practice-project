package com.bosic.springboot.demo.myfirstapp.controller;

public class IncompleteDetailsException extends RuntimeException {
    private String type;

    private IncompleteDetailsException(String type) {
        this.type = type;
    }

    public static IncompleteDetailsException createWith(String type) {
        return new IncompleteDetailsException(type);
    }

    @Override
    public String getMessage() {
        return "Incomplete " + type + " details !!!";
    }
}
