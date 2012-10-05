package com.mincoms.book.admin.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.domain.Paging;
import com.mincoms.book.service.BookCategoryServiceImplTest;
import com.mincoms.book.service.CategoryService;
import com.mincoms.test.TestApplicationContext;

public class RestrictionServiceImplTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(RestrictionServiceImplTest.class);
	
	@Autowired
	 RestrictionService service;

	@Test
	public void testGetRestrictionUserList() {
		String columns[]={"id","reasonId"};
		Paging page = new Paging(columns,0,10,0,"desc","");
		service.getRestrictionUserList(page, true);
	}

}
