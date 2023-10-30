package com.bank.model;

public class LoanResponse {
    private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LoanResponse(String message) {
		this.message = message;
	}
    
}
