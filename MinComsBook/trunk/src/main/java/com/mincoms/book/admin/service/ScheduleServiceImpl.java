package com.mincoms.book.admin.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mincoms.book.admin.repository.RestrictionRepository;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.service.RentalService;
import com.mincoms.book.service.UserService;
import com.mincoms.book.util.DateUtil;
import com.mincoms.book.util.MailService;

@Service
public class ScheduleServiceImpl implements ScheduleService{
private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
	
	@Autowired
	RestrictionRepository restrictionRepo;
	@Autowired
	UserService userService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	RentalService rentalService;
	@Autowired
	MailService mailService;
	
	@Scheduled(cron="0 15 07 * * ?")//매일오전 07
	@Override
	public void solveRestriction() {
		
		List<BookRestriction> BookRestrictions = restrictionRepo.findBySolveDateIsNull();
		
		for(BookRestriction brs : BookRestrictions){
			 Calendar cal=Calendar.getInstance();
			 cal.setTime(brs.getRegDate());
			 cal.add(cal.DATE, Integer.parseInt(brs.getBasecode().getCodeKey()));
			 
			if(DateUtil.DateCompareToday(cal.getTime()) == 0 || DateUtil.DateCompareToday(cal.getTime()) == -1){
				brs.setSolveDate(new Date());
				brs.setSolveReason("자동해제");
				restrictionRepo.save(brs);
			}
		}
		
		
	}

	//@Scheduled(cron="*/5 * * * * ?")
	@Scheduled(cron="0 15 07 * * ?")//매일오전 07
	@Override
	public void returnBookOverUsers() {
		List<String> receptions = new ArrayList();
		List<BookRental> bookRentals = rentalService.findByReturnDateIsNotNull();
		String subject ="";
		String body ="";
		String userName ="";
		for(BookRental bookRental:bookRentals){
			if(DateUtil.DateCompareToday(bookRental.getEndDate()) == 1){
				//receptions.add("faye12005@gmail.com");
				receptions.add( bookRental.getUserInfo().getEmail() );
//				logger.info("반납해라"+bookRental.getUserInfo().getEmail());
				subject = bookRental.getBookInfo().getTitle() + " 도서를 반납해주세요";
				body = bookRental.getBookInfo().getTitle() + " 도서를 반납해주세요 제발" +"<br>";
//				body += "반납일은 " + bookRental.getReturnDate() + "이었 도서를 반납해주세요"+"<br>";
				userName = bookRental.getUserInfo().getEmail();
				mailService.sendMail("faye12005@gmail.com",bookRental.getUserInfo().getEmail(), subject, body, userName);
			}
		}
		//mailService.sendMail("faye12005@gmail.com", receptions, "도서대출 반납기한이 지났습니다","반납해주세요");//전체메일
		
	}

}
