package com.java.beans;

import java.sql.Date;

public class History {
	private int historyId;
	private String letterType;
	private Date residentViewLetterDate;
	private int residentId;
	
	public History() {}
	
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	
	public String getLetterType() {
		return letterType;
	}
	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}
	
	public Date getResidentViewLetterDate() {
		return residentViewLetterDate;
	}
	public void setResidentViewLetterDate(Date residentViewLetterDate) {
		this.residentViewLetterDate = residentViewLetterDate;
	}
	public int getResidentId() {
		return residentId;
	}
	public void setResidentId(int residentId) {
		this.residentId = residentId;
	}
}
