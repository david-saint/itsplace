package com.mincoms.book.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mincoms.book.Exception.MincomsException;
import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategory.AddBookCategory;
import com.mincoms.book.domain.BookCategoryRoot;
import com.mincoms.book.domain.BookCategorySub;
import com.mincoms.book.repository.CategoryRepository;
import com.mincoms.book.repository.CategoryRootRepository;
import com.mincoms.book.repository.CategorySubRepository;
import com.mincoms.book.service.CategoryService;
@Controller
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	CategoryRootRepository rootRepo;
	@Autowired
	CategorySubRepository subRepo;
	@Autowired
	CategoryRepository repo;
	
	@RequestMapping(value = "/admin/category/root", method = RequestMethod.GET)
	public String root_add(@RequestParam(required=false,defaultValue="0") int id, Model model)  {
		if(id == 0){
			model.addAttribute("bookCategoryRoot",new BookCategoryRoot() );
		}else{
			model.addAttribute("bookCategoryRoot",categoryService.findByBookCategoryRoot(id) );
		}
		model.addAttribute("bookCategoryRootList", categoryService.findByBookCategoryRootAll());
		return "admin/category/root";
	}
	@RequestMapping(value = "/admin/category/root/save", method = RequestMethod.POST)
	public  String root_add(@Validated BookCategoryRoot bookCategoryRoot, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.debug("필드에러:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			logger.debug("필드에러:"+result.toString());
			model.addAttribute("bookCategoryRootList", categoryService.findByBookCategoryRootAll());
			return "admin/category/root";
		}else{	
			categoryService.save(bookCategoryRoot);
		}		
		return "redirect:/admin/category/root";
	}
	@RequestMapping(value = "/admin/category/root/delete", method = RequestMethod.GET)
	public String root_delete(@RequestParam(required=true,defaultValue="0") int id, Model model){
		try{
			rootRepo.delete(id);
			
		}catch(TransactionSystemException e){
			e.printStackTrace();
			throw new MincomsException("대분류  카테고리를 사용중입니다 삭제할 수 없습니다");
		}
		return "redirect:/admin/category/root";
	}
	
	
	
	
	
	
	@RequestMapping(value = "/admin/category/sub", method = RequestMethod.GET)
	public String sub_add(@RequestParam(required=false,defaultValue="0") int id,@RequestParam(required=false,defaultValue="0") int rootid, Model model)  {
		if(id == 0){
			model.addAttribute("bookCategorySub",new BookCategorySub() );
		}else{
			model.addAttribute("bookCategorySub",categoryService.findByBookCategorySub(id) );
		}
		
		model.addAttribute("bookCategoryRootList", categoryService.findByBookCategoryRootAll());
		model.addAttribute("bookCategorySubList", categoryService.findByBookCategorySubAll(rootid));
		model.addAttribute("rootid", rootid);
		return "admin/category/sub";
	}
	@RequestMapping(value = "/admin/category/sub/save", method = RequestMethod.POST)
	public  String sub_add(@Validated BookCategorySub bookCategorySub, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.debug("필드에러:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			logger.debug("필드에러:"+result.toString());
			model.addAttribute("bookCategoryRootList", categoryService.findByBookCategoryRootAll());
			return "admin/category/sub";
		}else{	
			categoryService.save(bookCategorySub);
		}		
		return "redirect:/admin/category/sub";
	}

	@RequestMapping(value = "/admin/category/sub/delete", method = RequestMethod.GET)
	public String sub_delete(@RequestParam(required=true,defaultValue="0") int id, Model model)   {
		try{
			subRepo.delete(id);
		}catch(TransactionSystemException e){			
			e.printStackTrace();
			throw new MincomsException("중분류  카테고리를 사용중입니다 삭제할 수 없습니다");
		}
		return "redirect:/admin/category/sub";
	}
	
	public void test() throws Exception{
		throw new Exception("ㅇㅇ");
	}

	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String add(@RequestParam(required=false,defaultValue="0") int id,
					  @RequestParam(required=false,defaultValue="0") int rootid,
					  @RequestParam(required=false,defaultValue="0") int subid, Model model)  {
		if(id == 0){
			model.addAttribute("bookCategory",new BookCategory());
			model.addAttribute("bookCategorySubList", null);
		}else{
			BookCategory bookCatgeory = categoryService.findByBookCategory(id);
			model.addAttribute("bookCategory", bookCatgeory);
			model.addAttribute("bookCategorySubList", categoryService.findByBookCategorySubAll(bookCatgeory.getBookCategorySub().getBookCategoryRoot().getId()));
		}
		
		model.addAttribute("bookCategoryRootList", categoryService.findByBookCategoryRootAll());
		model.addAttribute("bookCategoryList", categoryService.findByBookCategoryAll(subid));
		model.addAttribute("rootid", rootid);
		model.addAttribute("subid", subid);
		return "admin/category/category";
	}
	@RequestMapping(value = "/admin/category/save", method = RequestMethod.POST)
	public  String add(@Validated({AddBookCategory.class})  BookCategory bookCategory, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.debug("필드에러:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			logger.debug("필드에러:"+result.toString());
			model.addAttribute("bookCategoryRootList", categoryService.findByBookCategoryRootAll());
			model.addAttribute("bookCategorySubList", categoryService.findByBookCategorySubAll(0));
			model.addAttribute("bookCategoryList", categoryService.findByBookCategoryAll(0));
			return "admin/category/category";
		}else{	
			categoryService.save(bookCategory);
		}		
		return "redirect:/admin/category";
	}
	@RequestMapping(value = "/admin/category/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(required=true,defaultValue="0") int id, Model model)  {
		try{
			repo.delete(id);
			
		}catch(TransactionSystemException e){
			e.printStackTrace();
			throw new MincomsException("소분류  카테고리를 사용중입니다 삭제할 수 없습니다");
		}
		
		return "redirect:/admin/category";
	}
	
	
}
