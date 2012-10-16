package net.itsplace.web.repository;

import static org.junit.Assert.*;

import net.itsplace.init.TestApplicationContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaceRepositoryTest  extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceRepositoryTest.class); 
	
	@Autowired
	PlaceRepository repo;
	@Test
	public void test() {
		repo.findAll();
	}

}
