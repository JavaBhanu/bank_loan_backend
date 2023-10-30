package com.bank.controller;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.CoreMatchers.is;

import com.bank.model.AccountType;
import com.bank.model.Address;
import com.bank.model.Customer;
import com.bank.model.IdProofType;
import com.bank.repo.CustomerRepo;
import com.bank.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest {
	
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ObjectMapper objectMapper;
    
    
    
    @BeforeEach
    void setup(){
    	customerRepo.deleteAll();
    }

    @Test
    public void registerCustomerTest() throws Exception{

        // given - precondition or setup
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
       

        // when - action or behaviour that we are going test
    	  ResultActions response=mockMvc.perform(post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)));

        // then - verify the result or output using assert statements
          
    	  response.andExpect(status().isOk())
          .andDo(print())
          .andExpect(jsonPath("$.userName",
                  is(customer.getUserName())));
    }
	    
	/*
	 * @MockBean private CustomerService customerService;
	 * 
	 * @Autowired private MockMvc mockMvc;
	 * 
	 * @Test public void testAddEmployee() { Customer customer = new Customer();
	 * customer.setAccountId(152); customer.setBranchName("BASHEERABAD");
	 * customer.setContactNo("9393802365"); customer.setCountry("INDIA");
	 * customer.setEmail("bhanu236@gmail.com");
	 * customer.setIdentificationDocNo("15833333");
	 * customer.setIdProofType(IdProofType.AADHAAR);
	 * customer.setInitDepositAmount(2000.00);
	 * customer.setAccountType(AccountType.Savings);
	 * customer.setUserName("bhanuprakash"); customer.setState("Telangana");
	 * customer.setPassWord("Bhanu@123"); customer.setName("Bhanu"); Address address
	 * = new Address(); address.setCity("hyd");
	 * address.setStreet("Uradamma Temple"); address.setZipcode("501143");
	 * customer.setAddress(address);
	 * 
	 * Customer response=customerService.addCustomer(customer);
	 * 
	 * when(response).thenReturn(customer);
	 * 
	 * }
	 */
	
	    }
	   
