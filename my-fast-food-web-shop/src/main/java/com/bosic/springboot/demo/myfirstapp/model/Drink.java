package com.bosic.springboot.demo.myfirstapp.model;

public class Drink extends Product {
    public Drink() {
    }

    public Drink(int id, String type, String size) {
        super(id, type, size);
        // TODO Auto-generated constructor stub
    }

    public Drink(int id, String type, String size, double price) {
        super(id, type, size);

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
