package com.mincoms.book.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mincoms.book.admin.service.StaticsService;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.dto.DtoRentalStatics;
import com.mincoms.book.domain.vo.VoBookRestriction;
import com.mincoms.book.repository.DeptRepository;
import com.mincoms.book.service.BookService;
import com.mincoms.book.service.CategoryService;
import com.mincoms.book.service.UserService;

@Controller
public class StaticsController {
	private static final Logger logger = LoggerFactory.getLogger(StaticsController.class);
	@Autowired
	MessageSource messagesource;
	@Autowired
	StaticsService staticsService;
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	@Autowired
	DeptRepository deptRepo;
	@Autowired
	JsonResponse json;
	
	
	@RequestMapping(value = "/admin/statics/rentals", method = RequestMethod.GET)
	public String rentals(Model model)  {
	
		model.addAttribute("deptList",deptRepo.findByIsDeleted(false));
		
		return "admin/statics/rentals";
	}
	
	/**
	 * <b>도서대출 정지자 목록 Datatables</b> <br />
	 * <pre>
	 * <b>History:</b>
	 *     version 1.0, 2012.9.3 검색
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 8. 24
	 * @param iDisplayStart 페이지 번
	 * @param iDisplayLength 페이지 로우수 (한페이지에 보여줄 로우수)
	 * @param iSortCol_0 sort할 컬럼 번호 
	 * @param sSortDir_0 sort할 방향(asc/desc)
	 * @param sSearch 검색
	 * @return DataTables
	 * @return book/add.jsp 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value="/admin/statics/getRentalList",method = RequestMethod.GET)
    @ResponseBody
    public DataTable<DtoRentalStatics> getRestrictionUserList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false) Integer iDisplayLength,
    								@RequestParam(required=false, defaultValue="1") Integer iSortCol_0, 
    								@RequestParam(required=false, defaultValue="DESC" ) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch,
                                    @RequestParam(required=false, defaultValue="") String startDate,
                                    @RequestParam(required=false, defaultValue="") String endDate,
                                    @RequestParam(required=false, defaultValue="") boolean isRental,
                                    @RequestParam(required=false, defaultValue="") boolean badGuys,
                                    @RequestParam(required=false, defaultValue="") Integer deptId,
                                    @RequestParam(required=false, defaultValue="") Integer userId) {

                    logger.info("iDisplayStart:{}", iDisplayStart.toString());
                    logger.info("sSortDir_0:{}", sSortDir_0);
                    logger.info("iSortCol_0:{}", iSortCol_0);
                    logger.info("iDisplayLength:{}", iDisplayLength);
                    logger.info("sSearch:{}", sSearch);
                    logger.info("isRental:{}",isRental);
                    logger.info("deptId:{}", deptId);
                    logger.info("userId:{}", userId);
                    logger.info("startDate:{}", startDate);
                    logger.info("endDate:{}", endDate);
                    logger.info("badGuys:{}", badGuys);
                
                  
                    String columns[]={"", "title", "G.DeptName", "userRname","B.startDate","B.endDate", "B.returnDate"};
                    Paging paging = new Paging(columns,iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
                    Map<String, Object> parameter = new HashMap<String, Object>();
                    parameter.put("deptId", deptId);
                    parameter.put("userId", userId);
                    parameter.put("sSearch", sSearch);
                    parameter.put("isRental", isRental);
                    parameter.put("badGuys", badGuys);
                    if(!startDate.equals("") && !endDate.equals("")){
                    	parameter.put("startDate", startDate + " 00:00:00");
                        parameter.put("endDate", endDate + " 23:59:59");
                    }
                    
                    paging.setParameter(parameter);
                    logger.info(paging.toString());
                  
                    
                    return staticsService.findRentalStatics(paging);
    }   
}
