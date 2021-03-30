package com.simplilearn.Phase1_lockme.model;

public class Users_db {
	
	String username;
	String password;
	
	public Users_db(String username,String password)
	{
		this.username=username;
		this.password=password;
	}
	
	
    public Users_db() {
		
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
}
