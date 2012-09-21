package com.mincoms.book.controller;

import java.util.List;

import javax.annotation.Signed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.BookReservation;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.security.SignedUser;
import com.mincoms.book.service.BookService;
import com.mincoms.book.service.ReservationService;

@Controller
public class ReservationController {
private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	BookService bookService;
	
	@Autowired
	ReservationService reservationService;
	@Autowired
	JsonResponse json;
	@Autowired
	MessageSource messageSource;
	/**
	 * <b>도서예약 </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param isbn 
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/book/reservation", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse  reservation(@RequestParam(required=true) String isbn, Model model)  {
		
		return reservationService.reservation(isbn);
	}
	/**
	 * <b>도서예약취소 </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param id 
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/book/reservationCancel", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse  reservationCancel(@RequestParam(required=true) int id)  {
		
		return reservationService.reservationCancel(id);
	}
	/**
	 * <b>사용자 도서예약목록 </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param id 
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/book/reservations", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse reservations()  {
		
		
		 json.setResult( reservationService.findByReservationBooks(SignedUser.getUserId()));
		 json.setSuccess();
		 return json;
	}
}
