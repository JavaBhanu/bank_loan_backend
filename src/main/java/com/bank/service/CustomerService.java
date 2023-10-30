package com.bank.service;

import com.bank.model.Customer;
import com.bank.model.LoginResponse;


public interface CustomerService {
	
	public Integer doLogin(String email, String passWord);

	public Customer addCustomer(Customer c);

	public Customer updateCustomer(Customer c);

	public Customer getCustomerById(int accountId);
	
	public LoginResponse customerLogin(String email, String passWord);
	
	public Integer doLoginForUpdate(String userName, String passWord);

}
