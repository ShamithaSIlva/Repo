package com.mifinity.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mifinity.entity.Card;
import com.mifinity.entity.CardSearchCriteria;
import com.mifinity.entity.User;
import com.mifinity.entity.UserTypes;

public class Database {
	
	private Map<String,List<Card>> userCards = new HashMap(); 
    private Map<String,User> users = new HashMap(); 
    private static Database database;
    
    private Database()
    {
    	
    }
    
    public static Database getInstance()
    {
    	if(database ==  null)
    	{
    		database = new Database();
    	}
    	return database;
    }
    
    public String saveUser(String username, String password,UserTypes userType)
    {
    	String message = "";
    	if(users.containsKey(username))
    	{
    		message =  "Username has already taken";
    	}
    	else
    	{
    		users.put(username, new User(username,password,userType));
    		message = "User saved successfully";
    	}
    	return message;
    }
        
    public User getUser(String username)
    {
    	return users.get(username);
    }
    
    public String saveCard(Card card)
    {
    	if(userCards.containsKey( card.getUsername() ))
    	{
    		userCards.get( card.getUsername() ).add( card );
    	}
    	else
    	{
    		List<Card> cards = new ArrayList<Card>();
    		cards.add( card );
    		userCards.put( card.getUsername(), cards );
    	}    	
    	return "success";
    }
    
    public String updateCard(Card card)
    {
    	if(userCards.containsKey( card.getUsername() ))
    	{
    		for(Card cardInt : userCards.get( card.getUsername() ))
        	{
        		if(card.getCardNumber().equals( cardInt.getCardNumber() ))
        		{
        			cardInt.setExpDate( card.getExpDate() );
        		}
        	}
    	}    	   	
    	return "success";
    }
    
    public List<Card> getCardsForUser(String username, CardSearchCriteria searchCriteria)
    {
    	List<Card> searchedCards = new ArrayList<Card>();
    	for(Card card : userCards.get( username ))
    	{
    		if(card.getCardNumber().contains( searchCriteria.getCardNumber() ))
    		{
    			searchedCards.add( card );
    		}
    	}
    	return searchedCards;
    }
    
    public boolean ifCardExist(String cardNumber,String username)
    {
    	if(userCards.get( username ) != null)
    	{
    		for(Card card : userCards.get( username ))
        	{
        		if(card.getCardNumber().equals( cardNumber ))
        		{
        			return true;
        		}
        	}
    	}    	
    	return false;
    }
}
