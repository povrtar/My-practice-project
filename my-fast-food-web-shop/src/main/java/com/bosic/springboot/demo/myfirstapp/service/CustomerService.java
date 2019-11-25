package com.bosic.springboot.demo.myfirstapp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bosic.springboot.demo.myfirstapp.model.Customer;

@Component
public class CustomerService {

    private static int counter = 1;
    private static List<Customer> customerList = new ArrayList<>();

    static {
        customerList.add(new Customer(1, "Mihajlo", "Bosic", "miha2810", "ADMIN", 3));
        customerList.add(new Customer(2, "Dunja", "Bosic", "dunja1311", "USER", 10));
        customerList.add(new Customer(3, "Dragana", "Bosic", "dragana1708", "USER", 20));
    }

    public void addCustomer(Customer customer) {
        customerList.add(new Customer(counter++, customer.getFirstName(), customer.getLastName(),
                customer.getPassword(), "USER", customer.getDiscountLev()));
    }

    public Customer getCustomerByName(String name) {
        Iterator<Customer> iterator = customerList.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getFirstName()
                        .equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> getCustomers() {
        return customerList;
    }
}
