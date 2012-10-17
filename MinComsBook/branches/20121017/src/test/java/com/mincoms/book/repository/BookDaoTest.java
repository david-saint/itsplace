package com.mincoms.book.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.dao.BookDao;
import com.mincoms.test.TestApplicationContext;

public class BookDaoTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(BookDaoTest.class);
	
	@Autowired
	BookDao bookDao;

	@Test
	public void test() {
	//	bookDao.getBookInfoList();
	}

}
