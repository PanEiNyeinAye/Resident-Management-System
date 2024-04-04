package com.java.model;

import javax.validation.constraints.Pattern;

public class CommunicationModel {
	private int communicationModelId;
	private int receiverId;
	private String stateOrRegionName;
	private String townshipName;
	private String wardOrVillageName;
	@Pattern(regexp = "^([0-9]{1,2})\\/([A-Z][a-z]|[A-Z][a-z][a-z])([A-Z][a-z]|[A-Z][a-z][a-z])([A-Z][a-z]|[A-Z][a-z][a-z])\\([N,P,E]\\)[0-9]{6}$", message =" * NRC number must be in correct format.")
	private String residentNrcNo;
	
	public CommunicationModel() {}
	
	public int getCommunicationModelId() {
		return communicationModelId;
	}
	public void setCommunicationModelId(int communicationModelId) {
		this.communicationModelId = communicationModelId;
	}	
	
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
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
	public String getResidentNrcNo() {
		return residentNrcNo;
	}
	public void setResidentNrcNo(String residentNrcNo) {
		this.residentNrcNo = residentNrcNo;
	}

}
