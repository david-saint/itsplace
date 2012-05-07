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
import net.itsplace.domain.Stamp;
import net.itsplace.place.service.PlaceStampService;
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
	 *  가맹점 스탬프 적립 및 소진     <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/q", method = RequestMethod.POST)
	public @ResponseBody JsonResponse saveorBurn(Stamp stamp,BindingResult result, Model model)  {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {	
			placeStampService.saveStamp(stamp);
			json.setResult(stamp);
			json.setStatus("SUCCESS");
			
		}		
		
		return json;
	}
	/**
	 * 회원검색  <br />
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
                  
                    /*User u = new User();
                    Field temp[] = u.getClass().getDeclaredFields();
                    String columns[] = new String[temp.length];
                    for(int i=0; i<temp.length;i++){
                    	logger.info(temp[i].getName());
                    	columns[i] = temp[i].getName();
                    }*/
                    String columns[] = new String[]{"profileImageUrl", "email", "name","role", "mobile", "isDelete", "isEmail", "saveDate", "editDate","dddd"};
                    
                    
                 
                   
                    return  placeStampService.getPlaceStampUserList(columns,iDisplayStart,iDisplayLength,iSortCol_0,sSortDir_0,sSearch);
           
                   
    }       
}
