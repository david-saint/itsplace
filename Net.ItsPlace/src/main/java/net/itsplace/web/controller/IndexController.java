package net.itsplace.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Message;
import net.itsplace.web.service.IndexService;
import net.itsplace.web.service.PlaceService;
import net.itsplace.web.service.SearchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String index(Locale locale, Model model) {
		model.addAttribute("recentPlaceList",indexService.getRecentPlaceList(4));
		model.addAttribute("recentEventList",indexService.getRecentEventList(4));
		return "web/index/index";
	}
	
	
	
	
	@RequestMapping(value = "/getmessage",  produces = "application/json")
	public @ResponseBody Message getMessage() {
	  	logger.info("Accessing protected resource");
	   	return new Message(100, "Congratulations!", "itsplace getmessage");
	}
	
}
