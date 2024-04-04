package com.java.model;

import java.sql.Date;

public class SearchedResidentModel {
	private int residentId;
	private String residentName;
	private String gender;
	private Date dob;
	private String nrcNo;
	private String fatherName;
	private String motherName;
	private String ethnicity;
	private String wardOrVillageName;
	private String crimeName;
	private boolean isCriminalResident;
	
	public SearchedResidentModel(int residentId,String residentName,String gender,Date dob,String nrcNo,String fatherName,String motherName,String ethnicity,String wardOrVillageName,boolean isCriminalResident) {
		this.residentId = residentId;
		this.residentName = residentName;
		this.gender = gender;
		this.dob = dob;
		this.nrcNo = nrcNo;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.ethnicity = ethnicity;
		this.wardOrVillageName = wardOrVillageName;
		this.isCriminalResident = isCriminalResident;
	}
	
	public SearchedResidentModel(int residentId,String residentName,String gender,Date dob,String nrcNo,String fatherName,String motherName,String ethnicity,String wardOrVillageName,String crimeName,boolean isCriminalResident) {
		this.residentId = residentId;
		this.residentName = residentName;
		this.gender = gender;
		this.dob = dob;
		this.nrcNo = nrcNo;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.ethnicity = ethnicity;
		this.wardOrVillageName = wardOrVillageName;
		this.crimeName = crimeName;
		this.isCriminalResident = isCriminalResident;
	}
	
	public int getResidentId() {
		return residentId;
	}
	public void setResidentId(int residentId) {
		this.residentId = residentId;
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
	
	public String getWardOrVillageName() {
		return wardOrVillageName;
	}
	public void setWardOrVillageName(String wardOrVillageName) {
		this.wardOrVillageName = wardOrVillageName;
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
