package net.itsplace.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.itsplace.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService extends SqlMapClientDaoSupport implements UserDetailsService {
	
	private static final Logger logger =  LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;
	
	
	/**
	 * Retrieves a user record containing the user's credentials and access. 
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		
		// Declare a null Spring User
		UserDetails user = null;
		
		try {
			
			
			net.itsplace.domain.User dbUser = userService.getUser(username);	 
			
		
			
			
			// Populate the Spring User object with details from the dbUser
			// Here we just pass the username, password, and access level
			// getAuthorities() will translate the access level to the correct role type
		
			user = new CustomUserDetails(
						dbUser, 
						dbUser.getEmail(),						
						dbUser.getPassword().toLowerCase(),
						true,
						true,
						true,
						true,
						getAuthorities(dbUser.getRole())
						/*user =  new User(
					dbUser.getEmail(), 
					dbUser.getPassword().toLowerCase(),
					true,
					true,
					true,
					true,
					getAuthorities(dbUser.getRole()) */
					);

		} catch (Exception e) {
			logger.error(e.toString()+"사용자가 없습니다.");
			//throw new UsernameNotFoundException("Error in retrieving user");
		}
		
		// Return user to Spring for processing.
		// Take note we're not the one evaluating whether this user is authenticated or valid
		// We just merely retrieve a user that matches the specified username
		return user;
	}
	
	/**
	 * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
	 * Basically, this interprets the access value whether it's for a regular user or admin.
	 * 
	 * @param access an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	 public Collection<GrantedAuthority> getAuthorities(String flag) {
			// Create a list of grants for this user
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(3);
			//0 : admin 관리자
			//1 : franchises 가맹점
			//2 : user 사용자
			//3 : franchises + user
			//11 : 가맹점 + 1 = 가맹점 프리머어 서비스
			//authList 권한 리스트 설계해야함..
			
			//모든 사용자는 일반사용자 권한도 가짐
			authList.add(new GrantedAuthorityImpl("ROLE_USER"));
			
			
			// Check if this user has admin access 
			// We interpret Integer(1) as an admin user
			if( flag.trim().equals("ROLE_ADMIN") ){
				
				logger.info("flag:" + flag +" ROLE_ADMIN 관리자 권한 획득");
				authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
				
			}else if(flag.trim().equals("ROLE_FRANCHISER")){
				
				logger.info("flag:" + flag +" ROLE_FRANCHISER 관리자 권한 획득");
				authList.add(new GrantedAuthorityImpl("ROLE_FRANCHISER"));
			
			}else if(flag.trim().equals("ROLE_FID")){
				
				logger.info("flag:" + flag +" ROLE_FID 가맹점 전용 아이디 스탬프 기능");
				authList.add(new GrantedAuthorityImpl("ROLE_FID"));
			
				
			}

			// Return list of granted authorities
			return authList;
	  }
}
