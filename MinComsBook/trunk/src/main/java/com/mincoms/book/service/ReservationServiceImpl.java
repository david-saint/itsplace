package com.mincoms.book.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.mincoms.book.admin.service.RestrictionService;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookReservation;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.QBookReservation;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.domain.vo.VoBookInfo;
import com.mincoms.book.repository.ReservationRepository;
import com.mincoms.book.security.SignedUser;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
public class ReservationServiceImpl implements ReservationService {
	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);
	@PersistenceContext
	private EntityManager em;
	@Autowired
	BookService bookService;
	@Autowired
	RestrictionService restrictionService;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	MessageSource messageSource;

	
	@Override
	@Deprecated
	public DataTable findReservationBooks(Paging page) {
		QBookReservation  bookReservation = QBookReservation.bookReservation;

		JPQLQuery query = new JPAQuery(em);
		List<Object[]> rs = query.from(bookReservation)
								 .groupBy(bookReservation.bookInfo.isbn,bookReservation.bookInfo.title)
								 .limit(page.getiDisplayLength())
								 .offset(page.getCurrentPage()*page.getiDisplayStart())
								 .list(bookReservation.bookInfo.thumbnail,
									   bookReservation.bookInfo.bookCategory.bookCategorySub,
									   bookReservation.bookInfo.bookCategory,
									   bookReservation.bookInfo.title,
									   bookReservation.bookInfo.authors,
									   bookReservation.bookInfo.count,
									   bookReservation.bookInfo.publisher,
									   bookReservation.bookInfo.publishedDate,
									   bookReservation.bookInfo.title,
									   bookReservation.count());
		for(Object[] rows : rs){
			VoBookInfo voBookInfo = new VoBookInfo();
			BookInfo bookInfo = new BookInfo();
			bookInfo.setThumbnail(rows[0].toString());
			bookInfo.setIsbn(rows[0].toString());
			bookInfo.setTitle(rows[1].toString());
			
			voBookInfo.setBookInfo(bookInfo);
		
			}
		return null;
	}

	@Override
	public JsonResponse reservation(String isbn) {
		JsonResponse json = new JsonResponse();
		if(bookService.isRental(isbn,SignedUser.getUserInfo()).getStatus() == json.SUCCEESS){			
			json.setResult(messageSource.getMessage("rental.able", null, Locale.getDefault()));
			json.setFail();
		}else{
			//대출중지중?
			json = restrictionService.isRestriction(SignedUser.getUserInfo());
			if(json.getStatus() == json.SUCCEESS){
				json.setFail();
				return json;
			}else{
				//예약가능함
				if(reservationRepository.findByReservationBook(isbn,SignedUser.getUserId()) == null){
					BookReservation bookReservation = new BookReservation();
					bookReservation.setBookInfo(bookService.findByIsbn(isbn));
					bookReservation.setIsCanceled(false);
					bookReservation.setReservationDate(new Date());
					bookReservation.setUserInfo(new UserInfo(SignedUser.getUserId()));
					bookReservation = reservationRepository.save(bookReservation);
					json.setResult(messageSource.getMessage("reservationBook.success", new Object [] {bookReservation.getBookInfo().getTitle()}, Locale.getDefault()));
					json.setSuccess();
				}else{
					json.setResult(messageSource.getMessage("reservationBook.already", null, Locale.getDefault()));
					json.setFail();
				}
			}
			
		}
		
		return json;
	}

	@Override
	public JsonResponse reservationCancel(int id) {
		JsonResponse json = new JsonResponse();
		BookReservation bookReservation = reservationRepository.findById(id);
		bookReservation.setIsCanceled(true);
		reservationRepository.save(bookReservation);
		json.setResult(messageSource.getMessage("reservationBook.cancel", new Object [] {bookReservation.getBookInfo().getTitle()}, Locale.getDefault()));
		json.setSuccess();
		return json;
	}

	@Override
	public List<BookReservation> findByReservationBooks(int userId) {
		return reservationRepository.findByReservationBooks(userId);
	}

	@Override
	public List<BookReservation> findByReservationBook(String isbn) {
		return reservationRepository.findByReservationBook(isbn);
	}

	@Override
	public BookReservation findByReservationBook(String isbn, int userId) {
		return reservationRepository.findByReservationBook(isbn, userId);
	}
}
