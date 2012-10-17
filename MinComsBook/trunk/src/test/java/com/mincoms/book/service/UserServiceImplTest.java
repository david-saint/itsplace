package com.mincoms.book.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.domain.UserInfo;
import com.mincoms.test.TestApplicationContext;

public class UserServiceImplTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
	
	@Autowired
	UserService userService;

	@Autowired
	
	UserInfo user;
	@Before
	public void setUp() throws Exception {
		user = new UserInfo();
	}

	
	
	@Test
	public void testFindActiveAll(){
		List<UserInfo> users = userService.findActiveAll();
		for(UserInfo user:users){
			//logger.info(user.getUserName() + user.getUserRname()+ user.getDept().getDeptName());
		}
		
		
	}

}
