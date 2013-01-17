package net.itsplace.service;

import static org.junit.Assert.*;

import net.itsplace.domain.JpaPaging;
import net.itsplace.init.TestApplicationContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaceStampServiceTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceStampServiceTest.class); 
	
	@Autowired
	PlaceStampService service;


	@Test
	public void testSaveStampStampString() {
		fail("Not yet implemented");
	}

	@Test
	public void testBurnStamp() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlaceStampUserList() {
		  String columns[] = new String[]{"profileImageUrl", "user", "name", "mobile", "stampedTotal", "stampedLastDate"};
          
          JpaPaging paging = new JpaPaging(columns,0, 10, 1, JpaPaging.DESC, "");
          
		service.getPlaceStampUserList(paging);
	}

	@Test
	public void testGetPlaceStampListByEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlaceStampedListByEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlaceStampList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveStampStamp() {
		fail("Not yet implemented");
	}

}
