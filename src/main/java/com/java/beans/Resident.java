package com.java.beans;
import java.sql.*;
import java.time.LocalDate;  
import java.time.Period;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;  

public class Resident {
	private int residentId;
	private String residentName;
	private String gender;
	private Date dob;
	@Pattern(regexp = "^([0-9]{1,2})\\/([A-Z][a-z]|[A-Z][a-z][a-z])([A-Z][a-z]|[A-Z][a-z][a-z])([A-Z][a-z]|[A-Z][a-z][a-z])\\([N,P,E]\\)[0-9]{6}$", message =" * NRC number must be in correct format.")
	private String nrcNo;
	private String fatherName;
	private String motherName;
	private String ethnicity;
	private String note;
	@Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$") 
	private String residentEmail;
	@Size(min = 8, max =8)
	private String residentPassword;
	private boolean bcg;
	private boolean fiveVaccines1;
	private boolean fiveVaccines2;
	private boolean fiveVaccines3;
	private boolean vitaminA;
	private boolean hpvVaccine;
	private boolean deceased;
	private int wardOrVillageId;
	private WardOrVillage wardOrVillage;
	private WardAS wardAS;
	
	public Resident() {}
	
	public Resident(String nrc) {
		nrcNo = nrc;
	}
	public Resident(int residentId, String nrc) {
		this.residentId = residentId;
		nrcNo = nrc;
	}
	public Resident(int residentId,String residentName,String gender,Date dob,String nrcNo,
			String fatherName,String motherName,String ethnicity,String note,String residentEmail,
			String residentPassword,int wardOrVillageId) {
		this.residentId = residentId;
		this.residentName = residentName;
		this.gender = gender;
		this.dob = dob;
		this.nrcNo = nrcNo;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.ethnicity = ethnicity;
		this.note = note;
		this.residentEmail =residentEmail;
		this.residentPassword = residentPassword;
		this.wardOrVillageId = wardOrVillageId;
	}
	
	public Resident(String residentName,String gender,String nrcNo,
			String fatherName,WardOrVillage wardOrVillage,WardAS wardAS) {
		this.residentName = residentName;
		this.gender = gender;
		this.nrcNo = nrcNo;
		this.fatherName = fatherName;
		this.wardOrVillage = wardOrVillage;
		this.wardAS = wardAS;
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
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public boolean getBcg() {
		return bcg;
	}
	public void setBcg(boolean bcg) {
		this.bcg = bcg;
	}
	public boolean getFiveVaccines1() {
		return fiveVaccines1;
	}
	public void setFiveVaccines1(boolean fiveVaccines1) {
		this.fiveVaccines1 = fiveVaccines1;
	}
	public boolean getFiveVaccines2() {
		return fiveVaccines2;
	}
	public void setFiveVaccines2(boolean fiveVaccines2) {
		this.fiveVaccines2 = fiveVaccines2;
	}
	public boolean getFiveVaccines3() {
		return fiveVaccines3;
	}
	public void setFiveVaccines3(boolean fiveVaccines3) {
		this.fiveVaccines3 = fiveVaccines3;
	}
	public boolean getVitaminA() {
		return vitaminA;
	}
	public void setVitaminA(boolean vitaminA) {
		this.vitaminA = vitaminA;
	}
	public boolean getHpvVaccine() {
		return hpvVaccine;
	}
	public void setHpvVaccine(boolean hpvVaccine) {
		this.hpvVaccine = hpvVaccine;
	}
	public boolean getDeceased() {
		return deceased;
	}
	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
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
	
	public WardAS getWardAS() {
		return wardAS;
	}
	public void setWardAS(WardAS wardAS) {
		this.wardAS = wardAS;
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
	
	public int calculateAgeInMonth(LocalDate dob)   
	{ 
		LocalDate curDate = LocalDate.now();
		if ((dob != null) && (curDate != null))   
		{  
			return Period.between(dob, curDate).getMonths();  
		}  
		else  
		{  
			return 0;  
		}  
	} 
}
