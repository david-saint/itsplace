package com.mincoms.book.repository;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.News;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.test.TestApplicationContext;

public class RentalRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(RentalRepositoryTest.class);
	
	@Autowired
	RentalRepository repo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BookRepository bookRepo;

	@Ignore
	public void testSAVE() {
		UserInfo user = userRepo.findByUserName("dhkim");
		BookInfo book = bookRepo.findByIsbn("9788979145595");
		
		Calendar calendar = java.util.Calendar.getInstance();
		calendar.add(calendar.DATE, 7);
		
		
		BookRental rental = new BookRental();
		
		rental.setStartDate(new Date());
		rental.setEndDate(calendar.getTime());
		rental.setUser(user);
		rental.setBookInfo(book);
		repo.save(rental);
		
		
	}
	
	@Ignore
	public void testFindAll(){
		Page<BookRental> rentals = repo.findAll(new PageRequest(0,10));
		
		for(BookRental rental: rentals){
			logger.info("book:{}",rental.getBookInfo().getTitle() + rental.getStartDate());
		}
	}
	@Ignore
	public void testReturnDateIsNull(){
		String isbn = "9788979145595";
		List<BookRental> rentals = repo.findByIsbn(isbn);
		for(BookRental rental: rentals){
			logger.info("book:{}",rental.getBookInfo().getTitle() + rental.getStartDate());
		}
	}
	@Test
	public void testFindBook(){
		String isbn = "9788979145595";
		BookInfo book = new BookInfo();
		book.setIsbn(isbn);
		List<BookRental> rentals = repo.findByBookInfoAndReturnDateIsNull(book);
		for(BookRental rental: rentals){
			logger.info("book:{}",rental.getBookInfo().getTitle() + rental.getStartDate());
		}
	}
	
}
