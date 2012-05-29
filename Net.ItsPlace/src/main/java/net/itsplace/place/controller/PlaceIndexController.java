package net.itsplace.place.controller;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import net.itsplace.place.service.PlaceUserService;
import net.itsplace.user.User;
import net.itsplace.user.UserInfo;
import net.itsplace.user.User.EditUser;
import net.itsplace.util.PagingManager;

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
public class PlaceIndexController {

	private static final Logger logger = LoggerFactory.getLogger(PlaceIndexController.class);
	@Autowired
	private PlaceUserService placeUserService;
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 가맹점 관리 <br />
	 * ROLE_FRANCHISER, ROLE_FID 권한 펼요
	 * 가맹점 관리자가 소유하고 있는 가맹점 리스   
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param 
	 * @return 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/place", method = RequestMethod.GET)
	public String place(Model model,HttpServletRequest request) {
		List<Place> placeList = placeUserService.getFranchiserListByEmail(UserInfo.getEmail());

		if(UserInfo.getFid()==0){
			UserInfo.setFid(placeList.get(0).getFid());
		}
		UserInfo.setPlaceList(placeList);
		
			for(int i=0;i<placeList.size();i++){
				logger.info(placeList.get(i).getFname());
			}
		/*if(UserInfo.getUser().getRole().equals(UserInfo.ROLE_FRANCHISER)){
			model.addAttribute("placeList",placeUserService.getFranchiserListByEmail(UserInfo.getEmail()));
		}else{
			model.addAttribute("placeList",placeUserService.getPlaceListByEmail(UserInfo.getEmail()));
		}*/
		
		return "place/index/index";
	}
	/**
	 * 가맹점 리스트   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/places", method = RequestMethod.GET)
	public @ResponseBody JsonResponse places()  {
		JsonResponse json = new JsonResponse();
		List<Place> placeList = placeUserService.getFranchiserListByEmail(UserInfo.getEmail());
		
		if(placeList.size()==1){
			UserInfo.setFid(placeList.get(0).getFid());
		}else{
			
		}
		
		json.setResult(placeList);
		json.setStatus("SUCCESS");
			
		return json;
	}
	
	/**
	 * 가맹점 선택    <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid 가맹정 코
	 * @return 
	 * @return json
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/changePlace", method = RequestMethod.POST)
	public @ResponseBody JsonResponse changePlace(@RequestParam(required=true) Integer fid)  {
		JsonResponse json = new JsonResponse();
		List<Place> placeList = placeUserService.getFranchiserListByEmail(UserInfo.getEmail());		
		UserInfo.setFid(fid); // 현재선택된 가맹ㅇ점 
		UserInfo.setPlaceList(placeList);
		
		logger.info("가맹점 변경 : " + fid);
		json.setResult(null);
		json.setStatus("SUCCESS");
			
		return json;
	}
	
}
