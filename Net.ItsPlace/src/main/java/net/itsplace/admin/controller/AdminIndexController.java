package net.itsplace.admin.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.itsplace.domain.JsonResponse;
import net.itsplace.service.BaseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminIndexController {

	private static final Logger logger = LoggerFactory.getLogger(AdminBasecontroller.class);
	@Autowired
	private BaseService adminBaseService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String administrator() {

		return "admin/index";
	}

	@RequestMapping(value = "/admin/nfc", method = RequestMethod.GET)
	public String places(Locale locale, Model model, Device device,SitePreference sitePreference) {
		
		return "admin/nfc/list";
	}
	
	
}
