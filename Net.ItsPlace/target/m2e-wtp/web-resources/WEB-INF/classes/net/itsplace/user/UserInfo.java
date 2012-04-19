package net.itsplace.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfo {
	
	public static CustomUserDetails currentUserDetails(){
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        return (CustomUserDetails) (principal instanceof UserDetails ? principal : null);
	    }
		return null;
	}

	/**
	 * 사용자 아이디
	 * @return
	 */
	public static String getEmail()
	{
		CustomUserDetails customUserDetails;
        customUserDetails =  currentUserDetails();
        return customUserDetails.getUser().getEmail();
	}
	

	/**
	 * 사용자 아이디
	 * @return
	 */
	public static User getUser()
	{
		CustomUserDetails customUserDetails;
        customUserDetails =  currentUserDetails();
        return  customUserDetails.getUser();
	}
	
	public static boolean autoLogin(String username,String password) {
		try {
			CustomUserDetailsService us = new CustomUserDetailsService();
		 // UserDetails userDetails =  us.loadUserByUsername(username);
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
