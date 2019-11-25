package com.bosic.springboot.demo.myfirstapp.model;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    private int discountLev;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String password, String role, int discountLev) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.discountLev = discountLev;
    }

    public Customer(int id, String firstName, String lastName, String password, String role, int discountLev) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.discountLev = discountLev;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDiscountLev() {
        return discountLev;
    }

    public void setDiscountLev(int discountLev) {
        this.discountLev = discountLev;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
