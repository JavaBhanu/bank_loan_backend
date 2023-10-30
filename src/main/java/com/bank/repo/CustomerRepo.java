package com.bank.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>,PagingAndSortingRepository<Customer, Integer>{

	 @Query("select c from Customer c where c.name=?1 or c.userName=?2 or c.email=?3 or c.contactNo=?4")
	 Customer checkCustomer(String name, String userName, String email, String contactNo);
	
	 @Query("select c.accountId from Customer c where c.email=?1 and c.passWord=?2")
	 Integer findCustomerByEmailAndPassword(String email, String passWord);
	 
	 @Query("select c from Customer c where c.email=?1 and c.passWord=?2")
	 Optional<Customer> findByUsernameOrEmail(String email, String password);
	 
	 @Query("select c from Customer c where c.userName=?1 and c.passWord=?2")
	 Customer findByUsername(String userName, String password);
	 
	
	 

	
}
