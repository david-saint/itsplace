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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/base")
public class AdminBasecontroller {
	private static final Logger logger = LoggerFactory.getLogger(AdminBasecontroller.class);
	@Autowired
	private AdminBaseService adminBaseService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String base_list(
			@RequestParam (value = "grpCd", required = false, defaultValue = "") String grpCd,
			Model model
			) {
		model.addAttribute("grpBasCdList",adminBaseService.getGrpBascdList());
		model.addAttribute("basCdList", adminBaseService.getBascdList(grpCd));
		return "admin/base/list";
	}
}
