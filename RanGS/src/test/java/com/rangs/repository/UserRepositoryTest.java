package com.rangs.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.rangs.domain.User;
import com.rangs.service.UserServiceTest;
import com.rangs.util.TestApplicationContext;

public class UserRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);
	@Autowired
	NewsRepository newsRepository;
	@Autowired
	UserRepository userRepo;
	
	@Test
	public void testFindAll() {
		
		List<User> userList = userRepo.findAll();
		
		logger.info("개체수{}",userList.size());
		
		
	}

	@Ignore
	public void testPaging(){
		
		Page<User> userList = (Page<User>) userRepo.findByFirstNameLike("John", new PageRequest(0,1));
		for (User user: userList) {
			logger.info("이름:{}",user.getFirstName()+user.getLastName());
		}
		assertThat(userList.getTotalPages(), is(2));
	}
}


