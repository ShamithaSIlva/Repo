package com.mifinity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
 
	@RequestMapping("/")
	public String loginPage(ModelMap model) {
	    return "login";
	}
}
