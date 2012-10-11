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

@Component
public class AsyncComponent {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
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
	//24시간 지나면 예약 취소 
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
				logger.info("메일발송 성공");
		}
}
