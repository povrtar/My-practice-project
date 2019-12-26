package com.myperssonal.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myperssonal.demo.entity.Customer;
import com.myperssonal.demo.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        Customer theCustomer = customerService.getCustomer(customerId);
        if (theCustomer == null) {
            throw new EntityNotFoundException("Customer not founded for id: " + customerId);
        }
        return theCustomer;
    }

}