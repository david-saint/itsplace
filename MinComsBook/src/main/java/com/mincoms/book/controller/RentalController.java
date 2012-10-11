package com.mincoms.book.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.BookInfo.AddBook;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.security.SignedUser;
import com.mincoms.book.service.BookService;
import com.mincoms.book.service.RentalService;
import com.mincoms.book.service.UserService;
import com.mincoms.validation.ExistIsbn;

@Controller
@Validated
public class RentalController {
private static final Logger logger = LoggerFactory.getLogger(RentalController.class);
	
	@Autowired
	BookService bookService;
	@Autowired
	UserService userService;
	@Autowired
	RentalService rentalService;
	@Autowired
	JsonResponse json;
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = "/book/rental", method = RequestMethod.GET)
	public String rental(@RequestParam(required=true) String isbn, Model model)  {
		BookRental rental = new BookRental();
		rental.setBookInfo(bookService.findByIsbn(isbn));
		model.addAttribute("rental",rental);
		
		return "rental/add";
	}
	/**
	 * <b>도서반납 폼 </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 21
	 * @param id 반납 아이디 
	 * @see 
	 */
	@RequestMapping(value = "/book/return", method = RequestMethod.GET)
	public String returnGet(@RequestParam(required=true) long id, Model model)  {
		BookRental rental = new BookRental();
		
		rental= rentalService.findById(id);
		model.addAttribute("rental",rental);
		
		return "rental/returnBook";
	}
	/**
	 * <b>도서반납 </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param id 반납 id 
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/book/return", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse returnPost(@RequestParam(required=true) long id) throws Exception  {
		JsonResponse json  = rentalService.returnBook(id);
		
		return json;
	}
	/**
	 * <b>도서연기</b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param id 반납 아이디 
	 * @param day 연기 일수 
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/book/extend", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse extendPost(@RequestParam(required=true) long id,
												 @RequestParam(required=true) int day,
												 @RequestParam(required=true) @NotEmpty(message="연기사유를 입력하세요") String extendReason)  {
		
		logger.info("연기일수:{}",day);
		logger.info("연기사유:{}",extendReason);
		JsonResponse json  = rentalService.extendBook(id, day, extendReason);
		return json;
	}
	/**
	 * <b>도서대여 </b> <br />
	 * 도서 대출여부를 확인하고 도서를 대출한다
	 * <pre>
	 * <b>History:</b>
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param ISBN 
	 * @param Day  대출 일수
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/book/rental", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse rental(
			@RequestParam(required=true)  String isbn,
			@RequestParam(defaultValue="7") Integer day,
			Model model) throws Exception  {
		logger.debug("isbn:{}",isbn);
		logger.debug("day:{}",day);
		JsonResponse json = rentalService.saveRental(isbn, day, SignedUser.getUserInfo());
		
		return json;
	}
	/**
	 * <b>도서대출 </b> <br />
	 * 도서 대출여부를 확인힌디
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param ISBN 
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/book/isrental", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse isrental(
			@RequestParam(required=true) String isbn,
			Model model) throws Exception  {
		
		
		logger.debug("isbn:{}",isbn);
		BookInfo book = bookService.findByIsbn(isbn);
		if(book != null){
			json = bookService.isRental(isbn);
		}else{
			json.setResult(messageSource.getMessage("not.register.book",null, Locale.getDefault()));
			json.setFail();
		}
		return json;
	}
	/**
	 * <b>사용자 도서 대출 목록 </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param ISBN 
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/book/rentals", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse rentals() throws Exception  {
		//UserInfo userInfo = userService.findByUserId(47);
		json.setResult(rentalService.findByUserInfoAndReturnDateIsNull(SignedUser.getUserInfo()));
		json.setSuccess();
		return json;
		
	}
	/**
	 * <b>사용자 도서 대출 목록(History) </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param ISBN 
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/book/rentalHistory", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse rentalHistory() throws Exception  {
		UserInfo userInfo = userService.findByUserId(47);
		json.setResult(rentalService.findByUserInfoAndReturnDateIsNotNull(userInfo));
		json.setSuccess();
		return json;
		
	}
	@RequestMapping(value = "/book/rentals", method = RequestMethod.GET)
	public String list(Model model) {
		UserInfo userInfo = userService.findByUserId(47);
		model.addAttribute("rentalBookList",rentalService.findByUserInfoAndReturnDateIsNull(userInfo));
		return "book/rentals";
	}
}
