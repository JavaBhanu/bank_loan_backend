package com.bank.service;

import com.bank.model.Loan;
import com.bank.model.LoanResponse;


public interface LoanService {
	
	public LoanResponse applyLoan(Loan l);
	
	public Loan save(Loan loan);
	
}
