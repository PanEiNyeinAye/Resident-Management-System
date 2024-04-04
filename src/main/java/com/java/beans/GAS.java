package com.java.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class GAS {
	private int gasId;
	private String gasName;
	private String gasEmployeeNo;
	private String gasPosition;
	@Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$") 
	private String gasEmail;
	@Size(min = 8, max =8)
	private String gasPassword;
	private int townshipId;
	private Township township;
	
	public GAS() {}
	
	public int getGasId() {
		return gasId;
	}
	public void setGasId(int gasId) {
		this.gasId = gasId;
	}
	
	public String getGasName() {
		return gasName;
	}
	public void setGasName(String gasName) {
		this.gasName = gasName;
	}
	
	public String getGasEmployeeNo() {
		return gasEmployeeNo;
	}
	public void setGasEmployeeNo(String gasEmployeeNo) {
		this.gasEmployeeNo = gasEmployeeNo;
	}
	
	public String getGasPosition() {
		return gasPosition;
	}
	public void setGasPosition(String gasPosition) {
		this.gasPosition = gasPosition;
	}
	
	public String getGasEmail() {
		return gasEmail;
	}
	public void setGasEmail(String gasEmail) {
		this.gasEmail = gasEmail;
	}
	
	public String getGasPassword() {
		return gasPassword;
	}
	public void setGasPassword(String gasPassword) {
		this.gasPassword = gasPassword;
	}
	
	public int getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(int townshipId) {
		this.townshipId = townshipId;
	}
	
	public Township getTownship() {
		return township;
	}
	public void setTownship(Township township) {
		this.township = township;
	}
}
