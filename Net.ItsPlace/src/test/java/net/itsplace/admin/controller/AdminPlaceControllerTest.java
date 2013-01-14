package net.itsplace.admin.controller;

import static org.junit.Assert.*;

import javax.inject.Inject;

import net.itsplace.domain.Place;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.service.IPlaceEventService;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class AdminPlaceControllerTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceControllerTest.class);
	

	@Autowired
	IPlaceEventService service;
	@Autowired
	AdminPlaceController controller;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddSubmit() {
		Place place = new Place();
		place.setName("babo");
		place.setFname("바보가게");
		//place.setFtype("1");
		place.setPhone1("666666");
		place.setMobile("22222222");
		DataBinder result = new DataBinder(place);
		//, Model model
		controller.addSubmit(place,result.getBindingResult() , null);
	}

}
