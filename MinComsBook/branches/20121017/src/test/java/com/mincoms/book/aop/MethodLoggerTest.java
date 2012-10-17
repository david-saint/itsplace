package com.mincoms.book.aop;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.domain.JsonResponse;
import com.mincoms.test.TestApplicationContext;

public class MethodLoggerTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(MethodLoggerTest.class);


	@Autowired
	JsonResponse json;
	@Test
	public void testMethodAOP() {
	
		logger.info(json.getStatus());
		
	}

}
