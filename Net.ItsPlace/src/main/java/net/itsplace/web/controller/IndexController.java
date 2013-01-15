package net.itsplace.web.controller;

import java.util.Locale;

import net.itsplace.domain.User;
import net.itsplace.service.BaseServiceImpl;
import net.itsplace.service.PlaceEventService;
import net.itsplace.service.PlaceService;
import net.itsplace.web.service.SearchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	PlaceService placeService;
	@Autowired
	PlaceEventService placeEventService;
	
	@Autowired
	private SearchService searchService;
		
	@Autowired
	private BaseServiceImpl commonService;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(@RequestParam(value="badCredential", required=false) boolean badCredential, Locale locale, Model model) {
		Locale.setDefault(locale) ;
		logger.info("사용자 메인 페이지:"+badCredential);
		
	
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		logger.info("사용자 메인 페이지s:{}",auth.getName());
		if(auth != null){
			if(auth.isAuthenticated()){
				if(!auth.getName().equals("anonymousUser")){
					return "redirect:/places";
				}
				
			}
		}
		if(badCredential == true){
			model.addAttribute("error","이메일 또는 비밀번호가 잘못되었어요");
		}else{
			model.addAttribute("error","");
		}
		model.addAttribute("recentPlaceList",placeService.findByRecentPalces(4));
		model.addAttribute("recentEventList",placeEventService.getRecentEventList(4));
		model.addAttribute("user",new User());
		
		
		return "web/index/index";
	}
	
	
	@RequestMapping(value = "/getmessage", method = RequestMethod.GET)
	public @ResponseBody String getMessage() {
	  	logger.info("Accessing protected resource");
	   	return "ssssssssss";
	}
	
}
