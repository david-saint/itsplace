package com.mincoms.book.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.repository.UserRepository;

public class SignedUser {
	public static CustomUserDetails currentUserDetails() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			return (CustomUserDetails) (principal instanceof UserDetails ? principal
					: null);
		}
		return null;
	}

	public static int getUserId() {
		return currentUserDetails().getUser().getUserId();
	}

	public static String getUserName() {
		return currentUserDetails().getUsername();
	}

	public static String getEmail() {
		return currentUserDetails().getUser().getEmail();
	}

	@Deprecated
	public static UserInfo getUserInfo() {
		return currentUserDetails().getUser();
	}

	public static Collection<GrantedAuthority> getAuthorities() {
		return currentUserDetails().getAuthorities();
	}

}
