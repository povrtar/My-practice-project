package com.bosic.springboot.demo.myfirstapp.model;

import org.springframework.stereotype.Component;

@Component
public class Product {

    protected String type;
    protected String size;
    protected double price;

    public Product() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product [type=" + type + ", price=" + price + "]";
    }

}
