package net.itsplace.repository;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.QPlaceStamp;
import net.itsplace.domain.QStamp;
import net.itsplace.domain.dto.PlaceCustomer;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.service.PlaceStampService;
import net.itsplace.service.PlaceStampServiceTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.ConstructorExpression;

public class StampRepositoryTest  extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(StampRepositoryTest.class); 
	
	@Autowired
	PlaceStampRepository placeStampRepo;

	@Autowired
	StampRepository s;
	
	@Autowired
	PlaceRepository p;
	
	@PersistenceContext
	private EntityManager em;
	
	@Test
	public void testSaveStampStampString() {
		//Place place = p.findOne(46);
		//List<PlaceStamp> placeStamps = placeStampRepo.findByPlace(place);
		
		//List<PlaceStamp> placeStamps = placeStampRepo.findByPlace();
		QStamp stamp = QStamp.stamp;
		QPlaceStamp placeStamp = QPlaceStamp.placeStamp;
		JPQLQuery query = new JPAQuery(em);
		query = query.from(stamp)
							.innerJoin(stamp.placeStamp, placeStamp)
							.where(stamp.status.notIn("C").and(placeStamp.place.fid.eq(46)))
							.groupBy(stamp.user.email);
									
		//List<Object[]> rs = query.list(stamp.pid.count(), stamp.saveDate.max());	
				
		List<PlaceCustomer> customers = new ArrayList<PlaceCustomer>();
		customers = query.list(ConstructorExpression.create(PlaceCustomer.class
							  , stamp.pid.count()
							  , stamp.saveDate.max()
							  , stamp.user
							  ));
							
		for(PlaceCustomer c : customers){
			logger.warn(c.toString());
		}
							
		
	}
}