package net.itsplace.place.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.itsplace.admin.service.AdminStampService;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.PlaceStamp.AddPlaceStamp;
import net.itsplace.domain.PlaceStamp.EditPlaceStamp;
import net.itsplace.domain.Stamp;
import net.itsplace.domain.Stamped;
import net.itsplace.place.service.PlaceStampService;
import net.itsplace.service.BaseServiceImpl;
import net.itsplace.service.PlaceService;
import net.itsplace.user.UserInfo;
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
@RequestMapping("/place/stamp")
public class PlaceStampController {
	private static final Logger logger = LoggerFactory.getLogger(PlaceStampController.class);
	@Autowired
	private PlaceService adminPlaceService;
	@Autowired
	private AdminStampService adminStampService;
	@Autowired
	private PagingManager pagingManaer;
	@Autowired
	private BaseServiceImpl commonService;
	
	@Autowired
	private PlaceStampService placeStampService;
	
	


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
		
		//model.addAttribute("placeStampList",adminStampService.getPlaceStampAll(fid));
		//Map<String, Object> param = new HashMap<String, Object>();
		//param.put("fid", UserInfo.getFid());
		
		model.addAttribute("placeStampList",placeStampService.getPlaceStampList(UserInfo.getFid()));	
		
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
	/**
	 *  가맹점 스탬프 수정    <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String placeStamp(@RequestParam(required=true) Integer fid,
							 @RequestParam(required=true) Integer stampid,
							 Model model)  {
		//List<PlaceStamp> placeStampList = adminStampService.getPlaceStampAll(fid);
		
		
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		model.addAttribute("stampTypeList",adminStampService.getStampTypeListAll());
		model.addAttribute("themeList",commonService.getBascdList("STAMPTHEME"));
		//List<PlaceStamp> placeStampList = adminStampService.getPlaceStampAll(fid);
		/*if(placeStampList.size()<=0){
			model.addAttribute("placeStampList",null);
			model.addAttribute("placeStamp", new PlaceStamp());
		}else{
			model.addAttribute("placeStampList",placeStampList);
			model.addAttribute("placeStamp", placeStampList.get(0));
		}*/
		model.addAttribute("placeStamp",adminStampService.getPlaceStamp(stampid));
		return "place/stamp/edit";
	}
	/**
	 *  가맹점 스탬프를 수정한다.   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody JsonResponse placeStampSubmit(@Validated({EditPlaceStamp.class}) PlaceStamp placeStamp,BindingResult result, Model model)  {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {	
			adminStampService.editPlaceStamp(placeStamp);
			json.setResult(placeStamp);
			json.setStatus("SUCCESS");
			
		}		
		
		return json;
	}
	/**
	 *  가맹점 스탬프를 삭제   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody JsonResponse placeStampDelete(@RequestParam(required=true) Integer stampid)  {
		JsonResponse json = new JsonResponse();
		PlaceStamp placeStamp = new PlaceStamp();
		placeStamp.setStampid(stampid);
		adminStampService.deletePlaceStamp(placeStamp);
		json.setResult("스탬프가 삭제되었습니다");
		json.setStatus("SUCCESS");
		return json;
	}
	/**
	 *  가맹점 스탬프 적립 및 소진     <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/q", method = RequestMethod.GET)
	public String save(Model model)  {
		model.addAttribute("Stamp", new Stamp());
		return "place/stamp/q";
	}
	
	/**
	 * 가맹점 회원검색  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param iDisplayStart 페이지 번
	 * @param iDisplayLength 페이지 로우수 (한페이지에 보여줄 로우수)
	 * @param iSortCol_0 sort할 컬럼 번호 
	 * @param sSortDir_0 sort할 방향(asc/desc)
	 * @param sSearch 검색
	 * @return DataTables
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/getPlaceStampUserList")
    @ResponseBody
    public DataTable<Stamp> getPlaceStampUserList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false) Integer iDisplayLength,
    								@RequestParam(required=false) Integer iSortCol_0, 
    								@RequestParam(required=false) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch ) {

                    logger.info("iDisplayStart:{}", iDisplayStart.toString());
                    logger.info("sSortDir_0:{}", sSortDir_0);
                    logger.info("iSortCol_0:{}", iSortCol_0);
                    logger.info("iDisplayLength:{}", iDisplayLength);
                    logger.info("sSearch:{}", sSearch);
                  
                    //B.stampedTotal, B.stampedLastDate, A.PROFILEIMAGEURL, A.EMAIL, A.NAME, A.MOBILE
                    String columns[] = new String[]{"profileImageUrl", "email", "name", "mobile", "stampedTotal", "stampedLastDate"};
                    
                    
                 
                   
                    return  placeStampService.getPlaceStampUserList(columns,iDisplayStart,iDisplayLength,iSortCol_0,sSortDir_0,sSearch);
           
                   
    }       

	/**
	 *   스탬프 적립및소진 사용중 스탬프 리스트  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param email
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stampped", method = RequestMethod.GET)
	public String burn(@RequestParam(required=true) String email,Model model)  {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fid", UserInfo.getFid());
		param.put("email", email);
		
		List<Stamped> stamppedListAll = placeStampService.getPlaceStampListByEmail(param);
		
		model.addAttribute("stamppedListAll",stamppedListAll);
		
		if(stamppedListAll != null && stamppedListAll.size()>0){
		//	model.addAttribute("stampid",stamppedListAll.get(0).getPlaceStamp().getStampid());// 적립할 최신 스탬프아이디
		}else{
		}
		List<PlaceStamp> placeStampList = placeStampService.getPlaceStampList(UserInfo.getFid());
		model.addAttribute("stampid",placeStampList.get(0).getStampid());//현재 선택된 가맹점의 사용중인 스탬프 아이 
		model.addAttribute("email",email);
		return "place/stamp/stampped";
	}
	/**
	 * 가맬점 관리자가 인증코드로 스탬프 적립       <br />
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
