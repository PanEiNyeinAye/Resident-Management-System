package com.java.beans;

import java.sql.Date;

public class Birth {
	private int birthId;
	private String placeOfBirth;
	private Date dateOfBirth;
	private String childGender;
	private String childFatherName;
	private String childMotherName;
	private Date dateOfRegistration;
	private String birthNote;
	private int wardOrVillageId;
	private WardOrVillage wardOrVillage;
	
	public Birth() {}
	
	public int getBirthId() {
		return birthId;
	}
	public void setBirthId(int birthId) {
		this.birthId = birthId;
	}
	
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getChildGender() {
		return childGender;
	}
	public void setChildGender(String childGender) {
		this.childGender = childGender;
	}
	public String getChildFatherName() {
		return childFatherName;
	}
	public void setChildFatherName(String childFatherName) {
		this.childFatherName = childFatherName;
	}
	public String getChildMotherName() {
		return childMotherName;
	}
	public void setChildMotherName(String childMotherName) {
		this.childMotherName = childMotherName;
	}
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public String getBirthNote() {
		return birthNote;
	}
	public void setBirthNote(String birthNote) {
		this.birthNote = birthNote;
	}
	public int getWardOrVillageId() {
		return wardOrVillageId;
	}
	public void setWardOrVillageId(int wardOrVillageId) {
		this.wardOrVillageId = wardOrVillageId;
	}
	public WardOrVillage getWardOrVillage() {
		return wardOrVillage;
	}
	public void setWardOrVillage(WardOrVillage wardOrVillage) {
		this.wardOrVillage = wardOrVillage;
	}
}
