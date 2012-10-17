package com.mincoms.book.admin.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.domain.Paging;
import com.mincoms.test.TestApplicationContext;

public class StaticsServiceImplTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(RestrictionServiceImplTest.class);
	
	@Autowired
	 StaticsService service;

	@Test
	public void testFindRentalStatics() {
		String columns[]={"A.isbn","reasonId"};
		Paging paging = new Paging(columns,0,10,0,"desc","");
		 Map<String, Object> parameter = new HashMap<String, Object>();
                                 
         paging.setParameter(parameter);
		service.findRentalStatics(paging);
	}

}
