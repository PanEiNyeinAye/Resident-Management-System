package com.java.beans;

public class StateOrRegion {
	private int stateOrRegionId;
	private String stateOrRegionName;
	
	public StateOrRegion() {}
	
	public StateOrRegion(String stateOrRegionName) {
		this.stateOrRegionName = stateOrRegionName;
	}
	
	public StateOrRegion(int stateOrRegionId, String stateOrRegionName) {
		this.stateOrRegionId = stateOrRegionId;
		this.stateOrRegionName = stateOrRegionName;
	}
	
	public int getStateOrRegionId() {
		return stateOrRegionId;
	}
	public void setStateOrRegionId(int stateOrRegionId) {
		this.stateOrRegionId = stateOrRegionId;
	}
	
	public String getStateOrRegionName() {
		return stateOrRegionName;
	}
	public void setStateOrRegionName(String stateOrRegionName) {
		this.stateOrRegionName = stateOrRegionName;
	}
}
