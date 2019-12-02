package com.bosic.springboot.demo.myfirstapp.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShoppingCart {

    private List<Product> productList = new ArrayList<>();
    private Customer customer = new Customer();
    private Calendar date;
    private BigDecimal total;

    public ShoppingCart(List<Product> productList, Customer customer, Calendar calendar, BigDecimal total) {
        this.productList = productList;
        this.customer = customer;
        this.date = calendar;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShoppingCart [productList=" + productList + ", customer=" + customer + ", date=" + date + ", total="
                + total + "]";
    }

}
