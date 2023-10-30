package com.bank.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.exception.CustomerAlreadyRegisteredException;
import com.bank.exception.CustomerNotFoundException;
import com.bank.model.Customer;
import com.bank.model.LoginResponse;
import com.bank.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public Customer addCustomer(Customer c) {
		//String name, String userName, String email, Number contactNo
		Customer customer = customerRepo.checkCustomer(c.getName(), c.getUserName(), c.getEmail(), c.getContactNo());
		if (customer == null) {
			return customerRepo.save(c);	
		}
		throw new CustomerAlreadyRegisteredException("Customer Already Registered: " + customer.getAccountId());
	}

	@Override
	public Customer updateCustomer(Customer c) {
		return customerRepo.save(c);
	}

	@Override
	public Customer getCustomerById(int accountId) {
		Customer customer = customerRepo.findById(accountId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + accountId));
		//logger.info("Customer Found: " + accountId);
		return customer;
	}

	@Override
	public Integer doLogin(String email, String passWord) {
		Integer customerId = null;
		customerId = customerRepo.findCustomerByEmailAndPassword(email, passWord);
		if(customerId==null) {
			throw new CustomerNotFoundException("Customer Not Found: Please Register First then login" + customerId);
		}
		return customerId;
	}
	
	@Override
	public LoginResponse customerLogin(String email, String passWord) {
		    String msg = "";

	        Optional<Customer> customerDetails = customerRepo.findByUsernameOrEmail(email, passWord);

	                if (customerDetails.isPresent()) {

	                    return new LoginResponse("Login Success", true);

	                } else {

	                    return new LoginResponse("Login Failed,Please Enter Valid Username or Password", false);

	                }
	}

	@Override
	public Integer doLoginForUpdate(String userName, String passWord) {
		// TODO Auto-generated method stub
		Integer accountId = null;
		try {
		       Customer customer = customerRepo.findByUsername(userName,passWord);
			//logger.info("Customer: " + customerId + " Logged In Successfully");
		    int accountId2 = customer.getAccountId();
			return accountId2;
		} catch (Exception e) {
			throw new CustomerNotFoundException("Customer Not Found: " + accountId);
		}
	}

}
