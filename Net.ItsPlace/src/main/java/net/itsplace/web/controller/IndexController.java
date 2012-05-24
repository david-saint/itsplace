package net.itsplace.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import net.itsplace.domain.Message;
import net.itsplace.web.service.IndexService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private IndexService indexService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		
		return "web/index/index";
	}
	  @RequestMapping(value = "/getmessage",  produces = "application/json")
	    public @ResponseBody Message getMessage() {
	    	logger.info("Accessing protected resource");
	    	return new Message(100, "Congratulations!", "itsplace getmessage");
	    }
	
}
