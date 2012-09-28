package com.mincoms.date;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.mincoms.book.BookUserDetailsServiceTest;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.service.RentalService;
import com.mincoms.book.service.UserService;
import com.mincoms.test.TestApplicationContext;

public class TestDate extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(TestDate.class);
	@Autowired
	RentalService rentalService;
	@Autowired
	UserService userService;
	@Scheduled(cron="*/5 * * * * ?")
	@Test
	public void test() {
		Calendar calendar = java.util.Calendar.getInstance();
		Calendar calendar2 = java.util.Calendar.getInstance();
		Calendar calendar3 = java.util.Calendar.getInstance();
		calendar.add(calendar.DATE,1);
		Date d = calendar.getTime();
		System.out.println(d.toString());
		
		System.out.println(calendar.getTime().toString());

		Date today2 = new Date();
		long diff = ( calendar.getTime().getTime()-today2.getTime()) /1000/60/60/24;
		
		Date today = new Date();		
		
		if(calendar.before(calendar2)){
			System.out.println("이후");
		}else{
			System.out.println("이전");
		}
		
		UserInfo userInfo = userService.findByUserId(47);
		List<BookRental> list = rentalService.findByUserInfoAndReturnDateIsNull(userInfo);
		 System.out.println(list.get(1).getEndDate().toString()+"");
		 
		 long endDay =  ((list.get(1).getEndDate().getTime() - new Date().getTime()) /1000/60/60/24);
		  System.out.println(endDay);
         endDay = Math.round(endDay)+1;
         System.out.println(endDay+"");
        String returnMessage = "";
        if(endDay<0){        	
        	returnMessage = "반납일 "+endDay;
        }else if(endDay == 0){
        	returnMessage = "오늘은 반납일입니다";
        }else{
        	returnMessage = (endDay) + "일 남았습니다";
        }
        System.out.println(returnMessage);
		
	}

}
