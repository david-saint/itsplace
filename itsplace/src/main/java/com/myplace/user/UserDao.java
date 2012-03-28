package com.myplace.user;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


public interface UserDao {
	/* 사용자 정보를 가져온다*/
	public User getUser(String email) throws DataAccessException;
	public User getUser(String email,String token) throws DataAccessException;	
	public User getUserByMobile(String mobile) throws DataAccessException;
	
	public void setUserEmailOn(User user) throws DataAccessException;
	
	
	/* 사용자 리스트를 가져온다*/
	public List<User>  getUserList(Map<String, Object> param) throws DataAccessException;
	/* 사용자를 저장한다*/
	public void setUser(User user) throws DataAccessException, MySQLIntegrityConstraintViolationException;
	/* 사용자를 수정한다*/
	public void updateUser(User user) throws DataAccessException;
	public void updateUserEnable(User user) throws DataAccessException;
	/* 사용자를 사용 정지한다*/
	public void updateUserDisable(User user) throws DataAccessException;
	
	/* SOCIAL 사용자 인증 정보를 저장한다*/
	public void saveSocial(Social social) throws DataAccessException;
	public void updateSocial(Social social) throws DataAccessException;
	public Social getSocial(Social social) throws DataAccessException;
	
	public String getUserConnection(Social social) throws DataAccessException;
}
