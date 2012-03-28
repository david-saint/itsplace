package com.myplace.user;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;


public interface UserService {
	public User getUser(String email);
	public User getUser(String email, String token);
	
	public User getUserByMobile(String mobile);
	
	/* 사용자를 저장한다*/
	public void saveUser(User user);
	public void updateUser(User user);
	/* 사용자를 사용 정지한다*/
	public void updateUserDisable(User user) ;
	public void updateUserEnable(User user) ;
	
	public List<User> getUserList(Map<String, Object> param) throws DataAccessException;
	
	/* SOCIAL 사용자 인증 정보를 저장한다*/
	public void saveSocial(Social social);
	public void updateSocial(Social social);
	public Social getSocial(Social social);
}
 