package com.mincoms.book.admin.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Validation;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mincoms.book.Exception.MincomsException;
import com.mincoms.book.api.Google;
import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategoryRoot;
import com.mincoms.book.domain.BookCategorySub;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookInfo.AddBook;
import com.mincoms.book.domain.BookInfo.EditBook;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.service.BookService;
import com.mincoms.book.service.CategoryService;
/**
 * <b>도서관리 컨트롤러 </b> <br />
 * <pre>
 * <b>History:</b>
 *     version 1.0, 2012.8.30  수정
 *     version 2.0, 2012.8.31 
 * </pre>
 * @author 김동훈
 * @version 2.0
 * @since 2012. 8. 24
 * @return JsonResponse
 * @Exception Exception 
 * @throws 
 * @see ssss
 */
@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	MessageSource messagesource;
	@Autowired
	BookService bookService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	JsonResponse json;

	/**
	 * <b>도서등록 Json응답</b> <br />
	 * <pre>
	 * <b>History:</b>
	 *     version 0.1, 2012.8.24 
	 *     version 0.1, 2012.8.24 
	 *     version 0.1, 2012.8.24 
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 8. 24
	 * @param BookInfo 도서 도메인
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/book/add/google", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse google(BookInfo book, Model model) throws Exception  {
		
		logger.debug("안드로이드 콜:{}",book.getIsbn());
		logger.debug("안드로이드 수량:{}",book.getCount());
		logger.debug("안드로이드 콜 카테고리:{}",book.getBookCategory().getId());
		
		BookInfo scanBookInfo = null;
		try{
			scanBookInfo = Google.GetBookInfo(book.getIsbn());
		}catch(Exception e){
			json.setFail();
		}
		
		if(book == null){
		}else{
			scanBookInfo.setRegDate(new Date());
			scanBookInfo.setCount(book.getCount());
			scanBookInfo.setBookCategory(book.getBookCategory());
			if(bookService.findByIsbn(book.getIsbn()) == null){
				bookService.save(scanBookInfo);
				json.setSuccess();
				json.setResult(scanBookInfo);
			}else{
				
				json.setFail();
				json.setResult("이미 등록되어있습니다");
				
			}
		}
		return json;
	}
	
	public void test(String num) {
		int x = 1* Integer.parseInt(num);
	}

	/*@ExceptionHandler(MincomsException.class)
	@ResponseBody
	public String handleException(RuntimeException e) {
		String test = messagesource.getMessage("javax.validation.constraints.NotNull.message", null,null);
		return test;
	}*/

	@RequestMapping(value = "/admin/book/add", method = RequestMethod.GET)
	public String add(Model model)  {
	
		logger.info("sl4j---------------->info");
		logger.warn("sl4j---------------->warn");
		logger.debug("sl4j---------------->debug");
		logger.error("sl4j---------------->error");
		
		model.addAttribute("bookInfo", new BookInfo());		
		model.addAttribute("categoryRootList", categoryService.findByBookCategoryRoot());
		
		return "admin/book/add";
	}
	/**
	 * <b>도서등록 Json응답</b> <br />
	 * <pre>
	 * <b>History:</b>
	 *     version 0.1, 2012.8.24 
	 *     version 0.1, 2012.8.24 
	 *     version 0.1, 2012.8.24 
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 8. 24
	 * @param BookInfo 도서 도메인
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/admin/book/add", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse addJson(@Validated({AddBook.class}) BookInfo book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.debug("필드에러:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			logger.debug("필드에러:"+result.toString());
			 json =  json.getValidationErrorResult(result, json);
			
		}else{	
			book.setRegDate(new Date());
			BookInfo saved = bookService.save(book);
			json.setSuccess();
			json.setResult(messagesource.getMessage("register", new Object [] {saved.getTitle()} , Locale.getDefault()));
		
		}		
		return json;
	}
	
	/**
	 * <b>도서등록</b> <br />
	 * <pre>
	 * <b>History:</b>
	 *     version 0.1, 2012.8.24 
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 8. 24
	 * @param BookInfo 도서 도메인
	 * @return book/add.jsp 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/admin/book/add", method = RequestMethod.POST)
	public String add(@Validated({AddBook.class}) BookInfo book, BindingResult result, Model model)   {
		logger.debug("Post 콜");
		if (result.hasErrors()) {
			logger.debug("필드에러발생:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			logger.debug("으아아:"+result.toString());
			logger.debug(book.toString());
			for(int i=0;i<result.getAllErrors().size();i++){
				ObjectError oe = result.getAllErrors().get(i);
				logger.debug("oe.getCode()="+oe.getCode());
				logger.debug("oe.getDefaultMessage()="+oe.getDefaultMessage());
				logger.debug("oe.getCodes()="+oe.getCodes()[0]);
				
			}
			model.addAttribute("categoryRootList", categoryService.findByBookCategoryRoot());
			return "book/add";
		} else {	
			book.setRegDate(new Date());
			bookService.save(book);
			
			
		}		
		return "admin/book/list";
	}
	
	@RequestMapping(value = "/admin/book/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required=true) String isbn, Model model) {
		BookInfo bookInfo = bookService.findByIsbn(isbn);
		model.addAttribute("bookInfo", bookInfo);
		model.addAttribute("categoryRootList", categoryService.findByBookCategoryRoot());			
		model.addAttribute("categorySubList", categoryService.findByIsDeletedBookCategorySub(false, bookInfo.getBookCategory().getBookCategorySub().getBookCategoryRoot()));
		model.addAttribute("categoryList", categoryService.findByIsDeleted(false, bookInfo.getBookCategory().getBookCategorySub()));
		return "admin/book/edit";
	}
	/**
	 * <b>도서등록</b> <br />
	 * <pre>
	 * <b>History:</b>
	 *     version 0.1, 2012.8.24 
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 8. 24
	 * @param BookInfo 도서 도메인
	 * @return book/edit.jsp 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/admin/book/edit", method = RequestMethod.POST)
	public String editSubmit(@Validated({EditBook.class}) BookInfo bookInfo, BindingResult result, Model model) throws Exception  {
		if (result.hasErrors()) {
			logger.debug("필드에러발생:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			logger.debug("으아아:"+result.toString());
			logger.debug(bookInfo.toString());
			for(int i=0;i<result.getAllErrors().size();i++){
				ObjectError oe = result.getAllErrors().get(i);
				logger.debug("oe.getCode()="+oe.getCode());
				logger.debug("oe.getDefaultMessage()="+oe.getDefaultMessage());
				logger.debug("oe.getCodes()="+oe.getCodes()[0]);
				
			}
			return "admin/book/edit";
		} else {	
			logger.info(bookInfo.toString());
			logger.info(bookInfo.toString());
			bookService.save(bookInfo);
			
			return "redirect:/admin/book/list";	
		}		
	}
	
	@RequestMapping(value = "/admin/book/list", method = RequestMethod.GET)
	public String list(Model model) {
	
		return "admin/book/list";
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
	@RequestMapping(value="/admin/book/findBookList",method = RequestMethod.GET)
    @ResponseBody
    public DataTable<BookInfo> findBookList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false) Integer iDisplayLength,
    								@RequestParam(required=false, defaultValue="1") Integer iSortCol_0, 
    								@RequestParam(required=false, defaultValue="DESC" ) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch,
                                    @RequestParam(required=false, defaultValue="") String isDeleted) {

                    logger.info("iDisplayStart:{}", iDisplayStart.toString());
                    logger.info("sSortDir_0:{}", sSortDir_0);
                    logger.info("iSortCol_0:{}", iSortCol_0);
                    logger.info("iDisplayLength:{}", iDisplayLength);
                    logger.info("sSearch:{}", sSearch);
                    logger.info("isDeleted:{}", isDeleted);
                  
                    String columns[] = new String[]{"", "bookCategory.bookCategorySub.bookCategoryRoot","bookCategory.bookCategorySub","bookCategory","title", "authors","regDate"};
                    
                    Paging page = new Paging(columns,iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0,sSearch);
                    logger.info(page.toString());
                    
                   return bookService.findBookList(page, isDeleted);
                   
    }   
	
}
