package net.itsplace.module.event;

import java.util.Locale;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.service.PlaceService;
import net.itsplace.user.Authority;
import net.itsplace.user.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class EventController {
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	@Autowired
	PlaceEventService placeEventService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	private  PlaceService placeService;
	@Autowired
	JsonResponse json;
	
	/**
	 * 가맹점별 이벤트관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/partner/event/list", method = RequestMethod.GET)
	public String list(@RequestParam(required=false) Integer fid, ModelMap model) {
		if( Authority.ROLE_FRANCHISER.name().equals(UserInfo.getUser().getRole())){
			model.addAttribute("place",placeService.getPlace(UserInfo.getFid()));

		}else{
			
			model.addAttribute("place",placeService.getPlace(fid));
		}
		
		return "admin/place/event/list";
	}
	
	/**
	 * 가맹점 이벤트관리
	 * @param locale
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/partner/event/add", method = RequestMethod.GET)
	public String add(@RequestParam(required=false) Integer fid, ModelMap model) {
		//franchiser
		 
		if( Authority.ROLE_FRANCHISER.name().equals(UserInfo.getUser().getRole())){
			model.addAttribute("place",placeService.getPlace(UserInfo.getFid()));
		}else{
			model.addAttribute("place",placeService.getPlace(fid));
		}
				
		model.addAttribute("placeEvent", new PlaceEvent());

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
	@RequestMapping(value = "/partner/event/add", method = RequestMethod.POST)
	@ResponseBody
 	public JsonResponse addSubmit(@Validated PlaceEvent placeEvent, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			 logger.info("필드에러:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			 json =  json.getValidationErrorResult(result, json);
			
		}else{	
			Place place = placeService.getPlace(placeEvent.getPlace().getFid());
			placeEvent.setPlace(place);
			logger.info("placeEvent:"+placeEvent.getTitle());
			PlaceEvent saved = placeEventService.savePlaceEvent(placeEvent);
			json.setSuccess();
			json.setResult(messageSource.getMessage("register", new Object [] {saved.getTitle()} , Locale.getDefault()));
		
		}		
		return json;
	
	}
	/**
	 * 가맹점 이벤트 수정 폼 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/partner/event/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required=true) Integer eid, ModelMap model) {
		PlaceEvent placeEvent = placeEventService.getPlaceEvent(eid);
		model.addAttribute("placeEvent", placeEvent);

		return "admin/place/event/edit";
	}
	/**
	 * 이벤트  수 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/partner/event/edit", method = RequestMethod.POST)
	@ResponseBody
 	public JsonResponse editSubmit(@Validated PlaceEvent placeEvent, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.info("place:"+placeEvent.toString());
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {	
			placeEvent.setPlace(placeService.getPlace(placeEvent.getPlace().getFid()));
			logger.info("placeEvent:"+placeEvent.getTitle());
			placeEvent.setIsDelete(false);
			placeEventService.editPlaceEvent(placeEvent);
			json.setResult(placeEvent);
			json.setStatus("SUCCESS");
		}
		return json;
	}
	
	/**
	 * 이벤트  삭제  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/partner/event/delete", method = RequestMethod.POST)
	@ResponseBody
 	public JsonResponse delete(@RequestParam(required=true) Integer eid, Model model) {
		try{
			placeEventService.deletePlaceEvent(eid);
			json.setStatus("SUCCESS");
		}catch(Exception e){
			json.setStatus("FAIL");
		}
		
		return json;
	}
	/**
	 * 이벤트  삭제  복구 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  
	 * @throws Exception 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/partner/event/deleteRevoke", method = RequestMethod.POST)
	@ResponseBody
 	public JsonResponse deleteRevoke(@RequestParam(required=true) Integer eid, Model model)  {
		
		placeEventService.deleteRevokePlaceEvent(eid);
		json.setStatus("SUCCESS");
		
		return json;
	}
	
	/**
	 *   <br />
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
	//@RequestMapping(value="/admin/place/event/getPlaceEventList",method = RequestMethod.GET, headers="Accept=application/xml, application/json")
	@RequestMapping(value="/partner/event/getPlaceEventList",method = RequestMethod.GET)
    @ResponseBody
    public DataTable<PlaceEvent> getPlaceEventList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false, defaultValue="10") Integer iDisplayLength,
    								@RequestParam(required=false, defaultValue="1") Integer iSortCol_0, 
    								@RequestParam(required=false, defaultValue="DESC" ) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch,
                                    @RequestParam(required=false, defaultValue="0") Integer fid,
                                    @RequestParam(required=false, defaultValue="") Boolean isDelete,
                                    @RequestParam(required=false, defaultValue="") Boolean isAuth) {

                    logger.info("iDisplayStart:{}", iDisplayStart.toString());
                    logger.info("sSortDir_0:{}", sSortDir_0);
                    logger.info("iSortCol_0:{}", iSortCol_0);
                    logger.info("iDisplayLength:{}", iDisplayLength);
                    logger.info("sSearch:{}", sSearch);
                    logger.info("isDelete:{}", isDelete);
                  
                    String columns[] = new String[]{"title", "startDate", "endDate"};                                       
                    JpaPaging paging = new JpaPaging(columns,iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0,sSearch);
                    
                  //  service.findPlaceEventist(paging, true);
                   
                    return  placeEventService.findPlaceEvenList( paging, fid, isDelete, isAuth);
           
                   
    }   
	
}

