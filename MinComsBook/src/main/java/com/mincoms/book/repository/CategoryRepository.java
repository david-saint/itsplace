package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategoryRoot;
import com.mincoms.book.domain.BookCategorySub;

public interface CategoryRepository extends JpaRepository<BookCategory, Integer> {

	public List<BookCategory> findByIsDeletedAndBookCategorySub(boolean isDeleted, BookCategorySub bookCategorySub, Sort sort);
	
	public List<BookCategory> findByBookCategorySub(BookCategorySub bookCategorySub, Sort sort);
}
