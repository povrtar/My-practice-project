package com.bosic.springboot.demo.myfirstapp.model;

public class Drink extends Product {

    public Drink() {
        super();
    }

    public Drink(String type, String size) {
        this.type = type;
    }

    public Drink(String type, String size, double price) {
        this.type = type;
        this.size = size;
        this.price = price;
    }
}
