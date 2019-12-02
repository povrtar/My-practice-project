package com.bosic.springboot.demo.myfirstapp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bosic.springboot.demo.myfirstapp.controller.IncompleteDetailsException;
import com.bosic.springboot.demo.myfirstapp.controller.ObjectNotFoundException;
import com.bosic.springboot.demo.myfirstapp.model.Customer;

@Component
public class CustomerService {

    private static List<Customer> customerList = new ArrayList<>();

    static {
        customerList.add(new Customer("Mihajlo", "Bosic", "miha2810", "ADMIN", 3));
        customerList.add(new Customer("Dunja", "Bosic", "dunja1311", "USER", 10));
        customerList.add(new Customer("Dragana", "Bosic", "dragana1708", "USER", 20));
    }

    public void addCustomer(Customer customer) throws IncompleteDetailsException {
        if (!customerHasRequiredFields(customer)) {
            throw IncompleteDetailsException.createWith(customer.getClass()
                                                                .toString());
        }
        customerList.add(new Customer(customer.getFirstName(), customer.getLastName(), customer.getPassword(), "USER",
                customer.getDiscountLev()));
    }

    public Customer getCustomerByName(String name) throws ObjectNotFoundException {
        Iterator<Customer> iterator = customerList.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getFirstName()
                        .equals(name)) {
                return customer;
            }
        }
        ObjectNotFoundException.createWith(name);
        return null;
    }

    public List<Customer> getCustomers() {
        return customerList;
    }

    private boolean customerHasRequiredFields(Customer customer) {
        return (!(customer.getFirstName() == null || customer.getLastName() == null || customer.getPassword() == null));
    }
}
