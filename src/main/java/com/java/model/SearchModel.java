package com.java.model;

public class SearchModel {
	private String searchType;
	private String nameOrNrcValue;
	public SearchModel() {}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getNameOrNrcValue() {
		return nameOrNrcValue;
	}
	public void setNameOrNrcValue(String nameOrNrcValue) {
		this.nameOrNrcValue = nameOrNrcValue;
	}
}
