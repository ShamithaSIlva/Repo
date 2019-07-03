package com.mifinity.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.mifinity.entity.UserTypes;

@Controller
public class LoginController
{

	private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

	@Autowired
	public LoginController( InMemoryUserDetailsManager inMemoryUserDetailsManager )
	{
		this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login( @RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes redir, HttpSession session )
	{
		ModelAndView view = null;
		session.setAttribute( "username", username );
		if ( inMemoryUserDetailsManager.userExists( username ) )
		{
			if ( inMemoryUserDetailsManager.loadUserByUsername( username ).getAuthorities().contains( new SimpleGrantedAuthority( "ROLE_" + UserTypes.CUSTOMER.name() ) ) )
			{
				view = new ModelAndView( new RedirectView( "/customer", true ) );
				redir.addFlashAttribute( "message", username );
			}
			else if ( inMemoryUserDetailsManager.loadUserByUsername( username ).getAuthorities().contains( new SimpleGrantedAuthority( "ROLE_" + UserTypes.ADMIN.name() ) ) )
			{
				view = new ModelAndView( new RedirectView( "/admin", true ) );
			}
		}
		else
		{
			view = new ModelAndView( new RedirectView( "/", true ) );
			redir.addFlashAttribute( "message", "User profile does not exist!" );
		}
		return view;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage( HttpServletRequest request, HttpServletResponse response )
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ( auth != null )
		{
			new SecurityContextLogoutHandler().logout( request, response, auth );
		}
		return "redirect:/";
	}

}
