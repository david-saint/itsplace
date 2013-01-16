package net.itsplace.user;

import java.util.List;

import net.itsplace.domain.Place;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfo {
	final static public String ROLE_USER = "ROLE_USER";
	final static public String ROLE_ADMIN = "ROLE_ADMIN";
	final static public String ROLE_FRANCHISER = "ROLE_FRANCHISER";
	final static public String ROLE_FID = "ROLE_FID";
	

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

	public static int getFid() {
		CustomUserDetails customUserDetails;
        customUserDetails =  currentUserDetails();
        return customUserDetails.getFid();
	}

	public static void setFid(int fid) {
		CustomUserDetails customUserDetails;
        customUserDetails =  currentUserDetails();
        customUserDetails.setFid(fid);
	}
	public static void setPlaceList(List<Place> placeList) {
		CustomUserDetails customUserDetails;
		customUserDetails =  currentUserDetails();
		customUserDetails.setPlaceList(placeList);
	}
	
}
