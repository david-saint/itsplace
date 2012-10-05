package com.mincoms.book.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BookRestriction;

public interface RestrictionRepository extends JpaRepository<BookRestriction, Integer> {
	Page<BookRestriction> findAll(Specification<BookRestriction> spec, Pageable pageable);
}
