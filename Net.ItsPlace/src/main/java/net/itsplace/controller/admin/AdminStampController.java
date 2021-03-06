package net.itsplace.controller.admin;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.StampType;
import net.itsplace.domain.User;
import net.itsplace.domain.StampType.AddStampType;
import net.itsplace.domain.StampType.EditStampType;
import net.itsplace.domain.User.AddUser;
import net.itsplace.domain.User.EditUser;
import net.itsplace.service.StampBaseService;
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
@RequestMapping("/admin/stamp")
public class AdminStampController {
	private static final Logger logger = LoggerFactory.getLogger(AdminStampController.class);
	@Autowired
	private StampBaseService stampBaseService;
	@Autowired
	private PagingManager pagingManaer;
	/**
	 * 스템프관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		return "admin/stamp/list";
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
	@RequestMapping(value="/getStampTypeList")
    @ResponseBody
    public DataTable<StampType> getStampTypeList(
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
         
		String columns[] = new String[] { "sid", "title", "stampcount", "eventday",
										  "remark", "dispseq", "editDate", "isDelete" };
		JpaPaging paging = new JpaPaging(columns,iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0,sSearch);
        
	       
		return  stampBaseService.getStampTypeList(paging, true);
    }   
	/**
	 * 스탬프 타입 생성 폼을 호출한<br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  add.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("stampType",new StampType());	
		return "admin/stamp/add";
	}
	/**
	 * 스탬프 타입을 생성한다  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param StampType
	 * @return  admin/add/add.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
 	public String addSubmit(@Validated({AddStampType.class}) StampType stampType, BindingResult result, Model model) {
		
	  
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			return "admin/stamp/add";
		} else {	
			stampBaseService.saveStampType(stampType);		
			return "admin/stamp/list";
		}
	
	}
	/**
	 * 스탬프 타입 수정 폼을 호출한다.   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param sid 스탬프타입 Primary key
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required=true) Integer sid, Model model)  {
	
		model.addAttribute("stampType",stampBaseService.getStampType(sid));
			
		
		return "admin/stamp/edit";
	}
	/**
	 * Ajax StampType 수정한다  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param user
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody JsonResponse editSubmit(@Validated({EditStampType.class}) StampType stampType, BindingResult result, Model model)  {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {	
			stampBaseService.editStampType(stampType);
			json.setResult(stampType);
			json.setStatus("SUCCESS");
		}		
		return json;
	}
	/**
	 * Ajax 관리자가 스탬프 타입을 삭제한다  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param stamptype
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody JsonResponse delete(@RequestParam(required=true) Integer sid)  {
		logger.info("sid:{}",sid);
		JsonResponse json = new JsonResponse();		
		
		stampBaseService.deleteStampType(sid); 
			
		json.setResult(null);
		json.setStatus("SUCCESS");
		return json;
	}
	/**
	 * Ajax 관리자가 스탬프 타입을 복구한다  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param stamptype
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/restore", method = RequestMethod.POST)
	public @ResponseBody JsonResponse restore(@RequestParam(required=true) Integer sid)  {
		logger.info("sid:{}",sid);
		JsonResponse json = new JsonResponse();		
		
		stampBaseService.deleteStampType(sid); 
			
		json.setResult(null);
		json.setStatus("SUCCESS");
		return json;
	}
}
