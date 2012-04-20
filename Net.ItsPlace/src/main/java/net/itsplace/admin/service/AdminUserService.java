package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import net.itsplace.user.User;



public interface AdminUserService {

	public List<User> getUserList(Map<String, Object> param);
	public User getUser(String email) ;
	public void saveUser(User user) ;
	public void updateUser(User user);
}
