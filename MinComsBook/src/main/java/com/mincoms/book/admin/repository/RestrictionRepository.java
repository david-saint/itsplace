package com.mincoms.book.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mincoms.book.domain.AppException;

public interface RestrictionRepository extends JpaRepository<AppException, Integer> {

}
