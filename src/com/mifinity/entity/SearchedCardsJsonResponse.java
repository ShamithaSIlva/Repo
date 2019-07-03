package com.mifinity.entity;

import java.util.List;
import java.util.Map;

public class SearchedCardsJsonResponse
{
	private Map<String, String> errorMessages;
	private List<Card> searchedCards;
	private boolean validated;
	
	public Map<String, String> getErrorMessages()
	{
		return errorMessages;
	}
	public void setErrorMessages( Map<String, String> errorMessages )
	{
		this.errorMessages = errorMessages;
	}
	public List<Card> getSearchedCards()
	{
		return searchedCards;
	}
	public void setSearchedCards( List<Card> searchedCards )
	{
		this.searchedCards = searchedCards;
	}
	public boolean isValidated()
	{
		return validated;
	}
	public void setValidated( boolean validated )
	{
		this.validated = validated;
	}		
}
