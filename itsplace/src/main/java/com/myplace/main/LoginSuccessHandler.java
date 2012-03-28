package com.myplace.main;

import java.io. IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import com.myplace.partner.franchiser.stamp.StampController;
import com.myplace.user.User;
import com.myplace.user.UserService;
import com.myplace.util.UrlTool;
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

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private UserService userService;
	private static final Logger logger =  LoggerFactory.getLogger(LoginSuccessHandler.class);
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException,ServletException {
		String requestUrl;
		User user = userService.getUser(authentication.getName());
		request.getSession().setAttribute("USERSESSION",user);
		
	//	String ctoken = (String) request.getSession().getAttribute(WebConstants.CSRF_TOKEN);
        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
        if(user.getRole().equals("ROLE_FID")){
    		requestUrl="/partner/fid";     				
    		getRedirectStrategy().sendRedirect(request, response, requestUrl);
            
    	}else if(user.getRole().equals("ROLE_FRANCHISER")){
    		requestUrl="/partner/main";     				
    		getRedirectStrategy().sendRedirect(request, response, requestUrl);
 
    	}
        
        if( defaultSavedRequest != null ) {
        	 requestUrl = defaultSavedRequest.getRequestURL();
             logger.debug("DefaultSavedRequest:{0}", requestUrl);
          //  requestUrl = UrlTool.addParamToURL(requestUrl, WebConstants.CSRF_TOKEN, ctoken, true);
        	
        	getRedirectStrategy().sendRedirect(request, response, requestUrl);
            
        } else {
        	//SavedRequest savedRequest = new DefaultSavedRequest(request, new PortResolverImpl());
        	//String url=savedRequest.getRedirectUrl();
        	 logger.debug("Default Request Redirect: {0}",request.getRequestURI() );
           //getRedirectStrategy().sendRedirect(request, response, ");
            super.onAuthenticationSuccess(request, response, authentication);
            
        }				
	}
	
	/**
	 *  Jquery 로그인시 사용
	 * */
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
