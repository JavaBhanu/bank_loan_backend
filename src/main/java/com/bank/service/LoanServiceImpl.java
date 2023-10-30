package com.bank.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.exception.CustomerNotFoundException;
import com.bank.model.Customer;
import com.bank.model.Loan;
import com.bank.model.LoanResponse;
import com.bank.repo.CustomerRepo;
import com.bank.repo.LoanRepo;

@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private LoanRepo loanRepo;
	
	@Override
	public LoanResponse applyLoan(Loan l) {
		// TODO Auto-generated method stub
		String email = l.getCustomer().getEmail();
		String passWord =  l.getCustomer().getPassWord();
		Optional<Customer> customerDetails = customerRepo.findByUsernameOrEmail(email, passWord);
		 if (customerDetails.isPresent()) {
			 int accountId = customerDetails.get().getAccountId();
			 System.out.println("============="+accountId);
			 //select loan_type from loan_info where account_id=152;
			
			 Customer customer = customerRepo.findById(accountId)
						.orElseThrow(() -> new CustomerNotFoundException("Cusotmer Not Found: " + accountId));
			 if(customer!=null) {
				 customer.addLoan(l);
				 Loan save = loanRepo.save(l);
				 return new LoanResponse("Thanks For Applying Loan,We will get back to you ");
			 }
         } else {
             return new LoanResponse("Please Register As a Bank Customer,Then Apply For Loan");
         }
		return new LoanResponse("Cusotmer Not Found");	
	}

	@Override
	public Loan save(Loan loan) {
		// TODO Auto-generated method stub
		return loanRepo.save(loan);
	}

}
