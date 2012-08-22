package com.rangs.service;

import static org.junit.Assert.*;

import java.util.List;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rangs.domain.Role;
import com.rangs.domain.User;
import com.rangs.repository.UserRepository;
import com.rangs.util.TestApplicationContext;

public class UserServiceTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Autowired
	UserService userService;
	
	User user;
	Role role;
	
	@Before
	public void setUp() throws Exception {
	
		user = new User();
        role = new Role();
		
	}
	
	@Ignore
	public void testCreate() {
		
		
		user.setRole(role);
		user.setFirstName("hoon");
		assertTrue(userService.create(user));
	}
	
	@Test
	public void testUpdate() throws Exception {
		user.setUsername("kim");
		user.setFirstName("hhh");
		
	
		user.setRole(role);
		logger.info("결과{}",userService.update(user).toString());
	}
	
	
}
