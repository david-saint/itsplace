package com.mincoms.book.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BaseCode;

public interface BaseCodeRepository extends JpaRepository<BaseCode, Integer> {
	@Query("select B from BaseCode B Where B.codeKey = ?1 And B.isDeleted = 0 And B.codeGroup='BookManager'")
	BaseCode findByBookManager(String userName);
	
	@Query("select B from BaseCode B Where  B.isDeleted = 0 And B.codeGroup='BookRestriction' And B.codeKey <> ''")
	List<BaseCode> findByRestrictions();
}
