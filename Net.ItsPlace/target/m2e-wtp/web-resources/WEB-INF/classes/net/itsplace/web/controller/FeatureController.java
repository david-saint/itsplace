package net.itsplace.web.controller;

import java.util.Locale;

import net.itsplace.web.service.FeatureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/feature")
public class FeatureController {
	private static final Logger logger = LoggerFactory.getLogger(FeatureController.class);
	@Autowired
	private FeatureService featureService;
	
	/**
	 * 서비스소개
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/feature", method = RequestMethod.GET)
	public String feature(Locale locale, Model model) {
		return "web/feature/feature";
	}
}
