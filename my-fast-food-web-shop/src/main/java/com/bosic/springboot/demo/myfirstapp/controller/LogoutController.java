package com.bosic.springboot.demo.myfirstapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
	private ProductService service;
	@Autowired
	private ShoppingCardService shoppingCardService;
	
	private Logger logger=LogManager.getLogger(LogoutController.class);

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String name = getLoggedInUserName(model);
		
		logger.info("User is " + name);
		shoppingCardService.addCard(service.getProductList(), name);
		logger.info("Total = "+shoppingCardService.getTotal(service.getProductList()));
		service.cleanProductList();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
}