package net.itsplace.admin.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.itsplace.admin.service.AdminPlaceService;
import net.itsplace.admin.service.AdminStampService;
import net.itsplace.common.CommonService;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Place;
import net.itsplace.domain.Place.AddPlace;
import net.itsplace.domain.Place.EditPlace;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.PlaceStamp.AddPlaceStamp;
import net.itsplace.domain.PlaceStamp.EditPlaceStamp;
import net.itsplace.user.User;
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
@RequestMapping("/admin/place")
public class AdminPlaceController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceController.class);
	@Autowired
	private AdminPlaceService adminPlaceService;
	@Autowired
	private AdminStampService adminStampService;
	@Autowired
	private PagingManager pagingManaer;
	@Autowired
	private CommonService commonService;
	/**
	 * 가맹점 승인관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public String place(Locale locale, Model model) {
		return "admin/place/request";
	}
	
	/**
	 * 가맹점 삭제/등록
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		return "admin/place/list";
	}
	
	/**
	 * 가맹점별 회원관리
	 * @param locale jhgjhgjhgjhg
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String userList(Locale locale, Model model) {
		return "admin/place/user/list";
	}
	
	/**
	 * 가맹점 이벤트관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/event", method = RequestMethod.GET)
	public String event(Locale locale, Model model) {
		return "admin/place/user/event";
	}
	/**
	 * 관리자가 가맹점 스탬프 수정    <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stamp/edit", method = RequestMethod.GET)
	public String placeStamp(@RequestParam(required=true) Integer fid, Model model)  {
		//List<PlaceStamp> placeStampList = adminStampService.getPlaceStampAll(fid);
		
		
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		model.addAttribute("stampTypeList",adminStampService.getStampTypeListAll());
		model.addAttribute("themeList",commonService.getBascdList("STAMPTHEME"));
		List<PlaceStamp> placeStampList = adminStampService.getPlaceStampAll(fid);
		if(placeStampList.size()<=0){
			model.addAttribute("placeStampList",null);
			model.addAttribute("placeStamp", new PlaceStamp());
		}else{
			model.addAttribute("placeStampList",placeStampList);
			model.addAttribute("placeStamp", placeStampList.get(0));
		}
		
		return "admin/place/stamp/edit";
	}
	/**
	 * 관리자가 가맹점 스탬프를 수정한다.   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stamp/edit", method = RequestMethod.POST)
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
	 * 관리자가 가맹점 스탬프 등록     <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stamp/add", method = RequestMethod.GET)
	public String placeStampAdd(@RequestParam(required=true) Integer fid, Model model)  {
		
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		
		model.addAttribute("placeStamp", new PlaceStamp());
		model.addAttribute("stampTypeList",adminStampService.getStampTypeListAll());
		model.addAttribute("themeList",commonService.getBascdList("STAMPTHEME"));
		List<PlaceStamp> placeStampList = adminStampService.getPlaceStampAll(fid);
	
		model.addAttribute("placeStampList",adminStampService.getPlaceStampAll(fid));			
		
		
		return "admin/place/stamp/add";
	}
	/**
	 * 관리자가 가맹점 스탬프를 등록한.   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stamp/add", method = RequestMethod.POST)
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
	 * 관리자가 가맹점을 생성한다    <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model Place
	 * @return  add.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model)  {
	
		model.addAttribute("place",new Place());
		
		return "admin/place/add";
	}
	/**
	 * Ajax 관리자가 가맹점을 등록한다. <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param user
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addSubmit(@Validated({AddPlace.class}) Place place, BindingResult result, Model model)  {
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			return "admin/place/add";
		} else {	
			adminPlaceService.savePlace(place)	;	
			return "admin/place/list";
		}	
	}
	/**
	 * 관리자가 가맹점 수정 폼을 호출한다.   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required=true) Integer fid, Model model)  {
	
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		model.addAttribute("stampTypeList",adminStampService.getStampTypeListAll());
		
		return "admin/place/edit";
	}
	/**
	 * Ajax 관리자가 가맹점을 수정한다. <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param user
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody JsonResponse editSubmit(@Validated({EditPlace.class}) Place place, BindingResult result, Model model)  {
		JsonResponse json = new JsonResponse();
		System.out.println("edit");
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {	
			adminPlaceService.editPlace(place);
			json.setResult(place);
			json.setStatus("SUCCESS");
			
		}		
		
		return json;
	}
	/**
	 * Ajax 가맹점 승인 하고 인증코드를 0000으로 초기화한다 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid 가맹점 pk
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody JsonResponse enable(@RequestParam(required=true) Integer fid)  {
		logger.info("fid:{}",fid);
		JsonResponse json = new JsonResponse();		
		
		adminPlaceService.enablePlace(fid); 
			
		json.setResult(null);
		json.setStatus("SUCCESS");
		return json;
	}
	/**
	 * Ajax 가맹점 승인  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid 가맹점 pk
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public @ResponseBody JsonResponse disable(@RequestParam(required=true) Integer fid)  {
		logger.info("fid:{}",fid);
		JsonResponse json = new JsonResponse();		
		
		adminPlaceService.disablePlace(fid); 
			
		json.setResult(null);
		json.setStatus("SUCCESS");
		return json;
	}
	/**
	 * 가맹점관리 리스트<br> 
	 * 관리자는 가맹점 승인, 승인취소, <br />
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
	@RequestMapping(value="/getPlaceList")
    @ResponseBody
    public DataTable<Place> getPlaceList(
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
         
		String columns[] = new String[] { "fid", "fileName", "fname", "name",
										  "mobile", "isAuth", "hdongname", "saveDate", "editDate" };

		DataTable<Place> table = iDisplayLength != null ?
                new DataTable<Place>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                new DataTable<Place>(columns, sSortDir_0, iDisplayStart);

		Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
        param.put("search", sSearch);
        param.put("sortDirection", sSortDir_0);
        param.put("sortColumn", table.getOrderColumn(iSortCol_0));
		
		List<Place> franchiserList = adminPlaceService.getPlaceList(param);
		pagingManaer.setTotalCount(pagingManaer.getFoundRows());
		table.setRows(franchiserList);
		table.setiTotalDisplayRecords(pagingManaer.getTotalCount());

		return table;
    }    
}
