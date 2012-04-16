package net.itsplace.web.controller;

import java.util.Locale;

import net.itsplace.web.service.SupportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/support")
public class SupportController {
	private static final Logger logger = LoggerFactory.getLogger(SupportController.class);
	@Autowired
	private SupportService supportService;
	
	/**
	 * 도움말
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help(Locale locale, Model model) {
		return "web/support/help";
	}
	
	/**
	 * 1:1 문의
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Locale locale, Model model) {
		return "web/support/write";
	}
	
	/**
	 * 나의 문의현황
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		return "web/support/list";
	}
}
