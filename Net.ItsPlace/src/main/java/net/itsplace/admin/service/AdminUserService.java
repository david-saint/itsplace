package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import net.itsplace.domain.DataTable;
import net.itsplace.user.User;



public interface AdminUserService {

	public DataTable getUserList(String columns[],  Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0, String sSearch, String role);
	public User getUser(String email) ;
	public void saveUser(User user) ;
	public void editUser(User user);
	public void deleteUser(User user);
}
