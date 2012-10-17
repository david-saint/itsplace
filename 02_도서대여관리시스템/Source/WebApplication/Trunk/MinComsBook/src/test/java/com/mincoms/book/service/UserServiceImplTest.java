package com.mincoms.book.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.domain.Authorities;
import com.mincoms.book.domain.DeptInfo;
import com.mincoms.book.domain.Role;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.repository.AuthoritiesRepository;
import com.mincoms.book.repository.AuthoritiesRepositoryTest;
import com.mincoms.test.TestApplicationContext;

public class UserServiceImplTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
	
	@Autowired
	UserService userService;

	@Autowired
	AuthoritiesRepository authRepo;
	
	UserInfo user;
	@Before
	public void setUp() throws Exception {
		user = new UserInfo();
	}

	@Ignore
	public void testSave() {
		user.setUserName("babo");
		user.setUserRname("홍길동");
		DeptInfo dept = new DeptInfo();
		dept.setDeptid(1);
		//user.setDept(dept);
		UserInfo saved = userService.save(user);
		
		Authorities auth = new Authorities();
		auth.setRole(new Role("role_admin"));
		auth.setUser(saved);
		authRepo.save(auth);
	}

	@Ignore
	public void testFindByName() {
		String userName ="babo";
		
		List<Authorities> auths = userService.findByAuthorities(userName);
		
		for(Authorities domain:auths){
			logger.info(domain.getUser().getUserRname() + domain.getRole().getName());
		}
	}
	
	@Test
	public void testFindActiveAll(){
		List<UserInfo> users = userService.findActiveAll();
		for(UserInfo user:users){
			//logger.info(user.getUserName() + user.getUserRname()+ user.getDept().getDeptName());
		}
		
		
	}

}
