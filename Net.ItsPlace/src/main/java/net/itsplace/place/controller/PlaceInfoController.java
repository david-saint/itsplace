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
	@Autowired
	private AdminStampService adminStampService;
	
	@RequestMapping(value = "/place/edit", method = RequestMethod.GET)
	public String placeInfo(Model model) {
		model.addAttribute("place",adminPlaceService.getPlace(UserInfo.getFid()));
		model.addAttribute("stampTypeList",adminStampService.getStampTypeListAll());
		
		return "place/edit";
	}

	/**
	 * 인증코드  관리폼 팝업   <br />
	 * ROLE_FRANCHISER
	 * 가맹점 관리자 인증코드 수정    
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param 
	 * @return 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/place/auth", method = RequestMethod.GET)
	public String auth(Model model) {
		
		return "place/auth";
	}
	/**
	 * 인증코드 발급   <br />
	 * ROLE_FRANCHISER
	 * 가맹점 관리자 인증코드 수정    
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param 
	 * @return 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/place/auth", method = RequestMethod.POST)
	public String authSubmit(Model model) {
		
		return "place/auth";
	}
}
