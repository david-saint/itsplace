package net.itsplace.admin.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.itsplace.admin.service.AdminPlaceService;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Place;
import net.itsplace.user.User;
import net.itsplace.util.PagingManager;

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
@RequestMapping("/admin/place")
public class AdminPlaceController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceController.class);
	@Autowired
	private AdminPlaceService adminPlaceService;
	@Autowired
	private PagingManager pagingManaer;
	
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
	 * @param locale
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
	 * Ajax 가맹점 승인  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid 가맹점 pk
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/enablePlace", method = RequestMethod.POST)
	public @ResponseBody JsonResponse enablePlace(@RequestParam(required=true) Integer fid)  {
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
	@RequestMapping(value = "/disablePlace", method = RequestMethod.POST)
	public @ResponseBody JsonResponse disablePlace(@RequestParam(required=true) Integer fid)  {
		logger.info("fid:{}",fid);
		JsonResponse json = new JsonResponse();		
		
		adminPlaceService.enablePlace(fid); 
			
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
										  "mobile", "authyn", "hdongname", "inpDate" };

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
