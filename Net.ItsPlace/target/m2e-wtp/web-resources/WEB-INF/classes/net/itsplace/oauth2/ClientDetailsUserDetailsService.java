package net.itsplace.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import net.itsplace.user.CustomUserDetailsService;


	 public class ClientDetailsUserDetailsService implements UserDetailsService {

	 	private final ClientDetailsService clientDetailsService;
	 	private static final Logger logger =  LoggerFactory.getLogger(ClientDetailsUserDetailsService.class);

	 	public ClientDetailsUserDetailsService(ClientDetailsService clientDetailsService) {
	 		this.clientDetailsService = clientDetailsService;
	 	}

	 	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			try{
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("---------------------------------------------------------------------------------");
				
				System.out.println("ClientDetails start:"+username);
				ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
				
				System.out.println("ClientDetails username: {}"+username);
				System.out.println("ClientDetails clientId: {},Authorities:{} "+clientDetails.getClientId());
				String clientSecret = clientDetails.getClientSecret() == null ? "" : clientDetails.getClientSecret();
		 		return new User(username, clientSecret, clientDetails.getAuthorities());
			
			}catch(Exception e){
				System.out.println("ClientDetails:{}에러"+e.getMessage());
				
		
			}
			return null;
	 	}

	 }


