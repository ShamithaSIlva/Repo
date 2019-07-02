package com.mifinity.entity;

public class User{

	private String id;
	private String username;
	private String password;
	private UserTypes type;
	
	public User(String username, String password,UserTypes type) {
		this.username = username;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserTypes getType() {
		return type;
	}
	public void setType(UserTypes type) {
		this.type = type;
	}	
}
