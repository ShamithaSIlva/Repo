package com.mifinity.controllers;

import java.security.Principal;
import java.util.HashMap;
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
import com.mifinity.entity.Card;
import com.mifinity.entity.CardJsonResponse;
import com.mifinity.entity.CardSearchCriteria;
import com.mifinity.entity.SearchedCardsJsonResponse;

@Controller
@RequestMapping(value = "/cards")
public class CardController
{
	@RequestMapping("/viewCards")
	public String viewCards() {
	    return "cards/viewCards";
	}

	@PostMapping(value = "/newcard", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CardJsonResponse createNewCard( @ModelAttribute @Valid Card card, BindingResult result,Principal principal,HttpSession session )
	{
		CardJsonResponse respone = new CardJsonResponse();
		card.setUsername( (String)session.getAttribute( "username" ) );
		if ( result.hasErrors())
		{
			Map<String, String> errors = result.getFieldErrors().stream().collect( Collectors.toMap( FieldError::getField, FieldError::getDefaultMessage ) );
			respone.setValidated( false );
			respone.setErrorMessages( errors );
		}
		else if(Database.getInstance().ifCardExist( card.getCardNumber(), card.getUsername() ))
		{
			Map<String, String> errors = new HashMap<String, String>();
			errors.put( "cardNumber", "This card has already inserted" );
			respone.setErrorMessages( errors );
		}
		else
		{
			Database.getInstance().saveCard( card );
			respone.setValidated( true );
			respone.setCard( card );
		}
		return respone;
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
			respone.setSearchedCards(Database.getInstance().getCardsForUser((String)session.getAttribute( "username" ), searchCriteria ));
		}
		return respone;
	}
	
}
