package net.itsplace.web.controller;

import net.itsplace.domain.TestRepo;
import net.itsplace.init.TestApplicationContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaceControllerTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(PlaceControllerTest.class);
	

	@Autowired
	PlaceController controller;
	@Autowired
	TestRepo testRepo;
	@Test
	public void test() {
		/*PlaceComment placeComment = new PlaceComment();
		placeComment.setComment("댁글내용 "); 
		placeComment.setFid(2);
		placeComment.setEmail("faye12005@gmail.com");
		DataBinder result = new DataBinder(placeComment);
		
		controller.addComment(placeComment, result.getBindingResult() , null);*/
		
	}

	@Test
	public void view(){
		testRepo.findAll();
			//logger.info(p.getFname()+p.getAddress().getBupname());
	}
}
