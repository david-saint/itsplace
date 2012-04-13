package com.myplace.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
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
        return customUserDetails.getEmail();
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
	

}
