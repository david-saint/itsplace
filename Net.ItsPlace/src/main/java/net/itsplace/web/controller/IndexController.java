package net.itsplace.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Message;
import net.itsplace.user.User;
import net.itsplace.web.service.IndexService;
import net.itsplace.web.service.PlaceService;
import net.itsplace.web.service.SearchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
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
	private IndexService indexService;
	@Autowired
	private PlaceService placeService;
	@Autowired
	private SearchService searchService;
		

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(@RequestParam(value="badCredential", required=false) boolean badCredential, Locale locale, Model model, Device device,SitePreference sitePreference) {
		Locale.setDefault(locale) ;
		logger.info("사용자 메인 페이지:"+badCredential);
		
		if (device.isMobile()) {
            logger.info("Hello mobile user!");      
        } else {
            logger.info("Hello desktop user!");         
        }
		if (sitePreference == SitePreference.MOBILE) {
			 logger.info("sitePreference mobile user!");    
        } else {
        	 logger.info("sitePreference desktop user!");         
        }
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
		model.addAttribute("recentPlaceList",indexService.getRecentPlaceList(4));
		model.addAttribute("recentEventList",indexService.getRecentEventList(4));
		model.addAttribute("user",new User());
		return "web/index/index";
	}
	
	
	
	
	@RequestMapping(value = "/getmessage", method = RequestMethod.GET)
	public @ResponseBody String getMessage() {
	  	logger.info("Accessing protected resource");
	   	return "ssssssssss";
	}
	
}
