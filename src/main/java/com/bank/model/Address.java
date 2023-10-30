package com.bank.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	//private String state;
    private String street;
    private String zipcode;
    private String city;
	
	/*
	 * public String getState() { return state; } public void setState(String state)
	 * { this.state = state; }
	 */
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

}
