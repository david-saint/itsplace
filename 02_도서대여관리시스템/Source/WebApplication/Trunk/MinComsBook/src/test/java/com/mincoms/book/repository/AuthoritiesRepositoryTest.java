package com.mincoms.book.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.mincoms.book.domain.Authorities;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.service.UserService;
import com.mincoms.test.TestApplicationContext;

public class AuthoritiesRepositoryTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(AuthoritiesRepositoryTest.class);
	
	@Autowired
	AuthoritiesRepository repository;
	
	@Autowired
	UserService userService;
	
	UserInfo user;
	@Before
	public void setUp() throws Exception {
		user = new UserInfo();
	}
	
	@Test
	public void testGetAuth(){
		
		user = userService.findByUserName("dhkim");
		logger.info("이름:{}",user.getUserRname());
		
		List<Authorities> auths = repository.findByUser(user);
		for(Authorities domain: auths){
			logger.info("권한:{}",domain.getRole().getName());
		}
	}
	@Ignore
	public void testPaging(){
		logger.info("이름");
		logger.info("이름");
		logger.info("이름");
		logger.info("이름");
		Page<Authorities> list = (Page<Authorities>) repository.findAll(new PageRequest(0,10));
		for (Authorities domain: list) {
			logger.info("이름:{}","");
		}
		assertThat(list.getTotalPages(), is(1));
	}
}


