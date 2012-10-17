package com.mincoms.book.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategoryRoot;
import com.mincoms.book.domain.BookCategorySub;
import com.mincoms.book.repository.BookRepository;
import com.mincoms.book.repository.CategoryRepository;
import com.mincoms.book.repository.CategoryRootRepository;
import com.mincoms.book.repository.CategorySubRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	CategoryRootRepository categoryRootRepo;
	@Autowired
	CategorySubRepository categorySubRepo;
	
	@Override
	public BookCategory save(BookCategory bookCategory) {
		
		return categoryRepo.save(bookCategory);
	}

	@Override
	public BookCategoryRoot save(BookCategoryRoot bookCategoryRoot) {
		return categoryRootRepo.save(bookCategoryRoot);
	}


	@Override
	public BookCategorySub save(BookCategorySub bookCategorySub) {
		return categorySubRepo.save(bookCategorySub);
	}

	@Override
	public List<BookCategoryRoot> findByBookCategoryRoot() {
		return categoryRootRepo.findByIsDeleted(false);
	}




	@Override
	public BookCategoryRoot findByBookCategoryRoot(Integer rootid) {
		return categoryRootRepo.findOne(rootid);
	}
	@Override
	public BookCategory findByBookCategory(Integer id) {
		return categoryRepo.findOne(id);
	}


	

	@Override
	public BookCategorySub findByBookCategorySub(Integer subid) {
		return categorySubRepo.findOne(subid);
	}


	@Override
	public List<BookCategory> findByIsDeleted(boolean isDeleted,BookCategorySub bookCategorySub) {
		return categoryRepo.findByIsDeletedAndBookCategorySub(isDeleted, bookCategorySub, new Sort(Sort.Direction.ASC, "dispSeq"));
	}


	@Override
	public List<BookCategorySub> findByIsDeletedBookCategorySub(boolean isDeleted,BookCategoryRoot bookCategoryRoot) {
		return categorySubRepo.findByIsDeletedAndBookCategoryRoot(isDeleted, bookCategoryRoot, new Sort(Sort.Direction.ASC, "dispSeq"));
	}

	@Override
	public List<BookCategoryRoot> findByBookCategoryRootAll() {
		return categoryRootRepo.findAll();
	}

	

	@Override
	public List<BookCategorySub> findByBookCategorySubAll(Integer rootid) {
		if(rootid == 0){
			return categorySubRepo.findAll();
		}else{
			BookCategoryRoot bookCategoryRoot = categoryRootRepo.findOne(rootid);
			return categorySubRepo.findByBookCategoryRoot(bookCategoryRoot, new Sort(Sort.Direction.ASC, "dispSeq"));
		}
		
	}

	@Override
	public List<BookCategory> findByBookCategoryAll(Integer subid) {
		if(subid == 0){
			return categoryRepo.findAll();
		}else{
			BookCategorySub bookCategorySub = categorySubRepo.findOne(subid);
			return categoryRepo.findByBookCategorySub(bookCategorySub, new Sort(Sort.Direction.ASC, "dispSeq"));
		}
	}

	

	

}
