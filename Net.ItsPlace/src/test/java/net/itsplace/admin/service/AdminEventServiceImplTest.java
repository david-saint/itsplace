package net.itsplace.admin.service;

import static org.junit.Assert.*;

import net.itsplace.domain.JpaPaging;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.service.PlaceEventService;
import net.itsplace.web.repository.PlaceRepositoryTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminEventServiceImplTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(AdminEventServiceImplTest.class); 

	@Autowired
	PlaceEventService service;
	
	@Test
	public void test() {
		String columns[] = new String[]{"title", "startDate", "endDate"};                                       
        JpaPaging paging = new JpaPaging(columns,0, 10, 0, "Desc","");
        
	//	service.findPlaceEventist(paging, true);
	}

}
