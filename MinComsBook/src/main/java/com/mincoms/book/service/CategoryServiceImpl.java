package com.mincoms.book.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategoryRoot;
import com.mincoms.book.repository.BookRepository;
import com.mincoms.book.repository.CategoryRepository;
import com.mincoms.book.repository.CategoryRootRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	CategoryRootRepository categoryRootRepo;
	
	@Override
	public BookCategory save(BookCategory bookCategory) {
		
		return categoryRepo.save(bookCategory);
	}

	
	@Override
	public List<BookCategoryRoot> findByBookCategoryRoot() {
		return categoryRootRepo.findByIsDeleted(false);
	}


	@Override
	public List<BookCategory> findByIsDeleted(
			boolean isDeleted ,BookCategoryRoot bookCategoryRoot) {
		
		return categoryRepo.findByIsDeletedAndBookCategoryRoot(isDeleted, bookCategoryRoot, new Sort(Sort.Direction.ASC, "dispSeq"));
	}


	@Override
	public BookCategoryRoot findByBookCategoryRoot(Integer rootid) {
		return categoryRootRepo.findOne(rootid);
	}

}
