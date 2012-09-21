package com.mincoms.book.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.repository.BookRepository;
import com.mincoms.book.repository.RentalRepository;
import com.mincoms.book.security.SignedUser;
import com.mincoms.book.util.DateUtil;

@Service
public class RentalServiceImpl implements RentalService {
	private static final Logger logger = LoggerFactory.getLogger(RentalServiceImpl.class);
	@Autowired
	BookRepository bookRepo;
	@Autowired
	RentalRepository rentalRepo;
	@Autowired
	BookService bookService;
	@Autowired
	MessageSource messageSource;

	@Override
	public JsonResponse saveRental(String isbn, int day, UserInfo userInfo) {
		JsonResponse json = new JsonResponse();
		if(bookService.isRental(isbn)){
			BookInfo book = bookRepo.findByIsbn(isbn);
			if(book != null){
				Calendar calendar = java.util.Calendar.getInstance();
				Date startDate = calendar.getTime();
				calendar.add(Calendar.DATE, day);
				Date endDate = calendar.getTime();
				
				BookRental rental = new BookRental();
				rental.setBookInfo(book);	
				rental.setStartDate(startDate);			
				rental.setEndDate(endDate);
				rental.setUser(userInfo);
				rental.setExtendcount(0);
				rental = rentalRepo.save(rental);
				
				json.setResult(messageSource.getMessage("rentaled", new Object [] {rental.getBookInfo().getTitle()}, Locale.getDefault()));
				json.setSuccess();
				
			}else{
				json.setResult(messageSource.getMessage("not.register.book",null, Locale.getDefault()));
				json.setFail();
			}
			
		}else{
			json.setResult(messageSource.getMessage("can.not.find.rental.book",null, Locale.getDefault()));
			json.setFail();
		}
		
		
		return json;
	}

	@Override
	public List<BookRental> findByUserInfoAndReturnDateIsNull(UserInfo userInfo) {
		return rentalRepo.findByUserInfoAndReturnDateIsNull(userInfo);
	}

	@Override
	public BookRental findById(long id) {
		return rentalRepo.findById(id);
	}

	@Override
	public JsonResponse returnBook(long id) {
		BookRental bookRental = rentalRepo.findById(id);
		bookRental.setReturnDate(new Date());
		rentalRepo.save(bookRental);
		JsonResponse json = new JsonResponse();
		json.setResult(messageSource.getMessage("returnBook", new Object [] {bookRental.getBookInfo().getTitle()}, Locale.getDefault()));
		json.setSuccess();
		return json;
	}

	@Override
	public JsonResponse extendBook(long id, int day, String extendReason) {
		BookRental bookRental = rentalRepo.findById(id);
		JsonResponse json = new JsonResponse();
	
		
		if(DateUtil.DateCompareToday(bookRental.getEndDate()) == 1 ){
			json.setResult(messageSource.getMessage("can.not.extendBook.needReturnBook",null, Locale.getDefault()));
			json.setFail();
		
		}else if(bookRental.getExtendcount()==1){
			json.setResult(messageSource.getMessage("can.not.extendBook.one",null, Locale.getDefault()));
			json.setFail();
			
		}else{
			Calendar calendar = java.util.Calendar.getInstance();
			calendar.add(Calendar.DATE, day);
			Date endDate = calendar.getTime();
			bookRental.setEndDate(endDate);
			bookRental.setExtendReason(extendReason);
			bookRental.setExtendcount(bookRental.getExtendcount() + 1);
			rentalRepo.save(bookRental);
			json.setResult(messageSource.getMessage("extendBook", new Object [] {bookRental.getBookInfo().getTitle()}, Locale.getDefault()));
			json.setSuccess();
		}
		
		return json;
	}


	
	
}
