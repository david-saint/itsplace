package net.itsplace.admin.controller;

import java.util.Locale;

import net.itsplace.admin.service.AdminBaseService;
import net.itsplace.admin.service.AdminEventService;
import net.itsplace.admin.service.AdminPlaceService;
import net.itsplace.domain.Bascd;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.PlaceEvent.AddPlaceEvent;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Bascd.AddBascd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminEventController {
	private static final Logger logger = LoggerFactory.getLogger(AdminEventController.class);
	@Autowired
	private AdminEventService adminEventService;
	
	@Autowired
	private  AdminPlaceService adminPlaceService;
	
	/**
	 * 가맹점 이벤트관리
	 * @param locale
	 * @param model
	 * @return
	 */
	private Place place;
	@RequestMapping(value = "/admin/place/event/add", method = RequestMethod.GET)
	public String add(@RequestParam(required=true) Integer fid,
						ModelMap model) {
		
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		place = adminPlaceService.getPlace(fid);
		model.addAttribute("placeEvent", new PlaceEvent());
		model.addAttribute("placeEventList",adminEventService.getPlaceEventList(fid));
		return "admin/place/event/add";
	}
	/**
	 * 이벤트  생성 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/admin/place/event/add", method = RequestMethod.POST)
 	public String addSubmit(@Validated({AddPlaceEvent.class}) PlaceEvent placeEvent, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			logger.info("place:"+placeEvent.toString());
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			
			return "admin/base/add";
		} else {	
			logger.info("placeEvent:"+placeEvent.getTitle());
			adminEventService.savePlaceEvent(placeEvent);
			return "redirect:/admin/place/event/add?fid="+placeEvent.getPlace().getFid();
		}
	
	}
}
