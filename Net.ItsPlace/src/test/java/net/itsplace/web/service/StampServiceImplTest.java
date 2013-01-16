package net.itsplace.web.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import net.itsplace.init.TestApplicationContext;
import net.itsplace.service.StampService;
import net.itsplace.web.controller.PlaceControllerTest;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class StampServiceImplTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(StampServiceImplTest.class);
	

	@Autowired
	StampService stampService;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPlaceStampedList() {
		stampService.getPlaceStampedList("faye12005@gmail.com");
	}

	@Test
	public void testGetStampedList() {
		Map<String, Object> param  = new HashMap();
		param.put("fid",2);
		param.put("email", "faye12005@gmail.com");
		stampService.getStampedList(param);
		
	}

}
