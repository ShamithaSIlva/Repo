package com.mifinity.entity;

import org.hibernate.validator.constraints.NotEmpty;

public class CardSearchCriteria
{
	@NotEmpty()
	private String cardNumber;

	public String getCardNumber()
	{
		return cardNumber;
	}

	public void setCardNumber( String cardNumber )
	{
		this.cardNumber = cardNumber;
	}	
}
