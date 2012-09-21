package com.mincoms.book.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.service.UserService;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private UserService userService;
	
	
	private static final Logger logger =  LoggerFactory.getLogger(LoginSuccessHandler.class);
	
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException,ServletException {
		
		logger.debug("onAuthenticationSuccess start");
		
		String requestUrl;
		//UserInfo user = userService.findByUserName(authentication.getName());
		
		 
	//	String ctoken = (String) request.getSession().getAttribute(WebConstants.CSRF_TOKEN);
        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
 
  
        
        if( defaultSavedRequest != null ) {
        	
        	 System.out.println("onAuthenticationSuccess 111");
        	 
        	 requestUrl = defaultSavedRequest.getRequestURL();
        	 logger.info("저장된값:"+requestUrl);
             logger.debug("DefaultSavedRequest:{0}", requestUrl);
          //  requestUrl = UrlTool.addParamToURL(requestUrl, WebConstants.CSRF_TOKEN, ctoken, true);
        	
        	getRedirectStrategy().sendRedirect(request, response, requestUrl);
        	  
        } else {
        	
        	 
        	// logger.info("그냥진행");
        	//SavedRequest savedRequest = new DefaultSavedRequest(request, new PortResolverImpl());
        	//String url=savedRequest.getRedirectUrl();
        	// logger.info("Default Request Redirect URL: {}",request.getRequestURL() );
           //getRedirectStrategy().sendRedirect(request, response, ");
        	 if(logger.isInfoEnabled()){//개발모드
        		 
        		 logger.info("Default Request Redirect URI: {}",request.getRequestURI() );
        		 logger.info("Default Request Redirect URL: {}",request.getRequestURL() );
        		
        		 
        		if(request.getHeader("X-Ajax-call")!=null && request.getHeader("X-Ajax-call").equals("true")){
    			 	logger.info("ajax Login Success");
    			 
    				 Collection<GrantedAuthority> authList = SignedUser.getAuthorities();
    				 String roles = "";
    				 for(GrantedAuthority auth : authList){
    					 System.out.println(auth.getAuthority());
    					 roles += auth.getAuthority() +",";
    				 }
    				
    				 response.getWriter().print(roles);
 		             response.getWriter().flush();
    				
        			 
        		 }else{
        			 System.out.println("onAuthenticationSuccess  getRedirectStrategy().sendRedirect");
        			 //리다이렉트 할경우 포스트로 전송중이었던 테이타는 자동 로그인후 전송이 안된다 //리다이렉트 이기때문
        			 getRedirectStrategy().sendRedirect(request, response, request.getRequestURL().toString());
        			
        		 }
        	 }else{
        		 System.out.println("onAuthenticationSuccess  onAuthenticationSuccess(request, response, authentication)");
        		 super.onAuthenticationSuccess(request, response, authentication); 
        	 }
            
            
        }			
        
	}
	
	
	@Override 
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) { 

        String url = request.getParameter("redirect_after_login"); 
        if(url != null){ 
            return url; 
        }
        else 
            return super.determineTargetUrl(request, response); 
    } 

}
