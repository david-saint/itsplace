package net.itsplace.repository;

import static org.junit.Assert.fail;

import java.util.List;

import net.itsplace.domain.PlaceStamp;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.service.PlaceStampService;
import net.itsplace.service.PlaceStampServiceTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class StampRepositoryTest  extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(StampRepositoryTest.class); 
	
	@Autowired
	PlaceStampRepository placeStampRepo;

	@Autowired
	StampRepository s;
	
	@Autowired
	PlaceRepository p;
	@Test
	public void testSaveStampStampString() {
		List<PlaceStamp> placeStamps = placeStampRepo.findByPlace(p.findOne(46));
		s.f
	}
}