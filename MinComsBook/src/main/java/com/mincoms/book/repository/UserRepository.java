package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String> {
	UserInfo findByUserId(int userId);
	UserInfo findByUserName(String userName);
	List<UserInfo> findByIsDeleted(boolean isDeleted);
}


