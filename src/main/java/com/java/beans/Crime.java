package com.java.beans;

import java.sql.Date;

public class Crime {
	private int crimeId;
	private String crimeName;
	private Date criminalRecordDate;
	private String policeOfficerSign;
	private String crimeScenePlace;
	private int resultOfTheTrial;
	private int residentId;
	private Resident resident;
	private int townshipId;
	
	public Crime() {}
	
	public int getCrimeId() {
		return crimeId;
	}
	public void setCrimeId(int crimeId) {
		this.crimeId = crimeId;
	}
	
	public String getCrimeName() {
		return crimeName;
	}
	public void setCrimeName(String crimeName) {
		this.crimeName = crimeName;
	}
	public Date getCriminalRecordDate() {
		return criminalRecordDate;
	}
	public void setCriminalRecordDate(Date criminalRecordDate) {
		this.criminalRecordDate = criminalRecordDate;
	}
	public String getPoliceOfficerSign() {
		return policeOfficerSign;
	}
	public void setPoliceOfficerSign(String policeOfficerSign) {
		this.policeOfficerSign = policeOfficerSign;
	}
	public String getCrimeScenePlace() {
		return crimeScenePlace;
	}
	public void setCrimeScenePlace(String crimeScenePlace) {
		this.crimeScenePlace = crimeScenePlace;
	}
	public int getResultOfTheTrial() {
		return resultOfTheTrial;
	}
	public void setResultOfTheTrial(int resultOfTheTrial) {
		this.resultOfTheTrial = resultOfTheTrial;
	}
	public int getResidentId() {
		return residentId;
	}
	public void setResidentId(int residentId) {
		this.residentId = residentId;
	}

	public Resident getResident() {
		return resident;
	}
	public void setResident(Resident resident) {
		this.resident = resident;
	}
	public int getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(int townshipId) {
		this.townshipId = townshipId;
	}
}
