package com.java.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProfileModel {
	private int id;
	
	@Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$") 
	private String email;
	
	@Size(min = 8, max =8)
	private String password;
	
	@Size(min = 8, max =8)
	private String newPassword;
	
	public ProfileModel() {}
	
	public ProfileModel(int id, String email,
			String password) {
		this.id =id;
		this.email =email ;
		this.password =password ;
	}
	public ProfileModel(int id, String email,
			String password,String newPassword) {
		this.id =id;
		this.email =email ;
		this.password =password ;
		this.newPassword = newPassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
