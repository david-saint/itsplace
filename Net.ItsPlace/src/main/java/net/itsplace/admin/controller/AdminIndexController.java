package net.itsplace.admin.controller;

import net.itsplace.admin.service.AdminBaseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminIndexController {

	private static final Logger logger = LoggerFactory.getLogger(AdminBasecontroller.class);
	@Autowired
	private AdminBaseService adminBaseService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String administrator() {

		return "admin/index";
	}
	
}
