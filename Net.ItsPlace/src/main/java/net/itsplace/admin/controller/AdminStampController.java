package net.itsplace.admin.controller;

import java.util.Locale;

import net.itsplace.admin.service.AdminStampService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/stamp")
public class AdminStampController {
	private static final Logger logger = LoggerFactory.getLogger(AdminStampController.class);
	@Autowired
	private AdminStampService adminStampService;
	
	/**
	 * 스템프관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		return "admin/stamp/list";
	}
}
