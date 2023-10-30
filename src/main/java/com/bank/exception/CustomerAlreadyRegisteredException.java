package com.bank.exception;

public class CustomerAlreadyRegisteredException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9019693044878747521L;
	
	public CustomerAlreadyRegisteredException(String message){
		super(message);
	}

}
