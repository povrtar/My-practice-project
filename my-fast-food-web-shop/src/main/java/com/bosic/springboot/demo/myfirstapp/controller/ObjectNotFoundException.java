package com.bosic.springboot.demo.myfirstapp.controller;

public class ObjectNotFoundException extends RuntimeException {

    private String className;

    private ObjectNotFoundException(String className) {
        this.className = className;
    }

    public static ObjectNotFoundException createWith(String className) {
        return new ObjectNotFoundException(className);
    }

    @Override
    public String getMessage() {
        return "Object '" + className + "' not found";
    }
}
