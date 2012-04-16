package net.itsplace.web.controller;

import java.util.Locale;

import net.itsplace.web.service.SearchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/search")
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	@Autowired
	private SearchService searchService;
	
	/**
	 * 주변검색
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/place", method = RequestMethod.GET)
	public String place(Locale locale, Model model) {
		return "web/search/place";
	}
	
	/**
	 * 지도검색
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String map(Locale locale, Model model) {
		return "web/search/map";
	}
	
	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String event(Locale locale, Model model) {
		return "web/search/event";
	}
}
