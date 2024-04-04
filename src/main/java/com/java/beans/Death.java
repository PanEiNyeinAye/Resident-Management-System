package com.java.beans;

import java.sql.Date;

public class Death {
	private int deathId;
	private String personInformed;
	private String placeOfDeath;
	private Date dateOfDeath;
	private int residentId;
	private Resident resident;
	
	public Death() {}
	
	public int getDeathId() {
		return deathId;
	}
	public void setDeathId(int deathId) {
		this.deathId = deathId;
	}
	
	public String getPersonInformed() {
		return personInformed;
	}
	public void setPersonInformed(String personInformed) {
		this.personInformed = personInformed;
	}
	public String getPlaceOfDeath() {
		return placeOfDeath;
	}
	public void setPlaceOfDeath(String placeOfDeath) {
		this.placeOfDeath = placeOfDeath;
	}
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
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
}
