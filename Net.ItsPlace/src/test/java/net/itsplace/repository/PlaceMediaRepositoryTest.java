package net.itsplace.repository;

import static org.junit.Assert.*;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.module.event.PlaceEventRepository;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaceMediaRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceMediaRepositoryTest.class); 

	@Autowired
	PlaceMediaRepository repo;
	
	@Test
	public void testFindByPlace() {
		Place p = new Place();
		p.setFid(46);
		
		List<PlaceMedia> list = repo.findByPlaceAndIsDelete(p, false);
		for (PlaceMedia placeMedia : list) {
			System.out.println("dddddddd"+ placeMedia.getmUrl());
			logger.info(placeMedia.getmTitle() + placeMedia.getSize());
		//	logger.debug(placeMedia.getmTitle() + placeMedia.getSize());
		//	logger.warn("dd");
		}
		
	}

}
