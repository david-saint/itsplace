package com.myplace.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.*;
@Repository("UserDAO")
public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {
	//protected final Log log = LogFactory.getLog(getClass());
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	
	
	public User getUser(String email) throws DataAccessException{			
		return (User)getSqlMapClientTemplate().queryForObject("getUser",email);		
	}

	public User getUser(String email, String token) throws DataAccessException{		
		User user = new User();
		user.setEmail(email);
		user.setEmailToken(token);
		return (User)getSqlMapClientTemplate().queryForObject("getUserEmailToken",user);		
	}

	public void setUser(User user) throws DataAccessException,MySQLIntegrityConstraintViolationException {		
		logger.info("사용자 정보를 저장합니다 ");
		try{
			getSqlMapClientTemplate().insert("setUser",user);
			
		}catch(Exception e){
			
		}
	
	}



	public List<User> getUserList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getUserList", param);
		
	}



	public void updateUser(User user) throws DataAccessException {
		getSqlMapClientTemplate().update("updateUser",user);
		
	}



	public void saveSocial(Social social) throws DataAccessException {
		getSqlMapClientTemplate().insert("saveSocial", social);
		
	}



	public void updateSocial(Social social) throws DataAccessException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("updateSocial",social);
	}



	public Social getSocial(Social social) throws DataAccessException {
		// TODO Auto-generated method stub
		return (Social)getSqlMapClientTemplate().queryForObject("getSocial",social);
	}



	public String getUserConnection(Social social) throws DataAccessException {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("getUserConnection",social);
	}



	public void updateUserDisable(User user) throws DataAccessException {
		getSqlMapClientTemplate().update("updateUserDisable",user);
	}



	@Override
	public void updateUserEnable(User user) throws DataAccessException {
		getSqlMapClientTemplate().update("updateUserEnable",user);
		
	}



	@Override
	public User getUserByMobile(String mobile) throws DataAccessException {
		// TODO Auto-generated method stub
		return (User)getSqlMapClientTemplate().queryForObject("getUserByMobile",mobile);
	}



	@Override
	public void setUserEmailOn(User user) throws DataAccessException {
		
		getSqlMapClientTemplate().update("setUserEmailOn",user);
	}


	
	



}
