package com.mincoms.book.repository;

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

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.service.UserService;
import com.mincoms.test.TestApplicationContext;

public class RentalRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(RentalRepositoryTest.class);
	
	@Autowired
	RentalRepository repo;
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;
	@Autowired
	BookRepository bookRepo;

	@Test
	public void testSAVE() {
		UserInfo user = userRepo.findByUserName("dhkim");
		BookInfo book = bookRepo.findByIsbn("9788979145595");
	
		repo.findByRentalId(user.getUserId(), "9780811866026");
		
		
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
	@Ignore
	public void testFindBook(){
		String isbn = "9788996276593";
		BookInfo book = new BookInfo();
		book.setIsbn(isbn);
		UserInfo user = userService.findByUserId(47);
		List<BookRental> rentals = repo.findByUserInfoAndReturnDateIsNotNull(user);
		for(BookRental rental: rentals){
			logger.info("book:{}",rental.getBookInfo().getTitle() + rental.getStartDate()+ rental.getUserInfo().toString());
		}
	}
	
}
