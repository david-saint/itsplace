package com.mincoms.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.UserEtcInfo;

@Repository
public interface UserEtcInfoRepository extends JpaRepository<UserEtcInfo, Integer> {

	UserEtcInfo findByUserIdNumber(String userIdNumber);
}
