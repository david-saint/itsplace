package net.itsplace.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Stamp;
import net.itsplace.domain.Stamped;
import net.itsplace.place.service.PlaceStampService;
import net.itsplace.user.UserInfo;
import net.itsplace.web.service.StampService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stamp")
public class StampController {
	private static final Logger logger = LoggerFactory.getLogger(StampController.class);
	@Autowired
	private StampService stampService;
	
	@Autowired
	private PlaceStampService placeStampService;
	

	/**
	 *   적립된  스탬프 리스트  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String stampped(@RequestParam(required=false, defaultValue="0") Integer fid, Model model)  {
		List<Place> placeStampedList =  stampService.getPlaceStampedList(UserInfo.getEmail());
		model.addAttribute("placeStampedList",placeStampedList);
		if(placeStampedList.size()>0 && fid==0){
			fid = placeStampedList.get(0).getFid();
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fid", fid);
		param.put("email", UserInfo.getEmail());
		
	
		
		List<Stamped> stamppedListAll = placeStampService.getPlaceStampListByEmail(param);
		
		model.addAttribute("stamppedListAll",stamppedListAll);
		//model.addAttribute("stampid",placeStampList.get(0).getStampid());// 적립할 최신 스탬프아이디
		model.addAttribute("email",UserInfo.getEmail());
		return "web/stamp/list";
	}
	/**
	 * 즐겨찾기
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/favorite", method = RequestMethod.GET)
	public String favorite(Locale locale, Model model) {
		return "web/stamp/favorite";
	}
}
