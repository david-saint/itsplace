package com.mincoms.book.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;

import com.mincoms.book.admin.repository.ExceptionRepository;
import com.mincoms.book.admin.repository.ExceptionSpecs;
import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.Paging;
import com.mincoms.test.TestApplicationContext;

public class ExceptionRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(BookRepoTest.class);
	
	@Autowired
	ExceptionRepository repo;

	@Test
	public void test() {
		String columns[]={"id","message"};
		Paging page = new Paging(columns,0,10,1,"desc","");
		
		Specifications<AppException> spec = Specifications.where(ExceptionSpecs.isCompletd(false));
		//spec = spec.and(ExceptionSpecs.idEqual(17));
		
		Page<AppException> es = repo.findAll(spec, page.getPageable());
		
		for(AppException exception: es){
			logger.info("message:{}",exception.getMessage());
			logger.info("message:{}",exception.getUser().getUserRname());
		//	logger.info(exception.toString());
		}
	}

}
