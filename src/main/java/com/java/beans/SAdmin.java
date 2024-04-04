package com.java.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SAdmin {
	private int sadminId;
	private String sadminName;
	private String sadminEmployeeNo;
	@Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$") 
	private String sadminEmail;
	@Size(min = 8, max =8)
	private String sadminPassword;
	private int stateOrRegionId;
	private StateOrRegion stateOrRegion;
	
	public SAdmin() {}
	
	public SAdmin(int sadminId, String sadminEmail,
			String sadminPassword) {
		this.sadminId =sadminId;
		this.sadminEmail =sadminEmail ;
		this.sadminPassword =sadminPassword ;
	}
	
	public int getSadminId() {
		return sadminId;
	}
	public void setSadminId(int sadminId) {
		this.sadminId = sadminId;
	}
	
	public String getSadminName() {
		return sadminName;
	}
	public void setSadminName(String sadminName) {
		this.sadminName = sadminName;
	}
	public String getSadminEmployeeNo() {
		return sadminEmployeeNo;
	}
	public void setSadminEmployeeNo(String sadminEmployeeNo) {
		this.sadminEmployeeNo = sadminEmployeeNo;
	}
	public String getSadminEmail() {
		return sadminEmail;
	}
	public void setSadminEmail(String sadminEmail) {
		this.sadminEmail = sadminEmail;
	}
	public String getSadminPassword() {
		return sadminPassword;
	}
	public void setSadminPassword(String sadminPassword) {
		this.sadminPassword = sadminPassword;
	}
	public int getStateOrRegionId() {
		return stateOrRegionId;
	}
	public void setStateOrRegionId(int stateOrRegionId) {
		this.stateOrRegionId = stateOrRegionId;
	}
	public StateOrRegion getStateOrRegion() {
		return stateOrRegion;
	}
	public void setStateOrRegion(StateOrRegion stateOrRegion) {
		this.stateOrRegion = stateOrRegion;
	}
}
