package com.mifinity.entity;

import javax.validation.constraints.Pattern;

public class Card 
{
	private int id;
	
	@Pattern(regexp = "^((?:[A-Za-z]+ ?){1,3})$",message="Please insert a valid name")
	private String cardHoldername;
	
	@Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}",message="Card number should be in xxxx-xxxx-xxxx-xxxx format")
	private String cardNumber;
	
	@Pattern(regexp = "((0[1-9])|(1[02]))/\\d{2}",message="Should be in the format MM/YY")
	private String expDate;
	
	private String username;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardHoldername() {
		return cardHoldername;
	}
	public void setCardHoldername(String cardHoldername) {
		this.cardHoldername = cardHoldername;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String date) {
		this.expDate = date;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername( String username )
	{
		this.username = username;
	}	
}
