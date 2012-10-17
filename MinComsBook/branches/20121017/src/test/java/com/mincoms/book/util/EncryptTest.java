package com.mincoms.book.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.service.BookServiceImplTest;
import com.mincoms.test.TestApplicationContext;

public class EncryptTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(EncryptTest.class);


	@Test
	public void test() {
		logger.info(Encrypt.md5Encoding("1111"));
	}

}
