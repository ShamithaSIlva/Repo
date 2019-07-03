package com.mifinity.controllers;

import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mifinity.db.Database;
import com.mifinity.entity.CardSearchCriteria;
import com.mifinity.entity.SearchedCardsJsonResponse;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
 
	@RequestMapping("/home")
	public String adminHome() {
		return "admin/admin";
	}
	
	@PostMapping(value = "/searchcard", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public SearchedCardsJsonResponse searchCards( @ModelAttribute @Valid CardSearchCriteria searchCriteria, BindingResult result,Principal principal,HttpSession session )
	{
		SearchedCardsJsonResponse respone = new SearchedCardsJsonResponse();
		if ( result.hasErrors() )
		{
			Map<String, String> errors = result.getFieldErrors().stream().collect( Collectors.toMap( FieldError::getField, FieldError::getDefaultMessage ) );
			respone.setErrorMessages( errors );
		}
		else
		{			
			respone.setValidated( true );
			respone.setSearchedCards(Database.getInstance().getCardsForAdmin( searchCriteria ));
		}
		return respone;
	}
}