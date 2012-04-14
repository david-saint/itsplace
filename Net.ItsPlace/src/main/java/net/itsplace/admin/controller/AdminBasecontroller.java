package net.itsplace.admin.controller;


import java.util.Locale;

import net.itsplace.admin.service.AdminBaseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/base")
public class AdminBasecontroller {
	private static final Logger logger = LoggerFactory.getLogger(AdminBasecontroller.class);
	@Autowired
	private AdminBaseService adminBaseService;
	
	/**
	 * 기초코드
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String place(Locale locale, Model model) {
		return "admin/base/list";
	}
}
