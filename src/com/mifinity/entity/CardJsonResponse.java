package com.mifinity.entity;

import java.util.Map;

public class CardJsonResponse
{
	private Card card;
	private boolean validated;
	private Map<String, String> errorMessages;
	
	public Card getCard()
	{
		return card;
	}
	public void setCard( Card card )
	{
		this.card = card;
	}
	public boolean isValidated()
	{
		return validated;
	}
	public void setValidated( boolean validated )
	{
		this.validated = validated;
	}
	public Map<String, String> getErrorMessages()
	{
		return errorMessages;
	}
	public void setErrorMessages( Map<String, String> errorMessages )
	{
		this.errorMessages = errorMessages;
	}	
}
