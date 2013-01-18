package net.itsplace.web.repository;

import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.repository.PlacePredicates;
import net.itsplace.repository.PlaceRepository;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.mysema.query.types.Predicate;
public class PlaceRepositoryTest  extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceRepositoryTest.class); 
	
	@Autowired
	PlaceRepository repo;
	

	
	@Test
	public void test() {
		JpaPaging paging = new JpaPaging();
		
		Predicate predicate = PlacePredicates.isAuth(true).and(PlacePredicates.likeFname("babo"));
		
		Page<Place> places = repo.findAll(predicate, paging.getPageable("fid",Sort.Direction.DESC, 0, 2));
		//List<Place> places = repo.findAll();
		for (Place place : places) {
			logger.info(place.getFname());
		}
	}
	
	//@Test
	public void test2(){
		
	/*for (Place place : placeRepo.findByRecentPalces(2)) {
			logger.info(place.getFname());
		}
		*/
	}

}
