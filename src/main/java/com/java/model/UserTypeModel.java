package com.java.model;

public class UserTypeModel {
	private String userType;
	private String email;
	private String password;
	
	public void setUserType(String userType){
		this.userType = userType;
	}
	public String getUserType(){
		return userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
