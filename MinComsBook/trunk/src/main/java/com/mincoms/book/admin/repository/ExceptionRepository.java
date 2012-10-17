package com.mincoms.book.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;

@Repository
public interface ExceptionRepository  extends JpaRepository<AppException, String> {

	Page<AppException> findAll(Specification<AppException> spec, Pageable pageable);
}
