package com.mincoms.book.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategoryRoot;
import com.mincoms.book.repository.CategoryRepository;
import com.mincoms.book.repository.CategoryRootRepository;
import com.mincoms.test.TestApplicationContext;

public class BookCategoryServiceImplTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(BookCategoryServiceImplTest.class);
	
	@Autowired
	CategoryService service;

	@Autowired
	CategoryRepository repo;
	
	@Autowired
	CategoryRootRepository rootRepo;
	
	@Test
	public void test2() {
		
		/*List<BookCategory> list = service.findByIsDeleted(false, new Sort(Sort.Direction.ASC,"dispSeq"));
		for(BookCategory category : list){
			logger.info(category.getName());
		}*/
		BookCategoryRoot bookCategoryRoot = new  BookCategoryRoot();
		bookCategoryRoot.setId(1);
		/*List<BookCategory> list = repo.findByBookCategoryRoot(bookCategoryRoot);
		for(BookCategory category : list){
			logger.info(category.getName());
		}*/
		List<BookCategory> list = repo.findByIsDeletedAndBookCategoryRoot(false, bookCategoryRoot ,new Sort(Sort.Direction.DESC,"dispSeq"));
		for(BookCategory category : list){
			logger.info(category.getName());
		}
	}
	@Ignore
	public void test() {
		
		BookCategoryRoot bookRootCategory = new  BookCategoryRoot();
		bookRootCategory.setName("전문서적");
		bookRootCategory.setDispSeq(1);
		bookRootCategory.setDeleted(false);
		
	//	repo.save(bookRootCategory);
		
		bookRootCategory = rootRepo.findOne(2);
		BookCategory bookCategory = new BookCategory();
		bookCategory.setName("경영기획");
		bookCategory.setDispSeq(1);
		bookCategory.setDeleted(false);
		bookCategory.setBookCategoryRoot(bookRootCategory);
		service.save(bookCategory);
	}

}
