package net.itsplace.common;

import net.itsplace.admin.controller.AdminBasecontroller;
import net.itsplace.admin.service.AdminBaseService;
import net.itsplace.domain.Address;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.PlaceEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	@Autowired
	private CommonService commonService;
	
	/**
	 *  주소검색  <br />
	 *  지번검색 : 동명 번지수 또는 도로명검색
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/address/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTable<Address> list(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false, defaultValue="10") Integer iDisplayLength,
    								@RequestParam(required=false, defaultValue="1") Integer iSortCol_0, 
    								@RequestParam(required=false, defaultValue="DESC" ) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch ) {

       String columns[] = new String[]{"SIDO", "GUGUN", "BUPNAME"};
 
       return commonService.getAddressList(columns, iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
           
                   
    }   
}
