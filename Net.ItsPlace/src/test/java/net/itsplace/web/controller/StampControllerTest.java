package net.itsplace.web.controller;

import static org.junit.Assert.*;

import net.itsplace.domain.JsonResponse;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.web.service.PlaceServicew;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class StampControllerTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(StampControllerTest.class);
	

	@Autowired
	PlaceServicew service;
	@Autowired
	 StampController controller;
	@Autowired
	 SearchController sController;
	@Before
	public void setUp() throws Exception {
	}

	@TestedOn(ints = { 0 })
	public void save() {
		JsonResponse j = controller.save("2222","faye12005@gmail.com", 2);
		logger.info(j.getResult().toString());
	}
	@Test
	public void eventList() {
//		JsonResponse j = sController.eventList(1,10, 10);
//		logger.info(j.getResult().toString());
//		logger.info(j.getPaging().toString());
	}

}
