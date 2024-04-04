package com.java.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class WardAS {
	private int wardASId;
	private String wardASName;
	private String wardASEmployeeNo;
	private String wardASPosition;
	@Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$") 
	private String wardASEmail;
	@Size(min = 8, max =8)
	private String wardASPassword;
	private String wardASPhoneNo;
	private int wardOrVillageId;
	private WardOrVillage wardOrVillage;
	public WardAS() {}
	
	public WardAS(String wardASName) {
		this.wardASName=wardASName;
	}
	public WardAS(String wardASName,WardOrVillage wardOrVillage) {
		this.wardASName=wardASName;
		this.wardOrVillage = wardOrVillage;
	}
	public WardAS(int wardASId,String wardASName,String wardASEmployeeNo, String wardASPosition, String wardASEmail,
			String wardASPassword,String wardASPhoneNo, WardOrVillage wardOrVillage) {
		this.wardASId =wardASId;
		this.wardASName=wardASName;
		this.wardASEmployeeNo =wardASEmployeeNo ;
		this.wardASPosition = wardASPosition;
		this.wardASEmail =wardASEmail ;
		this.wardASPassword =wardASPassword ;
		this.wardASPhoneNo =wardASPhoneNo ;
		this.wardOrVillage = wardOrVillage;
		
	}
	
	public int getWardASId() {
		return wardASId;
	}
	public void setWardASId(int wardASId) {
		this.wardASId = wardASId;
	}
	
	public String getWardASName() {
		return wardASName;
	}
	public void setWardASName(String wardASName) {
		this.wardASName = wardASName;
	}
	
	public String getWardASEmployeeNo() {
		return wardASEmployeeNo;
	}
	public void setWardASEmployeeNo(String wardASEmployeeNo) {
		this.wardASEmployeeNo = wardASEmployeeNo;
	}
	
	public String getWardASPosition() {
		return wardASPosition;
	}
	public void setWardASPosition(String wardASPosition) {
		this.wardASPosition = wardASPosition;
	}
	
	public String getWardASEmail() {
		return wardASEmail;
	}
	public void setWardASEmail(String wardASEmail) {
		this.wardASEmail = wardASEmail;
	}
	
	public String getWardASPassword() {
		return wardASPassword;
	}
	public void setWardASPassword(String wardASPassword) {
		this.wardASPassword = wardASPassword;
	}
	
	public String getWardASPhoneNo() {
		return wardASPhoneNo;
	}
	public void setWardASPhoneNo(String wardASPhoneNo) {
		this.wardASPhoneNo = wardASPhoneNo;
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
