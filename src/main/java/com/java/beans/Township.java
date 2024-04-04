package com.java.beans;

public class Township extends StateOrRegion{
	private int townshipId;
	private String townshipName;
	private int stateOrRegionId;
	private StateOrRegion stateOrRegion;
	
	public Township() {}
	
	public Township(String townshipName) {
		this.townshipName = townshipName;
	}
	public Township(StateOrRegion stateOrRegion,String townshipName) {
		this.townshipName = townshipName;
		this.stateOrRegion = stateOrRegion;
	}
	
	public int getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(int townshipId) {
		this.townshipId = townshipId;
	}
	
	public String getTownshipName() {
		return townshipName;
	}
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
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
