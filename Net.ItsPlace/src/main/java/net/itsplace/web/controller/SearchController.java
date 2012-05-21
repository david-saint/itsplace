package net.itsplace.web.controller;

import java.util.List;
import java.util.Locale;

import net.itsplace.domain.Place;
import net.itsplace.util.Paging;
import net.itsplace.web.service.SearchService;
import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/search")
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	@Autowired
	private SearchService searchService;
	@Autowired
	private Paging page;
	
	/**
	 * 주변검색
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/place", method = RequestMethod.GET)
	public String place(Locale locale, Model model, @ModelAttribute Place p) {
		JSONArray array = new JSONArray();
		p.setStart(p.getPageSize() * (p.getCurrentPage() - 1));
		array = JSONArray.fromObject(searchService.placeInfo(p));
		String pageHTML = page.init(p.getCurrentPage(), p.getPageSize(), p.getPageBlock(), "PLACE", null);
		
		model.addAttribute("list", array);
		model.addAttribute("page", pageHTML);
		model.addAttribute("place", p);
		return "web/search/place";
	}
	
	/**
	 * 주변검색
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/placeAjax", method = RequestMethod.POST,  headers="Accept=application/json")
	public @ResponseBody List<Place>  placeAjax(Locale locale, Model model, @ModelAttribute Place p) {
		JSONArray array = new JSONArray();
		p.setStart(p.getPageSize() * (p.getCurrentPage() - 1));
		return searchService.placeInfo(p);
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
