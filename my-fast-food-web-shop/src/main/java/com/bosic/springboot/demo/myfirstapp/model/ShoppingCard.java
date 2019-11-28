package com.bosic.springboot.demo.myfirstapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCard {

    private List<Product> productList = new ArrayList<>();
    private Customer customer = new Customer();
    private Date date;
    private Double total;

    public ShoppingCard(List<Product> productList, Customer customer, Date date, Double total) {
        this.productList = productList;
        this.customer = customer;
        this.date = date;
        this.total = total;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShoppingCard [productList=" + productList + ", customer=" + customer + ", date=" + date + ", total="
                + total + "]";
    }

}
