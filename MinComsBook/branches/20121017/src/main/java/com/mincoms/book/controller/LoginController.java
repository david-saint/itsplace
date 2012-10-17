
package com.mincoms.book.controller;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.gcm.GcmApp;
import com.mincoms.book.security.BookUserDetailsService;
import com.mincoms.book.security.CustomUserDetails;
import com.mincoms.book.service.UserService;
import com.mincoms.book.util.Encrypt;


@Controller
public class LoginController {
        
	private static final Logger logger =  LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService userService;

	
	/**
	 * 로그인폼 <br>
	 * security-context.xml 참고할것
	 * 인증에 실패하면 authentication-failure-url 에 정의된 에러 파라미터를 넘긴다
	 * Validation 할것인지는 고려해볼것 				
	 * @author 김동훈
	 * @version 1.0, 2011.8.15 
	 * @param 
	 * @return  로그인페이지
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm(@RequestParam(value="error", required=false) boolean error,Model model,HttpServletRequest request) throws Exception {
		
		System.out.println("ㅎ로그인 페이지 리다이렉트");
		if(request.getHeader("user-agent")!=null){
			String agent = request.getHeader("user-agent");
			
			
			if(request.getHeader("Accept")!= null){			
				 Enumeration enumeration = request.getHeaderNames();
			       while (enumeration.hasMoreElements()){
			       	String temp = enumeration.nextElement().toString() ;
			       	System.out.println(temp);
			       	
			       } 
			       System.out.println("agent:"+request.getHeader("user-agent"));
				if(request.getHeader("Accept").equals("application/json")){
					System.out.println("Accept:"+request.getHeader("Accept"));
					
				}
				else{
					System.out.println("Accept:"+request.getHeader("Accept"));
					
				}
			}   
			if(agent.equals("android")){
				 ModelAndView m = new ModelAndView("/exception/ajax_exception");
		         m.addObject("ajaxExceptionMessage", "login");
		         
		         return m;
			}
		}
	
		if (error == true) {
			model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었어요");
		} else {
			model.addAttribute("error", "");
		}
			
		return  new ModelAndView("/login/login");
		
	}
	
	
	
	/**
	 * 접근권한이 없을시에 페이지 호출 security-context.xml의 access-denied-page="/denied" 정의함
	 * 	<http auto-config="true" use-expressions="true" access-denied-page="/denied" >
	 * access-denied-page="/denied"
	 * @author 김동훈
	 * @version 1.0, 2011.8.15 
	 * @param 
	 * @return  접근권한 없음 페이지
	 * @throws org.springframework.dao.DataAccessException if the query fails
	 * @see 
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied() {	
		return "login/denied";
	}

	
	 @RequestMapping(value="/logout")
	  public String logout(@RequestParam("targetUrl") String targetUrl, SessionStatus status) {
		  logger.info("로그아웃:{}",targetUrl);
	      status.setComplete();
	      return "redirect:" + targetUrl;
	  }
	  
	public  boolean autoLogin(String username,String password) {
		try {
		 // rDetailsService us = new BookUserDetailsService();
		  //UserDetails userDetails =  us.loadUserByUsername(username);
			 /*new CustomUserDetails(
						dbUser, 
						dbUser.getUserName(),						
						dbUser.getPassword().toLowerCase(),
						true,
						true,
						true,
						true,
						getAuthorities(dbUser.getAuthlevel())
			*/			
		  UsernamePasswordAuthenticationToken authentication = new 
		            UsernamePasswordAuthenticationToken(username, password);

		  // Place the new Authentication object in the security context.
		  SecurityContextHolder.getContext().setAuthentication(authentication);
		  
		 }
		 catch (Exception e) {
		  SecurityContextHolder.getContext().setAuthentication(null);		 
		  return false;
		 }
		 return true;
	}
}