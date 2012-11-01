/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.itsplace.social;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.itsplace.user.CustomUserDetails;
import net.itsplace.user.CustomUserDetailsService;
import net.itsplace.user.User;
import net.itsplace.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@Component
public class SimpleSignInAdapter implements SignInAdapter {

	private final RequestCache requestCache;
	
	
	private PersistentTokenBasedRememberMeServices rememberMeServices;
	private UserService userService ;
	
	@Inject
	public SimpleSignInAdapter(RequestCache requestCache, UserService userService,PersistentTokenBasedRememberMeServices rememberMeServices) {
		this.requestCache = requestCache;
		this.userService = userService;
		this.rememberMeServices = rememberMeServices;
	}
	
	@Override
	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
		//소셜 인증을 받으면 이리로 들어온다
		System.out.println("사인인 어탭터:"+localUserId+"extractOriginalUrl(request):"+extractOriginalUrl(request));
		CustomUserDetailsService cuser = new CustomUserDetailsService();
		
		User user = userService.getUser(localUserId);
		// Principal principal = new Princip();
		// UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(localUserId, "itsplace!@#$", cuser.getAuthorities("ROLE_USER"));
		CustomUserDetails details = new CustomUserDetails(
				user, 
				localUserId,						
				"",
				true,
				true,
				true,
				true,
				cuser.getAuthorities("ROLE_USER"));
		System.out.println("password:"+details.getUser().getPassword());
		
		UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(details, details.getUser().getPassword(),cuser.getAuthorities("ROLE_USER"));
		//	RememberMeAuthenticationToken newAuth = new RememberMeAuthenticationToken("itsplace",details,cuser.getAuthorities("ROLE_USER"));
		//PersistentTokenBasedRememberMeServices d ; d.
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
		nativeReq.setAttribute("_spring_security_remember_me", "1");
		
		SecurityContextHolder.getContext().setAuthentication(newAuth);
		//rememberMeServices.setKey("itsplace");
		//rememberMeServices.setParameter("_spring_security_remember_me");
		rememberMeServices.setAlwaysRemember(true);
		rememberMeServices.loginSuccess(nativeReq,nativeRes,newAuth);
		/*if(rememberMeServices.autoLogin(nativeReq, nativeRes)==null){
			
			rememberMeServices.loginSuccess(nativeReq,nativeRes,newAuth);
			System.out.println("rememberMeServices.loginSuccess(nativeReq,nativeRes,newAuth);");
			System.out.println("rememberMeServices.loginSuccess(nativeReq,nativeRes,newAuth);");
			System.out.println("rememberMeServices.loginSuccess(nativeReq,nativeRes,newAuth);");
			System.out.println("rememberMeServices.loginSuccess(nativeReq,nativeRes,newAuth);");
		}else{
			System.out.println("cookey exit");
			System.out.println("cookey exit");
			System.out.println("cookey exit");
			System.out.println("cookey exit");
			System.out.println("cookey exit");
			System.out.println("cookey exit");
			System.out.println("cookey exit");
			System.out.println("cookey exit");
		}
*/
//		SecurityContextHolder.getContext().setAuthentication(rememberMeAuthenticationToken);
		
//		return extractOriginalUrl(request);
		System.out.println("리다이텍트 소셜 로그인");
		return "redirect:/places";
	}

	private String extractOriginalUrl(NativeWebRequest request) {
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
		SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
		if (saved == null) {
			System.out.println("saved null");
			return null;
		}
		requestCache.removeRequest(nativeReq, nativeRes);
		removeAutheticationAttributes(nativeReq.getSession(false));
		return saved.getRedirectUrl();
	}
		 
	private void removeAutheticationAttributes(HttpSession session) {
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
