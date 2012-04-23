package net.itsplace.admin.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.itsplace.admin.service.AdminStampService;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.Franchiser;

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
@RequestMapping("/admin/stamp")
public class AdminStampController {
	private static final Logger logger = LoggerFactory.getLogger(AdminStampController.class);
	@Autowired
	private AdminStampService adminStampService;
	
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
	@RequestMapping(value="/getStampList")
    @ResponseBody
    public DataTable<Franchiser> getPlaceList(
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

		DataTable<Franchiser> table = iDisplayLength != null ?
                new DataTable<Franchiser>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                new DataTable<Franchiser>(columns, sSortDir_0, iDisplayStart);

		Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
        param.put("search", sSearch);
        param.put("sortDirection", sSortDir_0);
        param.put("sortColumn", table.getOrderColumn(iSortCol_0));
		
		List<Franchiser> franchiserList = adminPlaceService.getFranchiserList(param);
		pagingManaer.setTotalCount(pagingManaer.getFoundRows());
		table.setRows(franchiserList);
		table.setiTotalDisplayRecords(pagingManaer.getTotalCount());

		return table;
    }   
}
