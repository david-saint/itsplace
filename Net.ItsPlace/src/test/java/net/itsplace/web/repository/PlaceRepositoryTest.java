package net.itsplace.web.repository;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.TestRepo;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.repository.PlacePredicates;
import net.itsplace.repository.PlaceRepository;
import net.itsplace.repository.PlaceRepo;

import org.aspectj.weaver.Iterators;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
public class PlaceRepositoryTest  extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceRepositoryTest.class); 
	
	@Autowired
	PlaceRepository repo;
	
	@Autowired
	PlaceRepo placeRepo;
	
	
	public void test() {
		JpaPaging paging = new JpaPaging();
		
		Page<Place> places = repo.findAll(PlacePredicates.isAuth("Y"), paging.getPageable("fid",Sort.Direction.DESC, 0, 2));
		//List<Place> places = repo.findAll();
		for (Place place : places) {
			logger.info(place.getFname());
		}
	}
	
	@Test
	public void test2(){
	for (Place place : placeRepo.findByRecentPalces(2)) {
			logger.info(place.getFname());
		}
		
	}

}
