package com.mincoms.book.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategoryRoot;
import com.mincoms.book.domain.BookCategorySub;
import com.mincoms.book.domain.BookInfo;

public interface CategoryService {

	/**
	 * <b>도서 서브 카테고리 저장 {@link BookCategory}</b>	
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 12
	 * @param BookCategory 
	 * @return BookCategory 
	 * @throws Exception 
	 * @see 
	 */
	public BookCategory save(BookCategory bookCategory);
	
	public BookCategoryRoot save(BookCategoryRoot bookCategoryRoot);
	
	public BookCategorySub save(BookCategorySub bookCategorySub);
	

	/**
	 * <b>도서  카테고리 리스트 가져오기</b>	
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 12
	 * @param {@link BookCategoryRoot} 루트 카테고리 PK 
	 * @param isDeleted 카테고리 삭제여부
	 * @return BookCategory 
	 * @throws Exception 
	 * @see 
	 */
	
	public List<BookCategory> findByIsDeleted(boolean isDeleted, BookCategorySub bookCategorySub);
	/**
	 * <b>도서 서브 카테고리 리스트 가져오기</b>	
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 12
	 * @param {@link BookCategoryRoot} 루트 카테고리 PK 
	 * @param isDeleted 카테고리 삭제여부
	 * @return BookCategorySub 
	 * @throws Exception 
	 * @see 
	 */
	
	public List<BookCategorySub> findByIsDeletedBookCategorySub(boolean isDeleted, BookCategoryRoot bookCategoryRoot);
	/**
	 * <b>도서 루트 카테고리 리스트 (현재 사용중인 카테고리)</b>	
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 12
	 * @param  {@link BookCategoryRoot}  
	 * @return {@link BookCategoryRoot}  
	 * @throws Exception 
	 * @see 
	 */
	public List<BookCategoryRoot> findByBookCategoryRoot();
	
	public BookCategoryRoot findByBookCategoryRoot(Integer rootid);
	public BookCategorySub findByBookCategorySub(Integer subid);
	public BookCategory findByBookCategory(Integer id);
	
	public  List<BookCategoryRoot> findByBookCategoryRootAll();
	public  List<BookCategorySub> findByBookCategorySubAll(Integer rootid);
	public  List<BookCategory> findByBookCategoryAll(Integer subid);
}
