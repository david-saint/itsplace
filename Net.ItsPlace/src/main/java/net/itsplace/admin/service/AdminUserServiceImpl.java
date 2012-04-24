package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import net.itsplace.admin.dao.AdminUserDao;
import net.itsplace.user.User;
import net.itsplace.user.UserService;

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
	@Autowired
	private UserService userService;
	
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
	public void editUser(User user)  {
		adminUserDao.editUser(user);
		
	}
	
	/**
	 * 관리자는 사용자를 삭제하고 삭제 사유를 남기고 사용자에게 메일로 삭제 통보한다     <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param user
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@Override
	public void deleteUser(User user) {
		//String test = null;
		//char tesdt = test.charAt(100);
		userService.updateUserDisable(user);
		
	}
}
