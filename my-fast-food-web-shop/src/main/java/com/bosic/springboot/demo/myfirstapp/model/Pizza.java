package com.bosic.springboot.demo.myfirstapp.model;

public class Pizza extends Product {

    public Pizza() {
    }

    public Pizza(int id, String type, String size) {
        super(id, type, size);
    }

    public Pizza(int id, String type, String size, String type2, String size2, double price) {
        super(id, type, size);
        type = type2;
        size = size2;
        this.price = price;
    }

    private String type;
    private String size;
    private double price;

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Pizza [type=" + type + ", size=" + size + "]";
    }

}
