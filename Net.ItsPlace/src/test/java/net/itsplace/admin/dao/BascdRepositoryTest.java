package net.itsplace.admin.dao;

import static org.junit.Assert.*;

import net.itsplace.domain.TestRepo;
import net.itsplace.init.TestApplicationContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BascdRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(BascdRepositoryTest.class); 
	
	@Autowired
	Test2Repo repo;

	@Test
	public void test() {
		repo.findAll();
	}

}
