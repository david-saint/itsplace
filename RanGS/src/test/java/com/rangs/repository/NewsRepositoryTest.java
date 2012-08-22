package com.rangs.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.rangs.domain.News;
import com.rangs.util.TestApplicationContext;

public class NewsRepositoryTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(NewsRepositoryTest.class);
	
	@Autowired
	NewsRepository newsRepository;
	
	
	@Ignore
	public void testFindAll2() {
		
	}
	@Ignore
	public void testFindAll() {
		
		List<News> NewsList = newsRepository.findAll();
		
		logger.info("개체수{}",NewsList.size());
		
		
	}

	
	@Test
	public void testPaging(){
		
		Page<News> NewsList = (Page<News>) newsRepository.findAll(new PageRequest(0,10));
		for (News news: NewsList) {
			logger.info("이름:{}",news.getNewsTitle());
		}
		assertThat(NewsList.getTotalPages(), is(2));
	}
}


