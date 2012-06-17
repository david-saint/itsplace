package net.itsplace.place.controller;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceMenu.AddPlaceMenu;
import net.itsplace.domain.PlaceMenu.EditPlaceMenu;
import net.itsplace.domain.Stamp;
import net.itsplace.domain.PlaceEvent.AddPlaceEvent;
import net.itsplace.place.service.PlaceMenuService;
import net.itsplace.user.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PlaceMenuController {
	private static final Logger logger = LoggerFactory.getLogger(PlaceMenuController.class);
	
	@Autowired
	private PlaceMenuService placeMenuService;


	/**
	 * 가맹점 메뉴관리 <br> 
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid 가맹점 pk
	 * @return DataTables
	 * @throws 
	 * @see a
	 */
	@RequestMapping(value = "/place/menu/list", method = RequestMethod.GET)
	public String list(@RequestParam(required=false) Integer fid, Model model) {
		
		
		
		return "place/menu/list";
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
	@RequestMapping(value="/place/getMenuList",method = RequestMethod.GET)
    @ResponseBody
    public DataTable<Stamp> getMenuLList(
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
                    
                    
                 
                   
                    return  placeMenuService.getMenuList(columns,iDisplayStart,iDisplayLength,iSortCol_0,sSortDir_0,sSearch,UserInfo.getFid());
           
                   
    }       
	
	
	/**
	 * 가맹점 메뉴관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/place/menu/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		
		model.addAttribute("placeMenu", new PlaceMenu());

		return "place/menu/add";
	}
	/**
	 * 메뉴  생성 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/place/menu/add", method = RequestMethod.POST)
	@ResponseBody
 	public JsonResponse addSubmit(@Validated({AddPlaceMenu.class}) PlaceMenu placeMenu, BindingResult result, Model model) {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info("place:"+placeMenu.toString());
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getFieldError().getDefaultMessage());
			json.setStatus("FAIL");
		} else {	
			
			placeMenuService.saveMenu(placeMenu);
			json.setResult("메뉴를 추가하였씁니다");
			json.setStatus("SUCCESS");
		}
		return json;
	}
	/**
	 * 가맹점 메뉴 수정 폼 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/place/menu/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required=true) Integer mnid, ModelMap model) {
		PlaceMenu placeMenu = placeMenuService.getMenu(mnid);
		model.addAttribute("placeMenu", placeMenu);

		return "place/menu/edit";
	}
	/**
	 * 메뉴  수 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/place/menu/edit", method = RequestMethod.POST)
	@ResponseBody
 	public JsonResponse editSubmit(@Validated({EditPlaceMenu.class}) PlaceMenu placeMenu, BindingResult result, Model model) {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info("placeMenu:"+placeMenu.toString());
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {
			placeMenuService.editMenu(placeMenu);
			json.setResult("메뉴를 수정하였습니다");
			json.setStatus("SUCCESS");
		}
		return json;
	}
	
	/**
	 * 메뉴  삭제  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/place/menu/delete", method = RequestMethod.GET)
	@ResponseBody
 	public JsonResponse delete(@RequestParam(required=true) Integer mnid, Model model) {
		JsonResponse json = new JsonResponse();
		try{
			placeMenuService.deleteMenu(mnid);
			json.setStatus("SUCCESS");
		}catch(Exception e){
			json.setStatus("FAIL");
		}
		
		return json;
	}
}
