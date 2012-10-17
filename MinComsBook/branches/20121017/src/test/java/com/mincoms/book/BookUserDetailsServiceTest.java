package com.mincoms.book;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.repository.AuthoritiesRepositoryTest;
import com.mincoms.book.security.BookUserDetailsService;
import com.mincoms.test.TestApplicationContext;

public class BookUserDetailsServiceTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(BookUserDetailsServiceTest.class);

	@Autowired
	BookUserDetailsService service;
	
	@Test
	public void test() {
		service.loadUserByUsername("dhkim");
	}

}
