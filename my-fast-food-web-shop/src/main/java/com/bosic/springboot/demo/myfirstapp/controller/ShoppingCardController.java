package com.bosic.springboot.demo.myfirstapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bosic.springboot.demo.myfirstapp.service.ShoppingCardService;

@Controller
public class ShoppingCardController {

	@Autowired
	private ShoppingCardService shoppingCardService;

	String date = ShoppingCardService.getCurrentTimeStamp();

	@GetMapping("/managerPage")
	public String getManagerData(Model model) {
		model.addAttribute("dailyTotal", shoppingCardService.getDailyTotal(date));
		model.addAttribute("dailyPizzas", shoppingCardService.howManyPizzasForDate(date));
		return "manager-page";
	}
}