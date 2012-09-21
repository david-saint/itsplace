package com.mincoms.book.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.repository.UserRepository;
import com.mincoms.book.service.UserService;



public class BookUserDetailsService implements UserDetailsService {
	
	private static final Logger logger =  LoggerFactory.getLogger(BookUserDetailsService.class);

	@Autowired
	private UserService userService;


	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		
		UserDetails user = null;
		
		if(userService == null){
			logger.info("서비스 인젝션 실패");
			
		}
		try {
			
			UserInfo dbUser =  userService.findByUserName(userName);	 
			
			user = new CustomUserDetails(
						dbUser, 
						dbUser.getUserName(),						
						dbUser.getPassword().toLowerCase(),
						true,
						true,
						true,
						true,
						getAuthorities(dbUser.getAuthlevel())					
					);

		} catch (Exception e) {
			logger.error(e.toString()+"사용자가 없습니다.");
			//throw new UsernameNotFoundException("Error in retrieving user");
		}
		
	
		return user;
	}
	
	
	 public Collection<GrantedAuthority> getAuthorities(int authlevel) {
			// Create a list of grants for this user
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(3);
			//0 :  관리자
			//1 : 팀장
			//2 : 사원
			
			//모든 사용자는 일반사용자 권한도 가짐
			authList.add(new GrantedAuthorityImpl(Authority.WORKER.name()));
			
			if(authlevel == Authority.ADMIN.ordinal()){							
				authList.add(new GrantedAuthorityImpl(Authority.ADMIN.name()));
				
			}else if(authlevel == Authority.TEAMLEADER.ordinal()){		
				authList.add(new GrantedAuthorityImpl(Authority.TEAMLEADER.name()));
			}
			return authList;
	 }	  
}
