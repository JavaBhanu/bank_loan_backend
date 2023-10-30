package com.bank.exception;

public class CustomerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6601139990476915568L;
	
	public CustomerNotFoundException(String message){
		super(message);
	}

}
