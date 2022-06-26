package com.mobiquity.bean;

public class User {
	private String name, email, mobile, adminlevel, newpassword;
	
	public void setUserName( String name) {
		this.name=name;
		
	}
	public String getUserName() {
		return name;
	}
	
	public void setEmail( String email) {
		this.email=email;
		
	}
	public String getEmail() {
		return email;
	}
	
	public void setMobile( String mobile) {
		this.mobile=mobile;
		
	}
	public String getMobile() {
		return mobile;
	}
	
	public void setLevel( String lvl) {
		this.adminlevel=lvl;
		
	}
	public String getLevel() {
		return adminlevel;
	}
	
	public void setPassword( String pass) {
		this.newpassword=pass;
		
	}
	public String getPassword() {
		return newpassword;
	}

}
