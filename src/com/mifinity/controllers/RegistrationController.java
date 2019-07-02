package com.mifinity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.mifinity.db.Database;
import com.mifinity.entity.UserTypes;

@Controller
public class RegistrationController
{

	private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

	@Autowired
	public RegistrationController( InMemoryUserDetailsManager inMemoryUserDetailsManager )
	{
		this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
	}

	@RequestMapping("/register")
	public String registerPage()
	{
		return "register/register";
	}

	@RequestMapping("/processRegistration")
	public ModelAndView processRegistration( @RequestParam("username") String username, @RequestParam("password") String password, Model model ,RedirectAttributes redir)
	{
		if(!inMemoryUserDetailsManager.userExists( username ) )
		{
			inMemoryUserDetailsManager.createUser(User.withUsername(username).password(password).roles(UserTypes.CUSTOMER.name()).build());
			redir.addFlashAttribute("message", Database.getInstance().saveUser( username, password, UserTypes.CUSTOMER ));
		}
		else
		{
			redir.addFlashAttribute("message", "Username has already taken");
		}		
		return new ModelAndView(new RedirectView("/", true));
	}

}
