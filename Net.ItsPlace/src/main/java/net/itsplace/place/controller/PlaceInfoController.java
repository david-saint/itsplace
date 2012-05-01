package net.itsplace.place.controller;

import net.itsplace.admin.service.AdminBaseService;
import net.itsplace.admin.service.AdminPlaceService;
import net.itsplace.admin.service.AdminStampService;
import net.itsplace.user.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlaceInfoController {

	private static final Logger logger = LoggerFactory.getLogger(PlaceInfoController.class);
	@Autowired
	private AdminPlaceService adminPlaceService;
	private AdminStampService adminStampService;
	
	@RequestMapping(value = "/place/edit", method = RequestMethod.GET)
	public String placeInfo(Model model) {
		model.addAttribute("place",adminPlaceService.getPlace(UserInfo.getFid()));
		model.addAttribute("stampTypeList",adminStampService.getStampTypeListAll());
		
		return "place/edit";
	}
	
}
