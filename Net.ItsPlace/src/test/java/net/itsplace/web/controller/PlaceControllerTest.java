package net.itsplace.web.controller;

import static org.junit.Assert.*;

import java.util.List;

import net.itsplace.admin.service.AdminEventService;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.place.controller.PlaceCommentController;
import net.itsplace.place.controller.PlaceCommentControllerTest;
import net.itsplace.web.service.PlaceService;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.DataBinder;

public class PlaceControllerTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(PlaceControllerTest.class);
	

	@Autowired
	PlaceService service;
	@Autowired
	PlaceController controller;

	@Test
	public void test() {
		PlaceComment placeComment = new PlaceComment();
		placeComment.setComment("댁글내용 "); 
		placeComment.setFid(2);
		placeComment.setEmail("faye12005@gmail.com");
		DataBinder result = new DataBinder(placeComment);
		
		controller.addComment(placeComment, result.getBindingResult() , null);
		
	}

	@Test
	public void view(){
		Place p= service.getPlace(2);
			//logger.info(p.getFname()+p.getAddress().getBupname());
	}
}
