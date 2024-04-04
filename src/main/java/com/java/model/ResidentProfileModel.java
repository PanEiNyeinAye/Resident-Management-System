package com.java.model;

import java.sql.Date;

public class ResidentProfileModel {
	
	private String residentName;
	private String gender;
	private Date dob;
	private String nrcNo;
	private String fatherName;
	private String motherName;
	private String ethnicity;
	private String residentEmail;
	private String residentPassword;
	private String wardOrVillageName;
	private String townshipName;
	private String stateOrRegionName;
	private String crimeName;
	private boolean isCriminalResident;
	
	public ResidentProfileModel(String residentName,String gender,Date dob,String nrcNo,String fatherName,String motherName,String ethnicity, String residentEmail,String residentPassword,String wardOrVillageName,String townshipName,String stateOrRegionName,String crimeName,boolean isCriminalResident) {
	
		this.residentName = residentName;
		this.gender = gender;
		this.dob = dob;
		this.nrcNo = nrcNo;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.ethnicity = ethnicity;
		this.residentEmail = residentEmail;
		this.residentPassword = residentPassword;
		this.wardOrVillageName = wardOrVillageName;
		this.townshipName = townshipName;
		this.stateOrRegionName = stateOrRegionName;
		this.crimeName = crimeName;
		this.isCriminalResident = isCriminalResident;
	}
	
	public ResidentProfileModel(String residentName,String gender,Date dob,String nrcNo,String fatherName,String motherName,String ethnicity, String residentEmail,String residentPassword,String wardOrVillageName,String townshipName,String stateOrRegionName,boolean isCriminalResident) {
		this.residentName = residentName;
		this.gender = gender;
		this.dob = dob;
		this.nrcNo = nrcNo;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.ethnicity = ethnicity;
		this.residentEmail = residentEmail;
		this.residentPassword = residentPassword;
		this.wardOrVillageName = wardOrVillageName;
		this.townshipName = townshipName;
		this.stateOrRegionName = stateOrRegionName;
		this.isCriminalResident = isCriminalResident;
	}
	
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
	
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getResidentEmail() {
		return residentEmail;
	}
	public void setResidentEmail(String residentEmail) {
		this.residentEmail = residentEmail;
	}
	
	public String getResidentPassword() {
		return residentPassword;
	}
	public void setResidentPassword(String residentPassword) {
		this.residentPassword = residentPassword;
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
	public String getCrimeName() {
		return crimeName;
	}
	public void setCrimeName(String crimeName) {
		this.crimeName = crimeName;
	}
	public boolean getIsCriminalResident() {
		return isCriminalResident;
	}
	public void setIsCriminalResident(boolean isCriminalResident) {
		this.isCriminalResident = isCriminalResident;
	}
}
