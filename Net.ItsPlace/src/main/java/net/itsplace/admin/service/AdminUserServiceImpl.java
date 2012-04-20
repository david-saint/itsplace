package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import net.itsplace.admin.dao.AdminUserDao;
import net.itsplace.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;



@Service("AdminUserService")
public class AdminUserServiceImpl implements AdminUserService{
	private static final Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);
	
	@Autowired
	private AdminUserDao adminUserDao;
	
	@Transactional(readOnly=true)
	public List<User> getUserList(Map<String, Object> param){
		return adminUserDao.getUserList(param);
	}
	
	public User getUser(String email){
		return adminUserDao.getUser(email);
	}

	@Override
	public void saveUser(User user) {
		adminUserDao.saveUser(user);
		
	}

	@Override
	public void updateUser(User user)  {
		adminUserDao.updateUser(user);
		
	}
}
