package net.itsplace.controller.partner;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceUser;
import net.itsplace.service.BaseService;
import net.itsplace.service.PlaceUserService;
import net.itsplace.user.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private BaseService commonService;
	
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
	@RequestMapping(value = "/partner", method = RequestMethod.GET)
	public String place(Model model,HttpServletRequest request) {
		List<PlaceUser>  users = placeUserService.getPlaceListByEmail(UserInfo.getEmail());
		
		List<Place> placeList = new ArrayList();
		
		for(PlaceUser u: users){
			placeList.add(u.getPlace());
		}

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
	@RequestMapping(value = "/partner/places", method = RequestMethod.GET)
	public @ResponseBody JsonResponse places()  {
		JsonResponse json = new JsonResponse();
		List<PlaceUser>  users = placeUserService.getPlaceListByEmail(UserInfo.getEmail());
		List<Place> placeList = new ArrayList();
		
		for(PlaceUser u: users){
			placeList.add(u.getPlace());
		}
		
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
	@RequestMapping(value = "/partner/changePlace", method = RequestMethod.POST)
	public @ResponseBody JsonResponse changePlace(@RequestParam(required=true) Integer fid)  {
		JsonResponse json = new JsonResponse();
		
		List<PlaceUser>  users = placeUserService.getPlaceListByEmail(UserInfo.getEmail());
		List<Place> placeList = new ArrayList();
		
		for(PlaceUser u: users){
			placeList.add(u.getPlace());
		}
		
		UserInfo.setFid(fid); // 현재선택된 가맹ㅇ점 
		UserInfo.setPlaceList(placeList);
		
		logger.info("가맹점 변경 : " + fid);
		json.setResult(null);
		json.setStatus("SUCCESS");
			
		return json;
	}
	
}
