package com.mincoms.book.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.domain.UserInfo;

public interface RestrictionRepository extends JpaRepository<BookRestriction, Integer> {
	Page<BookRestriction> findAll(Specification<BookRestriction> spec, Pageable pageable);
	BookRestriction findByUserInfoAndSolveDateIsNull(UserInfo userInfo);
	List<BookRestriction> findBySolveDateIsNull();
	public List<BookRestriction> findByUserInfo(UserInfo userInfo);
}
