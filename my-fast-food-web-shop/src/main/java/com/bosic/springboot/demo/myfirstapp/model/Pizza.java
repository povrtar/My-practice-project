package com.bosic.springboot.demo.myfirstapp.model;

public class Pizza extends Product {
    private String size;

    public Pizza() {
    }

    public Pizza(String type, String size) {
        super();
    }

    public Pizza(String type, String size, double price) {
        super();
    }

    @Override
    public String toString() {
        return "Pizza [type=" + type + ", size=" + size + "]";
    }

}
