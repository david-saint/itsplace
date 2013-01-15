package net.itsplace.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.User;

import org.springframework.dao.DataAccessException;


public interface UserService {
	public User getUser(String email);
	public User getUser(String email, String token);
	public User getUserByPasswordLink(String passwordLInk);
	public User getUserByMobile(String mobile);
	
	/* 사용자를 저장한다*/
	public void saveUser(User user);
	public void updateUser(User user);
	/*리셋 요청시 업데이트함 로그인시 링크 삭제함*/
	public boolean updateUserPasswordLink(String url, String email);
	public void updateUserPassword(User user);
	/* 사용자를 사용 정지한다*/
	public void updateUserDisable(User user) ;
	public void updateUserEnable(User user) ;
	
//	public List<User> getUserList(Map<String, Object> param) throws DataAccessException;
	
	
	public DataTable getUserList(String columns[],  Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0, String sSearch, String role);
	
}
 