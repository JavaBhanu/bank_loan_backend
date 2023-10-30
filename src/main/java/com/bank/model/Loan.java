package com.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="loanInfo")
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loanId;
	private double loanAmt;
	private String loanType;
	private int duration;
	private String date;
	private int rateOfInterestInPercentage;
	
	@ManyToOne
	@JoinColumn(name = "accountId")
	private Customer customer;
	
	public int getLoanId() {
		return loanId;
	}


	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}


	public double getLoanAmt() {
		return loanAmt;
	}


	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}


	public String getLoanType() {
		return loanType;
	}


	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getRateOfInterestInPercentage() {
		return rateOfInterestInPercentage;
	}


	public void setRateOfInterestInPercentage(int rateOfInterestInPercentage) {
		this.rateOfInterestInPercentage = rateOfInterestInPercentage;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		// TODO Auto-generated method stub
		this.customer=customer;
	}

	
	
}
