package com.bank.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.model.Loan;
import com.bank.model.LoanResponse;
import com.bank.service.LoanService;

@RequestMapping("/loan")
@RestController
public class LoanController {
	
	@Autowired
	private LoanService loanService;

	@PostMapping(path ="/apply")
	public ResponseEntity<?> applyLoan(@RequestBody Loan loan) {
		LoanResponse loanResponse=loanService.applyLoan(loan);
		return ResponseEntity.ok(loanResponse);
	}
	
	@PostMapping("/saveLoan")
	public ResponseEntity<Loan> saveLoan(@RequestBody Loan loan){
		LoanResponse loanResponse=loanService.applyLoan(loan);
		if(loanResponse==null) {
			return new ResponseEntity<Loan>(HttpStatus.BAD_GATEWAY);
		}
		else {
			loanService.save(loan);
			return new ResponseEntity<Loan>(HttpStatus.OK);
		}
	}
	
	 

}
