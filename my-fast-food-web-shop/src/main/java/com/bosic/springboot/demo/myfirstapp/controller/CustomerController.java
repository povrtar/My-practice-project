package com.bosic.springboot.demo.myfirstapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.bosic.springboot.demo.myfirstapp.model.Customer;
import com.bosic.springboot.demo.myfirstapp.service.CustomerService;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String getCustomers(ModelMap model) {
        model.addAttribute("customer", new Customer());
        return "login";
    }

    @PostMapping("/customers")
    public String addCustomer(ModelMap model, @Valid Customer customer, BindingResult result) {
        if (customerIsWithoutNullField(customer)) {
            customer.setId(0);
            customerService.addCustomer(customer);
            model.addAttribute("customerList", customerService.getCustomers());
            return "customer-list";
        } else
            throw new RuntimeException("Incomplete Customer details");
    }

    @PutMapping("/customers")
    public String updateCustomer(ModelMap model, @Valid Customer customer, BindingResult result) {
        if (customerIsWithoutNullField(customer)) {
            customerService.addCustomer(customer);
            model.addAttribute("customerList", customerService.getCustomers());
            return "customer-list";
        } else
            throw new RuntimeException("Incomplete Customer details");
    }

    private boolean customerIsWithoutNullField(Customer customer) {
        if ((customer.getFirstName() == null || customer.getLastName() == null || customer.getPassword() == null)) {
            return false;
        } else {
            return true;
        }

    }
}