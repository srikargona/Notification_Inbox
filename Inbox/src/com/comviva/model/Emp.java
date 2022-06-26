package com.comviva.model;  
  
public class Emp 
{  
private String username;
private String password;
private int adminid;

private String name;
private String mobile;
private String email;
private String newpassword;
private String adminlevel;

public int getAdminid() {
	return adminid;
}
public void setAdminid(int adminid) {
	this.adminid = adminid;
}
public String getAdminlevel() {
	return adminlevel;
}
public void setAdminlevel(String adminlevel) {
	this.adminlevel = adminlevel;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getNewpassword() {
	return newpassword;
}
public void setNewpassword(String newpassword) {
	this.newpassword = newpassword;
}
public String getUsername() 
{
	return username;
}
public void setUsername(String username) 
{
	this.username = username;
}
public String getPassword() 
{
	return password;
}
public void setPassword(String password) 
{
	this.password = password;
}

  
}  