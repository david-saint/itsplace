package com.myplace.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

public class RememberAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private UserService userService;
	  
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException,ServletException {
		
		System.out.println("리멤버미 성공");
		System.out.println("리멤버미 성공");
		System.out.println("리멤버미 성공");
		System.out.println("리멤버미 성공");
		System.out.println("리멤버미 성공");
		//request.getSession().setAttribute("USERSESSION",userService.getUser(authentication.getName()));
		
	//	String ctoken = (String) request.getSession().getAttribute(WebConstants.CSRF_TOKEN);
        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
        
        
        if( defaultSavedRequest != null ) {
        	String requestUrl = defaultSavedRequest.getRequestURL();
            System.out.println("리다이렉트:" + requestUrl  );
          //  requestUrl = UrlTool.addParamToURL(requestUrl, WebConstants.CSRF_TOKEN, ctoken, true);
            getRedirectStrategy().sendRedirect(request, response, requestUrl);
        } else {
        	//SavedRequest savedRequest = new DefaultSavedRequest(request, new PortResolverImpl());
        	//String url=savedRequest.getRedirectUrl();
        System.out.println("디폴트로 리다이렉트:"+request.getRequestURI() );
        
            //super.onAuthenticationSuccess(request, response, authentication);
        getRedirectStrategy().sendRedirect(request, response, request.getRequestURI());
            
        }				
	} 

}
