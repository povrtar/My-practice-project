package com.bosic.springboot.demo.myfirstapp.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bosic.springboot.demo.myfirstapp.service.ShoppingCartService;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    Calendar date = Calendar.getInstance();

    @GetMapping("/managerPage")
    public String getManagerData(Model model) {
        model.addAttribute("dailyTotal", shoppingCartService.getDailyTotal(date));
        model.addAttribute("dailyPizzas", shoppingCartService.howManyPizzasForDate(date));
        return "manager-page";
    }
}