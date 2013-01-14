package net.itsplace.repository;

import static org.junit.Assert.*;

import net.itsplace.domain.Place;
import net.itsplace.init.TestApplicationContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaceEventRepositoryTest  extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceEventRepositoryTest.class); 

	@Autowired
	PlaceEventRepository repo;
	@Test
	public void testFindByIsDelete() {
	}

	@Test
	public void testFindByPlace() {
		Place p = new Place();
		p.setFid(1);
		repo.findByPlace(p);
		
	}

}
