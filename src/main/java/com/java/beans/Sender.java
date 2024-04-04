package com.java.beans;
import java.sql.Date;

public class Sender {
	private int senderId;
	private Date sentDate;
	private int wardASId;
	private String residentNrcNo;
	private WardAS wardAS;
	
	public Sender() {}
	
	public Sender(Date sentDate) {
		this.sentDate = sentDate;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public int getWardASId() {
		return wardASId;
	}
	public void setWardASId(int wardASId) {
		this.wardASId = wardASId;
	}
	public WardAS getWardAS() {
		return wardAS;
	}
	public void setWardAS(WardAS wardAS) {
		this.wardAS = wardAS;
	}
	public String getResidentNrcNo() {
		return residentNrcNo;
	}
	public void setResidentNrcNo(String residentNrcNo) {
		this.residentNrcNo = residentNrcNo;
	}
}
