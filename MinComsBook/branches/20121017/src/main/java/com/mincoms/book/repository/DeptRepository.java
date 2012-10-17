package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.DeptInfo;

public interface DeptRepository extends JpaRepository<DeptInfo, Integer> {

	public List<DeptInfo> findByIsDeleted(boolean isDeleted);
}
