package com.mincoms.book.service;

import java.util.List;

import com.mincoms.book.domain.UserInfo;

/**
 * Service interface for {@link UserInfo}s.
 * 
 * @author Oliver Gierke
 */
public interface UserService {

	/**
	 * 유저 저장 {@link UserInfo}.
	 * 
	 * @param user
	 * @return
	 */
	UserInfo save(UserInfo user);

	/**
	 * 유저 이름으로 유조거죵ㅎ가 {@link UserInfo}.
	 * 
	 * @param userName
	 * @return
	 */
	UserInfo findByUserName(String userName);
	
	/**
	 * 활성화중인 유저 리스트 가져오기 IsDeleted=0 {@link UserInfo}.
	 * 
	 * @param userName
	 * @return
	 */
	List<UserInfo> findActiveAll();
	/**
	 * 유저 가져오기 유저정보,부서정보 조인  {@link UserInfo}.
	 * 
	 * @param userName
	 * @return
	 */
	UserInfo findByUserId(int userId);
	/**
	 * 유저 가져오기 유저정보,부서정보 조인  {@link UserInfo}.
	 * 
	 * @param userName
	 * @return
	 */
	List<UserInfo> findByUserNameContaining(String userName);
	
	List<UserInfo> findByDeptInfo(int deptId);
}
