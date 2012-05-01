package net.itsplace.place.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.itsplace.admin.service.AdminPlaceService;
import net.itsplace.admin.service.AdminStampService;
import net.itsplace.common.CommonService;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Place;
import net.itsplace.domain.Place.EditPlace;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.PlaceStamp.AddPlaceStamp;
import net.itsplace.domain.PlaceStamp.EditPlaceStamp;
import net.itsplace.user.User;
import net.itsplace.user.UserInfo;
import net.itsplace.user.User.EditUser;
import net.itsplace.util.PagingManager;
import net.itsplace.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/place/stamp")
public class PlaceStampController {
	private static final Logger logger = LoggerFactory.getLogger(PlaceStampController.class);
	@Autowired
	private AdminPlaceService adminPlaceService;
	@Autowired
	private AdminStampService adminStampService;
	@Autowired
	private PagingManager pagingManaer;
	@Autowired
	private CommonService commonService;
	
	


	/**
	 * 가맹점 스탬프관리 -> 스탬프 리스<br> 
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid 가맹점 pk
	 * @return DataTables
	 * @throws 
	 * @see a
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(required=false) Integer fid, Model model) {
		if(StringUtil.isNull(fid)){
			fid = UserInfo.getFid();
		}
		model.addAttribute("placeStampList",adminStampService.getPlaceStampAll(fid));	
		
		return "place/stamp/list";
	}
	
	/**
	 *  가맹점 스탬프 등록     <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String placeStampAdd(Model model)  {
		
		model.addAttribute("place",adminPlaceService.getPlace(UserInfo.getFid()));
		
		model.addAttribute("placeStamp", new PlaceStamp());
		model.addAttribute("stampTypeList",adminStampService.getStampTypeListAll());
		model.addAttribute("themeList",commonService.getBascdList("STAMPTHEME"));
	
		
		
		return "place/stamp/add";
	}
	/**
	 *  가맹점 스탬프를 등록한.   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JsonResponse placeStampAdd(@Validated({AddPlaceStamp.class}) PlaceStamp placeStamp,BindingResult result, Model model)  {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {	
			adminStampService.savePlaceStamp(placeStamp);
			json.setResult(placeStamp);
			json.setStatus("SUCCESS");
			
		}		
		
		return json;
	}
}
