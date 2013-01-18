package net.itsplace.service;

import static org.junit.Assert.*;

import net.itsplace.basecode.ServiceType;
import net.itsplace.domain.Place;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.repository.PlacePredicates;
import net.itsplace.repository.PlaceRepository;
import net.itsplace.web.repository.PlaceRepositoryTest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysema.query.types.Predicate;

public class PlaceServiceImplTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceServiceImplTest.class); 
	
	@Autowired
	PlaceService service;

	
	@Test
	public void testFindByAllPredicateJpaPaging() {
		for(Place p : service.findByRecentPalces(5)){
			
			logger.warn(p.getPlaceType().name());
			logger.warn(p.getServiceType().name());
		}
		//logger.warn(ServiceType.Normal.ordinal()+"");
		//logger.warn(ServiceType.Normal.name());
	}

	@Ignore
	public void testFindByAllPredicate() {
		Predicate predicate = PlacePredicates.isAuth(true).and(PlacePredicates.likeFname("미스터"));
		service.findByAll(predicate);
	}

}
