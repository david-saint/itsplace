package net.itsplace.repository;

import static org.junit.Assert.*;

import net.itsplace.domain.Place;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.place.dao.PlacePredicate;
import net.itsplace.place.dao.PlaceRepository;
import net.itsplace.web.repository.PlaceRepositoryTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlacePredicateTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlacePredicateTest.class); 
	
	@Autowired
	private PlaceRepository repo;

	@Test
	public void testIsAuth() {
		Iterable<Place> places =  repo.findAll();
		for (Place place : places) {
			logger.info("places:{}",place.getName());
		}
	}

}
