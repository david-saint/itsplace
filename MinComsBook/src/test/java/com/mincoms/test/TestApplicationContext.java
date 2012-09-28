package com.mincoms.test;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
 
import junit.framework.TestCase;
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.repository.UserRepository;
import com.mincoms.book.security.CustomUserDetails;

/**
 * 
 * 
 * @author 김동훈
 * @version 1.0, 2011. 8. 22.
 * @see 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
"file:src/main/webapp/WEB-INF/spring/infrastructure.xml", 
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context.xml",
"file:src/main/webapp/WEB-INF/spring/ibatis-context-test.xml",
"file:src/main/webapp/WEB-INF/spring/task-context.xml",
"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public abstract class TestApplicationContext {

	@Autowired protected ApplicationContext context;
	@Autowired protected UserRepository userRepo;
	
    public void loginTestUser(String userName,String password) {
		UserInfo user = userRepo.findByUserName(userName);
    	CustomUserDetails details = new CustomUserDetails(
				user, 
				user.getUserName(),						
				user.getPassword().toLowerCase(),
				true,
				true,
				true,
				true,
				null);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName,password);
		token.setDetails(details);
		Authentication auth  = token;
        SecurityContextHolder.getContext().setAuthentication(auth);
      // SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("dhkim", "1111"));
    }
    public UserInfo getUser(String userName){
    	return userRepo.findByUserName(userName);
    }
}
