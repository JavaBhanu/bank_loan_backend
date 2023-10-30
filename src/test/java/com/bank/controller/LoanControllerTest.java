package com.bank.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.bank.model.AccountType;
import com.bank.model.Address;
import com.bank.model.Customer;
import com.bank.model.IdProofType;
import com.bank.model.Loan;
import com.bank.repo.LoanRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoanControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
    @Autowired
	private ObjectMapper objectMapper;

    @Autowired
    private LoanRepo loanRepo;
    
    @BeforeEach
    void setup(){
    	loanRepo.deleteAll();
    }
    @Test
    public void applyLoanTest() throws Exception{
    	  Customer customer = new Customer();
    	  customer.setAccountId(152); 
    	  customer.setBranchName("BASHEERABAD");
    	  customer.setContactNo("9393802365"); 
    	  customer.setCountry("INDIA");
    	  customer.setEmail("bhanu236@gmail.com");
    	  customer.setIdentificationDocNo("15833333");
    	  customer.setIdProofType(IdProofType.AADHAAR);
    	  customer.setInitDepositAmount(2000.00);
    	  customer.setAccountType(AccountType.Savings);
    	  customer.setUserName("bhanuprakash");
    	  customer.setState("Telangana");
    	  customer.setPassWord("Bhanu@123");
    	  customer.setName("Bhanu"); 
    	  Address address= new Address();
    	  address.setCity("hyd");
    	  address.setStreet("Uradamma Temple");
    	  address.setZipcode("501143");
    	  customer.setAddress(address); 

        // given - precondition or setup
           Loan loan=new Loan();
           loan.setDate("21/01/2023");
           loan.setDuration(5);
           loan.setLoanAmt(20000.00);
           loan.setLoanId(233);
           loan.setLoanType("personal");
           loan.setRateOfInterestInPercentage(10);
    	   loan.setCustomer(customer);
    	
       

        // when - action or behaviour that we are going test
    	  ResultActions response=mockMvc.perform(post("/loan/saveLoan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loan)));

        // then - verify the result or output using assert statements
          
    	  response.andExpect(status().isOk())
          .andDo(print());
    }
}
