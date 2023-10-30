package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.model.Customer;
import com.bank.model.LoginResponse;
import com.bank.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) {
		return new ResponseEntity<Customer>(customerService.addCustomer(c), HttpStatus.OK);
	}
	
	@PostMapping(path ="/loginCustomer")
	public ResponseEntity<?> loginCustomer(@RequestBody Customer customer)
	{
		String email=customer.getEmail();
		String passWord=customer.getPassWord();
		LoginResponse loginResponse = customerService.customerLogin(email,passWord);
		return ResponseEntity.ok(loginResponse);
	}
	
	
	@PutMapping("login/update")
	public ResponseEntity<String> updateAfterLogin(@RequestBody Customer customer){
		String userName=customer.getUserName();
		String password=customer.getPassWord();
		Integer accountId = customerService.doLoginForUpdate(userName, password);
		
		System.out.println("Customer Login Successfull"+accountId);
		if(accountId!=null) {
			Customer customerById = customerService.getCustomerById(accountId);
			customerById.setInitDepositAmount(customer.getInitDepositAmount());
			customerById.setBranchName(customer.getBranchName());
			customerById.setContactNo(customer.getContactNo());
			customerService.updateCustomer(customerById);
			return new ResponseEntity("Updated Account Details", HttpStatus.OK);
			
		}else {
			return new ResponseEntity("accountId Not Found",HttpStatus.NOT_FOUND);
		}
			    }
	
	/* --Update Customer Account Info By AccountId
	 
	    @PutMapping("{accountId}")
	 
	    public ResponseEntity<Customer> updateCustomer(@PathVariable("accountId") int customerId,
	                                                   @RequestBody Customer customer){
	    	
		   Customer customerById = customerService.getCustomerById(customerId);
		   if(customerById != null) {
			   customerById.setBranchName(customer.getBranchName());
			   customerService.updateCustomer(customerById);
			   return new ResponseEntity<Customer>(HttpStatus.OK);
		   }else {
			   return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND); 
		   }
		   
	*/
		   
		  	

}
