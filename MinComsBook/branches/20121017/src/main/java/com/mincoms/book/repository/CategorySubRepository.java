package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategoryRoot;
import com.mincoms.book.domain.BookCategorySub;

public interface CategorySubRepository extends JpaRepository<BookCategorySub, Integer> {
	public List<BookCategorySub> findByIsDeletedAndBookCategoryRoot(boolean isDeleted, BookCategoryRoot bookCategoryRoot, Sort sort);
	
	public List<BookCategorySub> findByBookCategoryRoot(BookCategoryRoot bookCategoryRoot,Sort sort);
	
}
