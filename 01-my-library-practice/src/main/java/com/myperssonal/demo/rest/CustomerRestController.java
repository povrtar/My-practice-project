package com.myperssonal.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myperssonal.demo.entity.Customer;
import com.myperssonal.demo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        Customer theCustomer = customerService.getCustomer(customerId);
        if (theCustomer == null) {
            throw new EntityNotFoundException("Customer not founded for id: " + customerId);
        }
        return theCustomer;
    }

    @PostMapping("/service/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {
        theCustomer.setId(0);
        if (theCustomer.getFirstName() == null || theCustomer.getLastName() == null) {
            throw new RuntimeException("Customer must have first and last name!!!");
        }
        customerService.saveCustomer(theCustomer);
        return theCustomer;
    }

    @DeleteMapping("/service/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        Customer tempCustomer = customerService.getCustomer(customerId);
        if (tempCustomer == null) {
            throw new EntityNotFoundException("Customer not founded for id: " + customerId);
        }
        customerService.deleteCustomer(customerId);
        return "Deleted customer id-" + customerId;
    }

}