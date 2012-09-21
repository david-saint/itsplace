package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.UserInfo;

@Repository
public interface BookRepository extends JpaRepository<BookInfo, String> {
	
	Page<BookInfo> findAll(Specification<BookInfo> spec, Pageable pageable);
	
	Page<BookInfo> findByTitleContainingOrAuthors(String title,String author,Pageable pageable);
	
	BookInfo findByIsbn(String isbn);
	
	
}
