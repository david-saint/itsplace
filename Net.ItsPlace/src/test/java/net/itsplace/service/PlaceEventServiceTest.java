package net.itsplace.service;

import static org.junit.Assert.*;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.init.TestApplicationContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaceEventServiceTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceEventServiceTest.class); 
	
	@Autowired
	PlaceEventService placeEventService;
 
	@Test
	public void testFindPlaceEventist() {
		  String columns[] = new String[]{"title", "startDate", "endDate"};                                       
          JpaPaging paging = new JpaPaging(columns,0, 5, 0, "desc", "");
         
          DataTable<PlaceEvent> list =  placeEventService.findPlaceEvenList(paging, 2, true, true);
          logger.info("Count:"+list.toString());
	}

}
