package com.bosic.springboot.demo.myfirstapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WellcomeController {
    @GetMapping("/")
    public String logControl(ModelMap model, HttpSession session) {

        model.addAttribute("name", OrderController.getLoggedInUserName(model));
        session.setAttribute("name", OrderController.getLoggedInUserName(model));
        return "welcome";
    }
}
