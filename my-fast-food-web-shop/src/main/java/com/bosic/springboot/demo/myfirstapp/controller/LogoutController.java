package com.bosic.springboot.demo.myfirstapp.controller;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.bosic.springboot.demo.myfirstapp.service.ProductService;
import com.bosic.springboot.demo.myfirstapp.service.ShoppingCardService;


@Controller

public class LogoutController {
	@Autowired
	ProductService service;
	@Autowired
	ShoppingCardService shoppingCard;
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response,ModelMap model) {
		String name = getLoggedInUserName(model);
		System.out.println("User is "+name);
		shoppingCard.saveCard(service.getProductList(), name);
		
		
	
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response,
					authentication);
		}

		return "redirect:/";
	}
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}
}