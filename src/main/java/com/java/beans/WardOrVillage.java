package com.java.beans;

public class WardOrVillage extends Township{
	private int wardOrVillageId;
	private String wardOrVillageName;
	private int townshipId;
	private Township township;
	
	public WardOrVillage() {}
	
	public WardOrVillage(String wardOrVillageName) {
		this.wardOrVillageName = wardOrVillageName;
	}
	public WardOrVillage( String wardOrVillageName, Township township) {
		this.wardOrVillageName = wardOrVillageName;
		this.township = township;
	}
	public WardOrVillage( int wardOrVillageId, String wardOrVillageName, Township township) {
		this.wardOrVillageId = wardOrVillageId;
		this.wardOrVillageName = wardOrVillageName;
		this.township = township;
	}
	public int getWardOrVillageId() {
		return wardOrVillageId;
	}
	public void setWardOrVillageId(int wardOrVillageId) {
		this.wardOrVillageId = wardOrVillageId;
	}
	
	public String getWardOrVillageName() {
		return wardOrVillageName;
	}
	public void setWardOrVillageName(String wardOrVillageName) {
		this.wardOrVillageName = wardOrVillageName;
	}
	public int getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(int townshipId) {
		this.townshipId = townshipId;
	}
	public Township getTownship() {
		return township;
	}
	public void setTownship(Township township) {
		this.township = township;
	}
	
	
}
