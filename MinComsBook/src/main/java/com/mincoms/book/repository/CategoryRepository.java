package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategoryRoot;

public interface CategoryRepository extends JpaRepository<BookCategory, Integer> {

	public List<BookCategory> findByIsDeletedAndBookCategoryRoot(boolean isDeleted, BookCategoryRoot bookCategoryRoot, Sort sort);
	
	public List<BookCategory> findByBookCategoryRoot(BookCategoryRoot bookCategoryRoot);
}
