package com.simplilearn.Phase1_lockme.model;

public class UserCredentials_lockerdb {
	
	String sitename;
	String username;
	String password;
	String signinuser;
	
	public UserCredentials_lockerdb(String sitename,String username,String password,String signinuser )
	{
		this.sitename=sitename;
		this.username=username;
		this.password=password;
		this.signinuser=signinuser;
		
	}

	public String getSigninuser() {
		return signinuser;
	}

	public void setSigninuser(String signinuser) {
		this.signinuser = signinuser;
	}

	public UserCredentials_lockerdb() {
		
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
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
