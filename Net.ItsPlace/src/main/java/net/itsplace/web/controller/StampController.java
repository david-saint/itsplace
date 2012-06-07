package net.itsplace.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.itsplace.domain.JsonResponse;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String list(Model model)  {
		List<Place> placeStampedList =  stampService.getPlaceStampedList(UserInfo.getEmail());
		model.addAttribute("placeStampedList",placeStampedList);
		model.addAttribute("fname", placeStampedList.get(0).getFname());
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("fid", placeStampedList.get(0).getFid());
		
		param.put("email", UserInfo.getEmail());
		
		List<Stamped> stamppedListAll = placeStampService.getPlaceStampListByEmail(param);
		
		model.addAttribute("stamppedListAll",stamppedListAll);
		model.addAttribute("email",UserInfo.getEmail());
		return "web/stamp/list";
	}
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
	@RequestMapping(value = "/list/{fid}", method = RequestMethod.GET)
	public String stampped(@PathVariable Integer fid, Model model)  {
		
		List<Place> placeStampedList =  stampService.getPlaceStampedList(UserInfo.getEmail());
		model.addAttribute("placeStampedList",placeStampedList);
		for(int i=0; i<placeStampedList.size();i++){
			if(placeStampedList.get(i).getFid() == fid){
				model.addAttribute("fname", placeStampedList.get(i).getFname());
			}
		}
		
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fid", fid);
		param.put("email", UserInfo.getEmail());
		
		List<Stamped> stamppedListAll = placeStampService.getPlaceStampListByEmail(param);
		model.addAttribute("stamppedListAll",stamppedListAll);
		model.addAttribute("email",UserInfo.getEmail());
		return "web/stamp/list";
	}
	/**
	 * 스마트폰 적립된 스탬프 가맹점 리스트
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/placeStampList", method = RequestMethod.POST)
	public @ResponseBody JsonResponse  PlaceList(@RequestParam(required=true, defaultValue="1") String  email			 									 
			 									){
		logger.info("email:{}",email);
		JsonResponse json = new JsonResponse();
		try{
			
			json.setResult(stampService.getPlaceStampListByEmail(email));
			json.setStatus("SUCCESS");	
		}catch(Exception e){
			
			json.setResult(e.getLocalizedMessage());
			json.setStatus("FAIL");	
		}
		
		
		return json;
	}
	/**
	 * 스마트폰 적립된 스탬프 리스트 가맹점별 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/stamppedList", method = RequestMethod.POST)
	public @ResponseBody JsonResponse  stamppedList(@RequestParam(required=true, defaultValue="1") String  email,
													@RequestParam(required=true, defaultValue="1") String  fid,
													@RequestParam(required=true, defaultValue="1") String  stampid
			 									){
		logger.info("email:{}",email);
		logger.info("fid:{}",fid);
		logger.info("stampid:{}",stampid);
		JsonResponse json = new JsonResponse();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("email", email);
			param.put("fid", fid);
			param.put("stampid", stampid);
			json.setResult(placeStampService.getPlaceStampedListByEmail(param));
			json.setStatus("SUCCESS");	
		}catch(Exception e){
			
			json.setResult(e.getLocalizedMessage());
			json.setStatus("FAIL");	
		}
		
		
		return json;
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
	
	/**
	 * //최신의 stampid를 조회하여 적힙한다 그러나 현재적립중인 슽탬프가 있다면(유효기간내에 적립중입 스틈프id가 존재한다면) 해당 stampid에 적립한다.
	 * 
	 * 가맹점 관리자 인증코드 수정    
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param 
	 * @return 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody JsonResponse save(@RequestParam(required=true) String authcode,
										   @RequestParam(required=true) Integer stampid,
										   @RequestParam(required=true) String email
										  ) {
		JsonResponse json = new JsonResponse();
		try{
		
			logger.info("authcode:{}",authcode);	
			logger.info("선택된 가맹점 :{}",UserInfo.getFid());	
			logger.info("로그인한 사용자 :{}",UserInfo.getEmail());	
			
			
			Stamp stamp = new Stamp();
			stamp.getPlaceStamp().setStampid(stampid);
			stamp.getUser().setEmail(email);
			if(placeStampService.saveStamp(stamp,authcode)){
				json.setResult("스탬프를 적립하였습니다 !!");
				json.setStatus("SUCCESS");
			}else{
				json.setResult("인증코드가 유효하지 않습니다");
				json.setStatus("FAIL");
			}
		}catch(Exception e){
			logger.info(e.getMessage());
			json.setResult("스탬프 적립에 실패하였습니다.");
			json.setStatus("FAIL");
		}
			return json;
	}
	/**
	 * 스탬프 소 진       <br />
	 * ROLE_FRANCHISER 권한만 인증코드를 변경할 수 있습니다.
	 * 가맹점 관리자 인증코드 수정    
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param 
	 * @return 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/burn", method = RequestMethod.POST)
	public @ResponseBody JsonResponse burn(
										   @RequestParam(required=true) Integer stampid,
										   @RequestParam(required=true) String email,
										   @RequestParam(required=true) Integer pid
										  ) {
		
		logger.info(stampid+email+pid);
		JsonResponse json = new JsonResponse();
		try{
			Stamp stamp = new Stamp();
			stamp.getPlaceStamp().setStampid(stampid);
			stamp.setPid(pid);
			stamp.getUser().setEmail(email);
			if(placeStampService.burnStamp(stamp,null)){
				
				json.setResult("스탬프를 소진하였습니다 !!");
				json.setStatus("SUCCESS");
			}else{
				json.setResult("인증코드가 유효하지 않습니다");
				json.setStatus("FAIL");
			}
		}catch(Exception e){
			json.setResult("스탬프 소진에 실패하였습니다.");
			json.setStatus("FAIL");
		}
			return json;
	}
}
