package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mincoms.book.domain.Authorities;
import com.mincoms.book.domain.UserInfo;


public interface AuthoritiesRepository  extends JpaRepository<Authorities, Integer> {
	//Authorities findByUserName(String userName);
	List<Authorities> findByUser(UserInfo user);
}
