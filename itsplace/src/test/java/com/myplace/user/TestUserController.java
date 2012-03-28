package com.myplace.user;

import static org.junit.Assert.*;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.transaction.TransactionConfiguration;


@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestUserController extends com.myplace.TestApplicationContext {
	
	@Autowired
	UserController userController;
	 
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	@Before
    public void setup() throws Exception {
		request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	    
	}	
	
	@Test
	public void testController() throws Exception{
	//	 RestTemplate restTemplate = new RestTemplate();
	  //    Object user = restTemplate.getForObject("http://localhost:8080/r/users/{username}", Object.class, "test_username");
		
	}
}
