package net.itsplace.user;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.place.service.PlaceUserService;
import net.itsplace.user.User;
import net.itsplace.user.UserService;
import net.itsplace.util.UrlTool;
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
	@Autowired
	private PlaceUserService placeUserService;
	
	private static final Logger logger =  LoggerFactory.getLogger(LoginSuccessHandler.class);
	
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException,ServletException {
		
		System.out.println("onAuthenticationSuccess start");
		String requestUrl;
		User user = userService.getUser(authentication.getName());
		//request.getSession().setAttribute("USERSESSION",user);
		
		 
		 System.out.println("onAuthenticationSuccess user.getRole():"+user.getRole());
		 
	//	String ctoken = (String) request.getSession().getAttribute(WebConstants.CSRF_TOKEN);
        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
        if(user.getRole().equals("ROLE_FID")){
    		requestUrl="/partner/fid";     				
    	//	getRedirectStrategy().sendRedirect(request, response, requestUrl);
            
    	}else if(user.getRole().equals("ROLE_FRANCHISER")){
    		requestUrl="/partner/main";     				
    		//getRedirectStrategy().sendRedirect(request, response, requestUrl);
 
    	}
        if(user.getRole().equals("ROLE_FID") || user.getRole().equals("ROLE_FRANCHISER")){
        	List<Place> placeList = placeUserService.getFranchiserListByEmail(UserInfo.getEmail());

     		if(UserInfo.getFid()==0){
     			UserInfo.setFid(placeList.get(0).getFid());
     		}
     		UserInfo.setPlaceList(placeList);
        }else{
        	List<Place> placeList = placeUserService.getFranchiserListByEmail(UserInfo.getEmail());

     		if(UserInfo.getFid()==0){
     			UserInfo.setFid(placeList.get(0).getFid());
     		}
     		UserInfo.setPlaceList(placeList);
        }
       
		
        System.out.println("onAuthenticationSuccess 가맹점 fid:"+UserInfo.getFid());
        
        if( defaultSavedRequest != null ) {
        	
        	 System.out.println("onAuthenticationSuccess 111");
        	 
        	 requestUrl = defaultSavedRequest.getRequestURL();
        	 logger.info("저장된값:"+requestUrl);
             logger.debug("DefaultSavedRequest:{0}", requestUrl);
          //  requestUrl = UrlTool.addParamToURL(requestUrl, WebConstants.CSRF_TOKEN, ctoken, true);
        	
        	getRedirectStrategy().sendRedirect(request, response, requestUrl);
        	  
        } else {
        	 System.out.println("onAuthenticationSuccess 1222211");
        	 
        	 logger.info("그냥진행");
        	//SavedRequest savedRequest = new DefaultSavedRequest(request, new PortResolverImpl());
        	//String url=savedRequest.getRedirectUrl();
        	 logger.info("Default Request Redirect URL: {}",request.getRequestURL() );
           //getRedirectStrategy().sendRedirect(request, response, ");
        	 if(logger.isInfoEnabled()){//개발모
        		 logger.info("Default Request Redirect URI: {}",request.getRequestURI() );
        		
        		 
        		 if(request.getHeader("X-Ajax-call")!=null){
        			 logger.info("ajax Login Success");
        			 if (request.getHeader("X-Ajax-call").equals("true")) {
        				 if( UserInfo.getUser().getRole().equals("ROLE_ADMIN") ){
        		            response.getWriter().print("ROLE_ADMIN");
        		            response.getWriter().flush();
        		        } else if( UserInfo.getUser().getRole().equals("ROLE_USER") ){
        		        	response.getWriter().print("ROLE_USER");
        		            response.getWriter().flush();
        		        }
        			 }
        		 }else{        			 
        			 getRedirectStrategy().sendRedirect(request, response, request.getRequestURL().toString());
        		 }
        	 }else{
        		 super.onAuthenticationSuccess(request, response, authentication); 
        	 }
            
            
        }			
        System.out.println("onAuthenticationSuccess end");
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
