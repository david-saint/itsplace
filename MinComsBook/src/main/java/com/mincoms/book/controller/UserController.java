package com.mincoms.book.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mincoms.book.admin.repository.BaseCodeRepository;
import com.mincoms.book.admin.service.RestrictionService;
import com.mincoms.book.domain.BaseCode;
import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategorySub;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.BookInfo.AddBook;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.domain.dto.DtoBookRestriction;
import com.mincoms.book.service.BookService;
import com.mincoms.book.service.CategoryService;
import com.mincoms.book.service.RentalService;
import com.mincoms.book.service.ReservationService;
import com.mincoms.book.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	MessageSource messagesource;
	@Autowired
	BookService bookService;
	@Autowired
	RentalService rentalService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	UserService userService;
	
	@Autowired
	JsonResponse json;
	
	@ResponseBody
	@RequestMapping(value = "/user/getActiveUsers", method = RequestMethod.GET, headers="Accept=application/json")
	public List<String> getActiveUsers(@RequestParam(required=true, defaultValue="1") String term){
		logger.info("term:{}",term);
		List<String> userNames = new ArrayList();
		for(UserInfo userInfo: userService.findByUserNameContaining(term)){
			userNames.add(userInfo.getUserName());
		}
		return userNames;
	}
	
	
	@RequestMapping(value = "/user/getUsersByDept", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody List<UserInfo> getUsersByDept(@RequestParam(value="deptId", required=true) Integer deptId)  {
		
		return userService.findByDeptInfo(deptId);
	}
}
