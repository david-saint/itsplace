package net.itsplace.common;

import static org.junit.Assert.*;

import java.util.List;

import net.itsplace.admin.controller.AdminEventController;
import net.itsplace.admin.controller.AdminEventControllerTest;
import net.itsplace.domain.Address;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.init.TestApplicationContext;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonControllerTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(CommonControllerTest.class);
	@Autowired
	CommonController controller;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() throws Exception {
		 DataTable<Address> address =	controller.list( 1, 10, 1, "desc", "진천동 52555");
			
			 List<Address> addressList = (List<Address>) address.getRows();
			 for(int i=0; i<addressList.size();i++){
				 logger.info(i+""+addressList.get(i).getSido()); 
			 }
			 
	}

}
