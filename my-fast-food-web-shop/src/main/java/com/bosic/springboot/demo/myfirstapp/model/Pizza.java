package com.bosic.springboot.demo.myfirstapp.model;

public class Pizza extends Product {

    private String size;

    public Pizza() {
    }

    public Pizza(String type, String size) {
        this.type = type;
        this.size = size;
    }

    public Pizza(String type, String size, double price) {
        this.type = type;
        this.size = size;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pizza [type=" + type + ", size=" + size + "]";
    }

}
