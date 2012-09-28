package com.mincoms.book.admin.controller;

import java.util.Date;

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

import com.mincoms.book.admin.repository.BaseCodeRepository;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.domain.BookInfo.AddBook;
import com.mincoms.book.service.BookService;
import com.mincoms.book.service.CategoryService;

@Controller
public class RestrictionController {
	private static final Logger logger = LoggerFactory.getLogger(RestrictionController.class);
	@Autowired
	MessageSource messagesource;
	@Autowired
	BookService bookService;
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	BaseCodeRepository baseCodeRepository;
	@RequestMapping(value = "/admin/restriction/add", method = RequestMethod.GET)
	public String add(Model model)  {
	
		
		model.addAttribute("bookRestriction", new BookRestriction());		
		model.addAttribute("restrictionList", baseCodeRepository.findByRestrictions());
		
		return "admin/restriction/add";
	}
	@RequestMapping(value = "/admin/restriction/add", method = RequestMethod.POST)
	public String add(@Validated BookRestriction bookRestriction, BindingResult result, Model model)   {
		logger.debug("Post 콜"+bookRestriction.toString());
		
		if (result.hasErrors()) {
			logger.debug("필드에러발생:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			logger.debug("으아아:"+result.toString());
			logger.debug(bookRestriction.toString());
			for(int i=0;i<result.getAllErrors().size();i++){
				ObjectError oe = result.getAllErrors().get(i);
				logger.debug("oe.getCode()="+oe.getCode());
				logger.debug("oe.getDefaultMessage()="+oe.getDefaultMessage());
				logger.debug("oe.getCodes()="+oe.getCodes()[0]);
				
			}
			model.addAttribute("categoryRootList", categoryService.findByBookCategoryRoot());
			return "book/add";
		} else {	
			
			
			
		}		
		return "admin/book/list";
	}
	
}
