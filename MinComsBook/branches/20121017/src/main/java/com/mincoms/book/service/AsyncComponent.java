package com.mincoms.book.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.mincoms.book.domain.BookReservation;
import com.mincoms.book.repository.BookRepository;
import com.mincoms.book.repository.RentalRepository;
import com.mincoms.book.repository.ReservationRepository;
import com.mincoms.book.util.MailService;
/**
 * <b>비동기 컴포넌트 </b> <br />
 * <pre>
 * <b>History:</b>
 * </pre>
 * @author 김동훈
 * @version 2.0
 * @since 2012. 8. 24  
 * @throws 
 * @see 
 */
@Component
public class AsyncComponent {

	private Logger logger = LoggerFactory.getLogger(AsyncComponent.class);
	
	@Autowired
	BookRepository bookRepo;
	@Autowired
	RentalRepository rentalRepo;
	@Autowired
	BookService bookService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	ReservationRepository reservationRepo;
	@Autowired
	MailService mailService;
	@Autowired
	MessageSource messageSource;
	/**
	 * <b>24시간후 예약취소 </b> <br />
	 * 24시간 후에 예약이 존쟇하면 자동 취소
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param isbn 
	 * @throws Exception 
	 * @see 
	 */
	@Async
	public void reservationBookCancelAfter24(String isbn){
			try {
				//Thread.sleep(86400000);//24시간
				Thread.sleep(5000);//24시간
				List<BookReservation> list = reservationRepo.findByReservationBook(isbn);
				for(BookReservation  bookReservation : list){
					reservationService.reservationCancel(bookReservation.getId());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("24시간뒤에실행");
	}
	
	/**
	 * <b>도서반난ㅂ시 예약자가 있으면 메일발송</b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param isbn 
	 * @throws Exception 
	 * @see 
	 */
	@Async
	public void reservationBookUserSendEmail(String isbn){
			List<BookReservation> list = reservationRepo.findByReservationBook(isbn);
			String subject="";
			String body ="";
			for(BookReservation  bookReservation : list){
				subject = bookReservation.getBookInfo().getTitle() + "도서가 대출 가능합니다";
				body = bookReservation.getBookInfo().getTitle() + "도서가 대출 가능합니다";
				mailService.sendMail("faye12005@gmail.com",bookReservation.getUserInfo().getEmail(), subject, body, bookReservation.getUserInfo().getUserRname());
			}
	}
}
