package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.user.User;

import org.springframework.dao.DataAccessException;



public interface AdminUserDao {
	public List<User>  getUserList(Map<String, Object> param) throws DataAccessException;
	public User getUser(String email) throws DataAccessException;
}
