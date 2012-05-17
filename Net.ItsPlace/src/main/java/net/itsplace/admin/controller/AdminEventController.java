package net.itsplace.admin.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.itsplace.admin.service.AdminBaseService;
import net.itsplace.admin.service.AdminEventService;
import net.itsplace.admin.service.AdminPlaceService;
import net.itsplace.domain.Bascd;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.PlaceEvent.AddPlaceEvent;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Bascd.AddBascd;
import net.itsplace.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminEventController {
	private static final Logger logger = LoggerFactory.getLogger(AdminEventController.class);
	@Autowired
	private AdminEventService adminEventService;
	
	@Autowired
	private  AdminPlaceService adminPlaceService;
	
	/**
	 * 가맹점 이벤트관리
	 * @param locale
	 * @param model
	 * @return
	 */
	private Place place; // 선택된 가맹점 
	
	@RequestMapping(value = "/admin/place/event/add", method = RequestMethod.GET)
	public String add(@RequestParam(required=true) Integer fid,
						ModelMap model) {
		
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		place = adminPlaceService.getPlace(fid);
		model.addAttribute("placeEvent", new PlaceEvent());
		model.addAttribute("placeEventList",adminEventService.getPlaceEventList(fid));
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
	@RequestMapping(value = "/admin/place/event/add", method = RequestMethod.POST)
	@ResponseBody
 	public JsonResponse addSubmit(@Validated({AddPlaceEvent.class}) PlaceEvent placeEvent, BindingResult result, Model model) {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info("place:"+placeEvent.toString());
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {	
			placeEvent.setPlace(place);
			logger.info("placeEvent:"+placeEvent.getTitle());
			adminEventService.savePlaceEvent(placeEvent);
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
	@RequestMapping(value = "/admin/place/event/delete", method = RequestMethod.GET)
	@ResponseBody
 	public JsonResponse delete(@RequestParam(required=true) Integer eid, Model model) {
		JsonResponse json = new JsonResponse();
		try{
			adminEventService.deletePlaceEvent(eid);
			json.setStatus("SUCCESS");
		}catch(Exception e){
			json.setStatus("FAIL");
		}
		
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
	@RequestMapping(value="/admin/place/event/getPlaceEventList",method = RequestMethod.GET)
    @ResponseBody
    public DataTable<PlaceEvent> getPlaceEventList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false, defaultValue="10") Integer iDisplayLength,
    								@RequestParam(required=false, defaultValue="1") Integer iSortCol_0, 
    								@RequestParam(required=false, defaultValue="DESC" ) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch ) {

                  //  logger.info("languageHeader:{}", languageHeader.toString());
                    logger.info("iDisplayStart:{}", iDisplayStart.toString());
                    logger.info("sSortDir_0:{}", sSortDir_0);
                    logger.info("iSortCol_0:{}", iSortCol_0);
                    logger.info("iDisplayLength:{}", iDisplayLength);
                    logger.info("sSearch:{}", sSearch);
                  
                    String columns[] = new String[]{"title", "startDate", "endDate"};
                    
                    
                 
                   
                    return  adminEventService.getPlaceEventList(columns, iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0, sSearch, 46);
           
                   
    }       
}

