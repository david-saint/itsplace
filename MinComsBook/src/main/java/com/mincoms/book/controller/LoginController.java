/**
 * 
 */
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

import com.mincoms.book.domain.UserInfo;
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
	 * 로그인폼 호출<br>
	 * security-context.xml 참고할것
	 * 인증에 실패하면 authentication-failure-url 에 정의된 에러 파라미터를 넘긴다
	 * Validation 할것인지는 고려해볼것 				
	 * @author 김동훈
	 * @version 1.0, 2011.8.15 
	 * @param 
	 * @return  로그인페이지
	 * @throws org.springframework.dao.DataAccessException if the query fails
	 * @see 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm(@RequestParam(value="error", required=false) boolean error, Model model,HttpServletRequest request) {
		
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
		 
		/* if(autoLogin("dhkim", "1111")){
			 System.out.println("/login 개발 모드 자동로그인 성공");	 
		 }else{
			 System.out.println("/login 개발 모드 자동로그인 실패");
		 }*/
//		 if(net.itsplace.user.UserInfo.autoLogin("faye12005@gmail.com", "hoon1014")){
//			 DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
//			 System.out.println("자동로그인"+request.getRequestURI()+defaultSavedRequest);
//			 //return request.getRequestURI();
//			// new CustomUserDetailsService().loadUserByUsername(username)
//			 model.addAttribute("back","back");
//			 
//		 }
		
		/*Map<String, Object> param;
		param = new HashMap<String, Object>();		
		List<User> userList= userService.getUserList(param);
		model.addAttribute("userList",userList);
		*/
		if (error == true) {
			model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었어요");
		} else {
			model.addAttribute("error", "");
		}
		//System.out.println("/user/login 실패시--------------------"+request.getHeader("X-Ajax-call"));
		////ogger.info("/user/login 실패시");
		
		
		//return "login/login";		
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
	@RequestMapping(value = "/user/getUser", method = RequestMethod.POST)
	public  @ResponseBody  UserInfo getuser(UserInfo userInfo) {
		logger.info("Android Call username:{}",userInfo.getUserName());
		UserInfo signedUser = null;
		
		signedUser = userService.findByUserName(userInfo.getUserName());
		signedUser.setGcmId(userInfo.getGcmId());
		userService.save(signedUser);
		
		return signedUser;
	}
	//민워크 로그인시 패스워드 업데이트
	@RequestMapping(value = "/user/setPassword", method = RequestMethod.POST)
	public  @ResponseBody  UserInfo setuser(UserInfo userInfo) {
		logger.info("Android Call username:{}",userInfo.getUserName());
		UserInfo signedUser = null;
		signedUser = userService.findByUserName(userInfo.getUserName());
		signedUser.setPassword( Encrypt.md5Encoding(userInfo.getPassword()));
		userService.save(signedUser);
		
		return signedUser;
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