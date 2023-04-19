package com.dto;

public class Contacts {
	
	private String name;
	private long phoneNo;
	private String address;
	private String favState;
	
	public Contacts(String name,long phoneNo,String address,String favState) {
		this.name=name;
		this.phoneNo=phoneNo;
		this.address=address;
		this.favState=favState;
		
	}
	
	public Contacts() {
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFavState() {
		return favState;
	}
	public void setFavState(String favState) {
		this.favState = favState;
	}
	
}


