package com.bosic.springboot.demo.myfirstapp.model;

public class Product {

    private int id;
    private String type;
    private String size;
    private double price;

    public Product() {
    }

    public Product(int id, String type, String size) {
        super();
        this.type = type;
        this.size = size;
    }

    public Product(int id, String type, String size, double price) {
        super();
        this.type = type;
        this.size = size;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", type=" + type + ", size=" + size + ", price=" + price + "]";
    }

}
