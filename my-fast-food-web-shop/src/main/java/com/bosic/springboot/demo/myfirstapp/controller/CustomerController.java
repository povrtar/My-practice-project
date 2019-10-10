package com.bosic.springboot.demo.myfirstapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bosic.springboot.demo.myfirstapp.entity.Customer;
import com.bosic.springboot.demo.myfirstapp.food.Product;
import com.bosic.springboot.demo.myfirstapp.service.CustomerService;

@Controller
public class CustomerController {
@Autowired
CustomerService customerService;
@GetMapping("/addcustomer")
public String addCustomerForm(ModelMap model) {
model.addAttribute("customer",new Customer());
return "login";}
@PostMapping("/addcustomer")
public String addCustomer(ModelMap model, @Valid Customer customer, BindingResult result) {

customerService.addCustomer(customer);
model.addAttribute("customerList",customerService.getCustomers());
return "customer-list";}
}