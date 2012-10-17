package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategoryRoot;

public interface CategoryRootRepository  extends JpaRepository<BookCategoryRoot, Integer> {
	
	public List<BookCategoryRoot> findByIsDeleted(boolean isDeleted);
}
