package com.java.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class LetterModel {
	
	private String residentName;
	private String gender;
	private Date dob;
	private String nrcNo;
	private String fatherName;
	private String wardOrVillageName;
	private String townshipName;
	private String stateOrRegionName;
	private String wardASName;
	private String policeOfficerName;
	
	public LetterModel() {}
	
	public String getResidentName() {
		return residentName;
	}
	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
			
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getNrcNo() {
		return nrcNo;
	}
	public void setNrcNo(String nrcNo) {
		this.nrcNo = nrcNo;
	}
	
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	public String getWardOrVillageName() {
		return wardOrVillageName;
	}
	public void setWardOrVillageName(String wardOrVillageName) {
		this.wardOrVillageName = wardOrVillageName;
	}
	public String getTownshipName() {
		return townshipName;
	}
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}
	public String getStateOrRegionName() {
		return stateOrRegionName;
	}
	public void setStateOrRegionName(String stateOrRegionName) {
		this.stateOrRegionName = stateOrRegionName;
	}
	public String getWardASName() {
		return wardASName;
	}
	public void setWardASName(String wardASName) {
		this.wardASName = wardASName;
	}
	public String getPoliceOfficerName() {
		return policeOfficerName;
	}
	public void setPoliceOfficerName(String policeOfficerName) {
		this.policeOfficerName = policeOfficerName;
	}
	public int calculateAge(LocalDate dob)   
	{ 
		LocalDate curDate = LocalDate.now();
		if ((dob != null) && (curDate != null))   
		{  
			return Period.between(dob, curDate).getYears();  
		}  
		else  
		{  
			return 0;  
		}  
	} 
}
