package com.mincoms.book.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;


import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.repository.UserRepository;
import com.mincoms.book.security.CustomUserDetails;
import com.mincoms.book.security.SignedUser;
import com.mincoms.test.TestApplicationContext;

public class RentalServiceImplTest extends TestApplicationContext {
	private static final Logger logger = LoggerFactory.getLogger(RentalServiceImplTest.class);
	
	@Autowired
	RentalService service;
	@Autowired
	BookService bservice;
	@Autowired
	UserRepository userRepo;
	
	
	@Ignore
	public void testRental() {

		JsonResponse json = service.saveRental("11111", 7,getUser("dhkim"));
		logger.info(json.toString());
		
		//service.saveRental();
	}
	@Test
	public void testUserRental() {
		UserInfo userInfo = userRepo.findByUserId(47);
		List<BookRental> rentals = service.findByUserInfoAndReturnDateIsNull(userInfo);
		
		for(BookRental rental:rentals){
			logger.info(rental.toString());
			
		}
	}
	
}
