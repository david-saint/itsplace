package net.itsplace.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class TestController {
	@RequestMapping(value = "/test/daum", method = RequestMethod.GET)
	public String base_list(Model model) {
	
		return "admin/test";
	}
}
