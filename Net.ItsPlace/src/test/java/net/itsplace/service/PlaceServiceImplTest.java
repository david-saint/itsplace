package net.itsplace.service;

import static org.junit.Assert.*;

import net.itsplace.init.TestApplicationContext;
import net.itsplace.repository.PlacePredicates;
import net.itsplace.repository.PlaceRepository;
import net.itsplace.web.repository.PlaceRepositoryTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysema.query.types.Predicate;

public class PlaceServiceImplTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceServiceImplTest.class); 
	
	@Autowired
	PlaceServiceImpl service;

	@Test
	public void testFindByAllPredicateJpaPaging() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFindByAllPredicate() {
		Predicate predicate = PlacePredicates.isAuth(true).and(PlacePredicates.likeFname("미스터"));
		service.findByAll(predicate);
	}

}
