package com.bosic.springboot.demo.myfirstapp.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.bosic.springboot.demo.myfirstapp.entity.Customer;
import com.bosic.springboot.demo.myfirstapp.food.Drink;

import com.bosic.springboot.demo.myfirstapp.food.Pizza;
import com.bosic.springboot.demo.myfirstapp.food.Product;
import com.bosic.springboot.demo.myfirstapp.service.CustomerService;

import com.bosic.springboot.demo.myfirstapp.service.ProductService;




@Controller

public class OrderController {
	private static double total=0;
	@Autowired
	Environment env;
@Autowired
ProductService service;
@Autowired
CustomerService customer;

	@GetMapping("/")
	public String logControl(ModelMap model) {
		model.addAttribute("name", getLoggedInUserName(model));
		return "welcome";
	}
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}
	@GetMapping("/list-of-food")
	
	public String mainOrderPage(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("productList",service.getProductList());
		model.put("total", total);
		model.put("name", name);
		
		
		return "order-page";
	}
@GetMapping("/add-pizza")
public String addHamburger(ModelMap model) {
	model.addAttribute("product", new Pizza());
	
	return "pizza";
}
@PostMapping("/add-pizza")
public String inputHam(ModelMap model, @Valid Product pizza, BindingResult result) {
	if(pizza.getSize().equals("M")){pizza.setPrice(Double.parseDouble(env.getProperty("pizzaM")));};
	if(pizza.getSize().equals("L")){pizza.setPrice(Double.parseDouble(env.getProperty("pizzaL")));};
	if(pizza.getSize().equals("XL")){pizza.setPrice(Double.parseDouble(env.getProperty("pizzaXL")));};
	service.addProduct(pizza);;
	total=total+(pizza.getPrice());
	
	return "redirect:/list-of-food";
}

@GetMapping("/add-drink")
public String addDrink(ModelMap model) {
model.addAttribute("product", new Drink());

return "drink";
}
@PostMapping("/add-drink")
public String inputDrink(ModelMap model, @Valid Product drink, BindingResult result) {
	if(result.hasErrors()) {
		return("drink");
	}
	drink.setPrice(Double.parseDouble(env.getProperty("drink")));
	service.addProduct(drink);
	
	total=total+(drink.getPrice());
	
	return "redirect:/list-of-food";
}
@GetMapping("/delete-prod")
public String deleteProd(@RequestParam int id) {
	if(service.getProdById(id).equals(null))throw new RuntimeException(
		"Something went wrong!!!");
	total=total-service.getProdById(id).getPrice();
	service.deleteProd(id);
	return "redirect:/list-of-food";
}

}