package com.mifinity.controllers;

import java.util.Map;
import java.util.stream.Collectors;

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

@Controller
@RequestMapping(value = "/cards")
public class CardController
{
//	@RequestMapping(value="/create", method=RequestMethod.GET)
//    public ModelAndView createCardPage() {
//        ModelAndView mav = new ModelAndView("cards/new-card");
//        mav.addObject("card", new Card());
//        return mav;
//    }

	@PostMapping(value = "/newcard", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CardJsonResponse createNewCard( @ModelAttribute @Valid Card card, BindingResult result )
	{
		CardJsonResponse respone = new CardJsonResponse();
		if ( result.hasErrors() )
		{
			Map<String, String> errors = result.getFieldErrors().stream().collect( Collectors.toMap( FieldError::getField, FieldError::getDefaultMessage ) );
			respone.setValidated( false );
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
}
