package com.java.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PoliceOfficer {
	private int policeOfficerId;
	private String policeOfficerName;
	private String policeEmployeeNo;
	private String policeOfficerPosition;
	@Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$") 
	private String policeOfficerEmail;
	@Size(min = 8, max =8)
	private String policeOfficerPassword;
	private int townshipId;
	private Township township;
	
	public PoliceOfficer() {}
	
	public PoliceOfficer(String name) {
		policeOfficerName = name;
	}
	public int getPoliceOfficerId() {
		return policeOfficerId;
	}
	public void setPoliceOfficerId(int policeOfficerId) {
		this.policeOfficerId = policeOfficerId;
	}
	
	public String getPoliceOfficerName() {
		return policeOfficerName;
	}
	public void setPoliceOfficerName(String policeOfficerName) {
		this.policeOfficerName = policeOfficerName;
	}
	
	public String getPoliceEmployeeNo() {
		return policeEmployeeNo;
	}
	public void setPoliceEmployeeNo(String policeEmployeeNo) {
		this.policeEmployeeNo = policeEmployeeNo;
	}
	
	public String getPoliceOfficerPosition() {
		return policeOfficerPosition;
	}
	public void setPoliceOfficerPosition(String policeOfficerPosition) {
		this.policeOfficerPosition = policeOfficerPosition;
	}
	
	public String getPoliceOfficerEmail() {
		return policeOfficerEmail;
	}
	public void setPoliceOfficerEmail(String policeOfficerEmail) {
		this.policeOfficerEmail = policeOfficerEmail;
	}
	
	public String getPoliceOfficerPassword() {
		return policeOfficerPassword;
	}
	public void setPoliceOfficerPassword(String policeOfficerPassword) {
		this.policeOfficerPassword = policeOfficerPassword;
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
