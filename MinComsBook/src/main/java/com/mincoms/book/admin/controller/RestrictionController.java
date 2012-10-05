package com.mincoms.book.admin.controller;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
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

import com.mincoms.book.admin.repository.BaseCodeRepository;
import com.mincoms.book.admin.service.RestrictionService;
import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BaseCode;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.BookInfo.AddBook;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.domain.dto.DtoBookRestriction;

import com.mincoms.book.domain.vo.VoBookRestriction;
import com.mincoms.book.service.BookService;
import com.mincoms.book.service.CategoryService;
@Validated
@Controller
public class RestrictionController {
	private static final Logger logger = LoggerFactory.getLogger(RestrictionController.class);
	@Autowired
	MessageSource messagesource;
	@Autowired
	BookService bookService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BaseCodeRepository baseCodeRepository;
	
	@Autowired
	RestrictionService restrictionService;
	
	
	@RequestMapping(value = "/admin/restriction/add", method = RequestMethod.GET)
	public String add(Model model)  {
	
		
		model.addAttribute("dtoBookRestriction", new DtoBookRestriction());		
		
		model.addAttribute("restrictionList", baseCodeRepository.findByRestrictions());
		
		return "admin/restriction/add";
	}
	
	
	@RequestMapping(value = "/admin/restriction/add", method = RequestMethod.POST)
	public String add(@Validated DtoBookRestriction dtoBookRestriction, BindingResult result, Model model)   {
		logger.debug("Post 콜"+dtoBookRestriction.toString());
		
		if(result.hasErrors()) {
			logger.debug("필드에러발생:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			logger.debug("으아아:"+result.toString());
			
			model.addAttribute("restrictionList", baseCodeRepository.findByRestrictions());
			return "admin/restriction/add";
		}else{	
			restrictionService.save(dtoBookRestriction);
			
			
		}		
		return "admin/restriction/list";
	}
	@RequestMapping(value = "/admin/restriction/list", method = RequestMethod.GET)
	public String list(Model model)  {
	
		
		return "admin/restriction/list";
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
	@RequestMapping(value="/admin/restriction/getRestrictionUserList",method = RequestMethod.GET)
    @ResponseBody
    public DataTable<VoBookRestriction> getRestrictionUserList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false) Integer iDisplayLength,
    								@RequestParam(required=false, defaultValue="1") Integer iSortCol_0, 
    								@RequestParam(required=false, defaultValue="DESC" ) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch,
                                    @RequestParam(required=false, defaultValue="") boolean isSolved) {

                    logger.info("iDisplayStart:{}", iDisplayStart.toString());
                    logger.info("sSortDir_0:{}", sSortDir_0);
                    logger.info("iSortCol_0:{}", iSortCol_0);
                    logger.info("iDisplayLength:{}", iDisplayLength);
                    logger.info("sSearch:{}", sSearch);
                    logger.info("isSolved:{}", isSolved);
                  
                  
                    String columns[]={"id","userRname","regDate", "solveDate"};
                    //Paging page = new Paging(columns,0,10,0,"desc","");
                    Paging page = new Paging(columns,iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
                
                    logger.info(page.toString());
                  
                    
                    return restrictionService.getRestrictionUserList(page, isSolved);
    }   
	/**
	 * <b>도서대출 정지 래제 </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 8. 24
	 * @param iSortCol_0 sort할 컬럼 번호 
	 * @param sSortDir_0 sort할 방향(asc/desc)
	 * @param sSearch 검색
	 * @return DataTables
	 * @return book/add.jsp 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/admin/restriction/solve", method = RequestMethod.GET)
	public String rental(@RequestParam(required=true) int id, Model model)  {
		
		BookRestriction bookRestriction = restrictionService.findByBookRestriction(id);
		model.addAttribute("bookRestriction",bookRestriction);
		
		return "/admin/restriction/solve";
	}
	/**
	 * <b>도서대출 정지 래제</b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param id 반납 아이디 
	 * @param day 연기 일수 
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	
	@RequestMapping(value = "/admin/restriction/solve", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse solvePost(@RequestParam(required=true) int id,
												 @RequestParam(required=true) @NotEmpty(message="해제사유를 입력하세요") String solveReason)  {
		
		logger.info("id:{}",id);
		logger.info("사유:{}",solveReason);
		
		BookRestriction bookRestriction = restrictionService.findByBookRestriction(id);
		bookRestriction.setSolveDate(new Date());
		bookRestriction.setSolveReason(solveReason);
		
		return restrictionService.save(bookRestriction);
	}
}
