package net.itsplace.web.controller;

import static org.junit.Assert.fail;
import net.itsplace.domain.JsonResponse;
import net.itsplace.init.TestApplicationContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchControllerTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(SearchControllerTest.class);
	

	@Autowired
	SearchController controller;

	@Test
	public void testSearchPlace() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaceList() {
		JsonResponse json = controller.PlaceList(1, 10, 10, "");
		logger.info(json.getResult().toString());
	}

}
