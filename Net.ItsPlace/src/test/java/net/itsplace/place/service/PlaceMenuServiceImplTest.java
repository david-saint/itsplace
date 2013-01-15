package net.itsplace.place.service;

import static org.junit.Assert.*;

import net.itsplace.domain.JpaPaging;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.repository.GroupBaseRepository;
import net.itsplace.repository.GroupBaseRepositoryTest;
import net.itsplace.service.PlaceMenuService;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaceMenuServiceImplTest  extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(PlaceMenuServiceImplTest.class); 
	
	@Autowired
	PlaceMenuService service;
	

	@Test
	public void testGetMenuList() {
		 String columns[] = new String[]{"title", "price","isSale","salePrice"};
         
         JpaPaging paging = new JpaPaging(columns,0, 10, 0, "desc","");
      
		service.getMenuList(paging, 2);
	}

}
