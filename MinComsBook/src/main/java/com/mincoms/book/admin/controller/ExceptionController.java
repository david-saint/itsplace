package com.mincoms.book.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mincoms.book.admin.service.ExceptionService;
import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.Paging;

@Controller
public class ExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	@Autowired
	ExceptionService exceptionService;
	
	@RequestMapping(value = "/admin/exception/list", method = RequestMethod.GET)
	public String list(@RequestParam (value = "grpcd", required = false, defaultValue = "") String grpcd, Model model) {
		return "admin/exception/list";
	}
	
	/**
	 * <b>도서목록 Datatables</b> <br />
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
	@RequestMapping(value="/admin/exception/getExceptionList",method = RequestMethod.GET)
    @ResponseBody
    public DataTable<AppException> getExceptionList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false) Integer iDisplayLength,
    								@RequestParam(required=false, defaultValue="1") Integer iSortCol_0, 
    								@RequestParam(required=false, defaultValue="DESC" ) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch,
                                    @RequestParam(required=false, defaultValue="") boolean isCompleted,
									@RequestParam(required=false, defaultValue="") boolean isDeleted) {

                    logger.info("iDisplayStart:{}", iDisplayStart.toString());
                    logger.info("sSortDir_0:{}", sSortDir_0);
                    logger.info("iSortCol_0:{}", iSortCol_0);
                    logger.info("iDisplayLength:{}", iDisplayLength);
                    logger.info("sSearch:{}", sSearch);
                    logger.info("isCompleted:{}", isCompleted);
                    logger.info("isDeleted:{}", isDeleted);
                  
                    AppException appException = new AppException();
                    appException.setCompleted(isCompleted);
                    appException.setDeleted(isDeleted);
                    String columns[]={"id","message"};
                    Paging page = new Paging(columns,iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
                
                    logger.info(page.toString());
                    
                   return exceptionService.getExceptionList(page, appException);
           
                   
    }   
}
