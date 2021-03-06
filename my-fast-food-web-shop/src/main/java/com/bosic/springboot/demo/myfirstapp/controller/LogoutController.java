package com.bosic.springboot.demo.myfirstapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.bosic.springboot.demo.myfirstapp.model.Product;
import com.bosic.springboot.demo.myfirstapp.service.ProductService;
import com.bosic.springboot.demo.myfirstapp.service.ShoppingCartService;

@Controller
public class LogoutController {
    @Autowired
    private ProductService service;
    @Autowired
    private ShoppingCartService shoppingCartService;
    private List<Product> productList = new ArrayList<Product>();
    private Logger logger = LogManager.getLogger(LogoutController.class);

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submit(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model)
            throws Exception {
        String name = (String) session.getAttribute("name");
        logger.info("In submit customer name is" + name);
        if (name != null) {
            shoppingCartService.addShoppingCart(service.getProductList(name), name);
        }
        productList.clear();
        service.cleanProductList(name);
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String name = getLoggedInUserName(model);
        service.cleanProductList(name);
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
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