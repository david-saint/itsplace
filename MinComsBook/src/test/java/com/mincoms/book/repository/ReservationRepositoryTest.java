package com.mincoms.book.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;

import com.mincoms.book.admin.repository.ExceptionSpecs;
import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookReservation;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.service.BookService;
import com.mincoms.book.service.RentalService;
import com.mincoms.book.service.UserService;
import com.mincoms.test.TestApplicationContext;

public class ReservationRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(RentalRepositoryTest.class);
	
	@Autowired
	ReservationRepository repo;

	@Autowired
	UserService userService;
	
	@Autowired
	RentalService rentalService;
	@Autowired
	BookService bookService;
	
	
	@Test
	public void test1(){
		BookReservation bookReservation = new BookReservation();
		BookInfo book = new BookInfo();
		book.setIsbn("9788996276593");
		bookReservation.setBookInfo(book);
		//repo.findByTest("9788996276593",47);
	}
	
	@Ignore
	public void testFindByReservationStatusAndBookRentalIsNull() {
		Specifications<BookReservation> spec = Specifications.where(ReservationSpecs.titleEqual(""));
		List<BookReservation> list = repo.findAll(spec);
		for(BookReservation b: list){
			//logger.info(b.getBookInfo().getTitle());
			logger.info(b.getId()+b.getBookInfo().getTitle());
		}
	}

	@Ignore
	public void testSave(){
		UserInfo userInfo = userService.findByUserId(47);
		BookInfo bookInfo = bookService.findByIsbn("9788996276593");
		BookReservation br= new BookReservation();
		br.setReservationDate(new Date());
		br.setIsCanceled(false);
		br.setBookInfo(bookInfo);
		br.setUserInfo(userInfo);
		for(int i=0; i<30;i++){
			repo.save(br);
		}
		
		
	}
}
