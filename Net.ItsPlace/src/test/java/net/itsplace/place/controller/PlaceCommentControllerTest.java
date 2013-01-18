package net.itsplace.place.controller;

import static org.junit.Assert.*;

import java.util.List;

import net.itsplace.admin.controller.AdminPlaceControllerTest;
import net.itsplace.controller.partner.PlaceCommentController;
import net.itsplace.domain.Address;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.PlaceComment;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.service.PlaceEventService;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaceCommentControllerTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(PlaceCommentControllerTest.class);
	

	@Autowired
	PlaceEventService service;
	@Autowired
	PlaceCommentController controller;
 
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPlaceCommentList() {
		 DataTable<PlaceComment> list =	controller.getPlaceCommentList(1, 10, 1, "desc","",2 );
			
		 List<PlaceComment> pList = (List<PlaceComment>) list.getRows();
		 for(int i=0; i<pList.size();i++){
			 logger.info(i+""+pList.get(i).getComment()); 
		 }
	}

}
