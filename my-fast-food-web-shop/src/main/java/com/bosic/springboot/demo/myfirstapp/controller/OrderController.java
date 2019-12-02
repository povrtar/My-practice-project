package com.bosic.springboot.demo.myfirstapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bosic.springboot.demo.myfirstapp.model.Drink;
import com.bosic.springboot.demo.myfirstapp.model.Pizza;
import com.bosic.springboot.demo.myfirstapp.service.ProductService;

@Controller
public class OrderController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String logControl(ModelMap model) {
        model.addAttribute("name", getLoggedInUserName(model));
        return "welcome";
    }

    @GetMapping("/orders")
    public String mainOrderPage(ModelMap model) {
        String name = getLoggedInUserName(model);
        model.put("productList", service.getProductList());
        model.put("total", service.getTotal());
        model.put("name", name);
        return "order-page";
    }

    @GetMapping("/add-order/pizzas")
    public String addPizza(ModelMap model) {
        model.addAttribute("product", new Pizza());
        return "pizza";
    }

    @PostMapping("/add-order/pizzas")
    public String inputPizza(ModelMap model, @Valid Pizza pizza, BindingResult result) {
        if (result.hasErrors())
            return ("redirect:/pizza");
        service.addProduct(pizza);
        return "redirect:/orders";
    }

    @GetMapping("/add-order/drinks")
    public String addDrink(ModelMap model) {
        model.addAttribute("product", new Drink());
        return "drink";
    }

    @PostMapping("/add-order/drinks")
    public String inputDrink(ModelMap model, @Valid Drink drink, BindingResult result) {
        if (result.hasErrors()) {
            return ("redirect:/drink");
        }
        service.addProduct(drink);
        return "redirect:/orders";
    }

    @PostMapping("/delete-prod")
    public String deleteProduct(@RequestParam int id) {
        service.deleteProduct(id);
        return "redirect:/orders";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext()
                                                .getAuthentication()
                                                .getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }
}