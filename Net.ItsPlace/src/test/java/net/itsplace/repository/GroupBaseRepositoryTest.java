package net.itsplace.repository;

import static org.junit.Assert.*;

import net.itsplace.init.TestApplicationContext;
import net.itsplace.web.repository.PlaceRepositoryTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupBaseRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(GroupBaseRepositoryTest.class); 
	//
	@Autowired
	GroupBaseRepository repo;
	
	
	@Test
	public void testFindByIsDelete() {
		repo.findByIsDelete(false);		
	}

}
