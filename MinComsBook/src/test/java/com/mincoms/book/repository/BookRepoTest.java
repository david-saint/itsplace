package com.mincoms.book.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookReservation;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.repository.BookRepository;
import com.mincoms.book.repository.BookSpecs;
import com.mincoms.book.repository.NewsRepository;
import com.mincoms.book.repository.NewsRepositoryTest;
import com.mincoms.test.TestApplicationContext;

public class BookRepoTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(BookRepoTest.class);
	
	@Autowired
	BookRepository repo;

	
	@Ignore
	public void testFindReservation(){
		/*List<BookInfo> books =repo.findAll();
		for(BookInfo book: books){
			List<BookReservation> brs =  book.getBookReservation();
			for(BookReservation br: brs){
				logger.info("title:{}", br.getReservationDate().toString());	
			}
			
			
		}*/
	}
	@Ignore
	public void testFindBook(){
		BookInfo book = repo.findByIsbn("1");
		if(book == null){
			logger.info("널Exception");
		}else{
			logger.info("book{}",book.toString());
			
		}
	}
	@Ignore
	public void testFindAll() {
		//Page<Book> books = (Page<Book>)bookRepo.findAll(new PageRequest(page.getiDisplayStart(),page.getiDisplayLength()));
		//Page<Book> books = (Page<Book>)repo.findAll(BookSpecs.titleEqual("11"),  new PageRequest(0,10));
		Page<BookInfo> books = (Page<BookInfo>)repo.findAll(BookSpecs.titleIsLike("11"),  new PageRequest(0,10));
		for(BookInfo book: books){
			logger.info("title:{}",book.getTitle());
			
		}
	}
	@Test
	public void testFindAll2() {
		//Page<Book> books = (Page<Book>)bookRepo.findAll(new PageRequest(page.getiDisplayStart(),page.getiDisplayLength()));
		Sort sort = new Sort(Sort.Direction.ASC, "title");
		
	/*	String columns[];
		for(int i=0; i<Book.class.getDeclaredFields().length;i++){
			logger.info("A:{}",Book.class.getDeclaredFields()[i]);
			Book.class.getDeclaredFields()[i].getAnnotations()
			
		}
		for(int i=0; i<Book.class.getDeclaredAnnotations().length;i++){
			logger.info("B:{}",Book.class.getDeclaredAnnotations()[i]);
			Book.class.g
		}*/
		
		String columns[]={"title","authors"};
		Paging page = new Paging(columns,0,10,0,"desc","");
		
	//Page<BookInfo> books = (Page<BookInfo>)repo.findByTitleContainingOrAuthors("","", new PageRequest(1,10));
		Page<BookInfo> books = (Page<BookInfo>)repo.findByTitleContainingOrAuthors("","", page.getPageable());
	//	Page<BookInfo> books = (Page<BookInfo>)repo.findAll(new PageRequest(0,10));
		for(BookInfo book: books){
			logger.info("title:{}",book.getTitle());
			logger.info(book.toString());
		}
		
		
		
	}

}
