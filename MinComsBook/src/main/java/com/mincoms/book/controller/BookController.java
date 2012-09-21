package com.mincoms.book.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mincoms.book.admin.controller.AdminController;
import com.mincoms.book.api.Google;
import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategoryRoot;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.BookInfo.AddBook;
import com.mincoms.book.domain.BookInfo.EditBook;
import com.mincoms.book.domain.VoBookInfo;
import com.mincoms.book.domain.dto.DtoBookInfo;
import com.mincoms.book.service.BookService;
import com.mincoms.book.service.CategoryService;

@Controller
public class BookController { 
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	@Autowired
	MessageSource messagesource;
	@Autowired
	BookService bookService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	JsonResponse json;
	
	
	@RequestMapping(value = "/book/search", method = RequestMethod.GET)
	public String search(Model model) {
		model.addAttribute("categoryRootList", categoryService.findByBookCategoryRoot());
		return "book/search";
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
	@RequestMapping(value="/book/getReservationGroupByBooks",method = RequestMethod.GET)
	@ResponseBody
	public DataTable<DtoBookInfo> getReservationGroupByBooks(
									@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
									@RequestParam(required=false) Integer iDisplayLength,
									@RequestParam(required=false, defaultValue="1") Integer iSortCol_0, 
									@RequestParam(required=false, defaultValue="DESC" ) String sSortDir_0, 
	                                @RequestParam(required=false, defaultValue="") String sSearch,
	                                @RequestParam(required=false, defaultValue="0") Integer bookCategoryRoot,
									@RequestParam(required=false, defaultValue="0") Integer bookCategory) {
	
	             
	                logger.info("bookCategoryRoot:{}", bookCategoryRoot);
	                logger.info("bookCategory:{}", bookCategory);
	              
	                String columns[] = new String[]{"regDate", "title", "authors"};
	             
	                Map<String, Object> parameter = new HashMap<String, Object>();
	                parameter.put("bookCategoryRoot", bookCategoryRoot);
	                parameter.put("bookCategory", bookCategory);                           
	                Paging paging = new Paging(columns,iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
	                paging.setParameter(parameter);
	               
	                logger.info(paging.toString());
	                logger.info("ddddddddddddddd:"+paging.getCurrentPage());
	                
	               return bookService.getReservationGroupByBooks(paging);
	       
	               
	}   

}
