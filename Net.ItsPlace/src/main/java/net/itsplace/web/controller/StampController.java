package net.itsplace.web.controller;

import java.util.Locale;

import net.itsplace.web.service.StampService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/stamp")
public class StampController {
	private static final Logger logger = LoggerFactory.getLogger(StampController.class);
	@Autowired
	private StampService stampService;
	
	/**
	 * 스템프확인
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		return "web/stamp/list";
	}
	
	/**
	 * 즐겨찾기
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/favorite", method = RequestMethod.GET)
	public String favorite(Locale locale, Model model) {
		return "web/stamp/favorite";
	}
}
