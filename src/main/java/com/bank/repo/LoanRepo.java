package com.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.bank.model.Loan;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Integer>{
	

}
