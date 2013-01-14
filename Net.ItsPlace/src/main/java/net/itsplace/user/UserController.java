package net.itsplace.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;




import net.itsplace.domain.Bascd;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Bascd.AddBascd;
import net.itsplace.place.controller.PlaceCommentController;
import net.itsplace.service.IUserService;
import net.itsplace.user.User.AddUser;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private PersistentTokenBasedRememberMeServices rememberMeServices;
	
	@Autowired
	private JsonResponse json;
	
	@RequestMapping(value = "/signup2", method = RequestMethod.GET)
	public String signup2( Model model,HttpServletRequest request) {
		model.addAttribute("user",new User());
		return "user/register";
	}
	
	@RequestMapping(value="/temp", method=RequestMethod.GET)
	public String temp() {
		return "user/temp";
	}
	/**
	 * 소셜 로그인 최초등록일경
	 * @author 김동훈
	 * @version 1.0, 2011.8.15 
	 * @param 
	 * @return  접근권한 없음 페이지
	 * @see 
	 */
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup( Model model, WebRequest request) {
		System.out.println("facebook login");
		User user = new User();
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			//request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Spring Social Showcase account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			//return SignupForm.fromProviderUser(connection.fetchUserProfile());
			UserProfile providerUser =	connection.fetchUserProfile();
			System.out.println("사인업"+providerUser.getEmail()+providerUser.getName()+providerUser.getUsername());
			
			user.setEmail(providerUser.getEmail());
			user.setName(providerUser.getName());
			user.setProfileImageUrl(connection.getProfileUrl());
			user.setRole("ROLE_USER");
			
//			userService.saveUser(user);
//			User dbUser = userService.getUser(user.getEmail());
//			CustomUserDetailsService cuser = new CustomUserDetailsService();
//			CustomUserDetails details = new CustomUserDetails(
//					dbUser, 
//					dbUser.getEmail(),						
//					dbUser.getPassword().toLowerCase(),
//					true,
//					true,
//					true,
//					true,
//					cuser.getAuthorities("ROLE_USER"));
//			//UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(details, null);
//			UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(details, details.getUser().getPassword(),cuser.getAuthorities("ROLE_USER"));
//			SecurityContextHolder.getContext().setAuthentication(newAuth);
			ProviderSignInUtils.handlePostSignUp(providerUser.getEmail(), request);
//			return "redirect:/places";
			model.addAttribute("user", user);
			return "user/signup";
		} else {
			//return new SignupForm();
			System.out.println("facebook login 회원가입으");
			model.addAttribute("userForm", user);
			model.addAttribute("user", user);
			return "user/signup";
		}
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated({AddUser.class}) User user, BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("signup--------------------->");
		
		if (result.hasErrors()) {
			logger.debug("필드에러:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			//json =  json.getValidationErrorResult(result, json);
			return "user/signup";
		} else {	
			userService.saveUser(user);
			json.setResult(user);
			json.setSuccess();
			User dbUser = userService.getUser(user.getEmail());
			CustomUserDetailsService cuser = new CustomUserDetailsService();
			CustomUserDetails details = new CustomUserDetails(
					dbUser, 
					dbUser.getEmail(),						
					dbUser.getPassword(),
					true,
					true,
					true,
					true,
					cuser.getAuthorities("ROLE_USER"));
			System.out.println("password:"+details.getUser().getPassword() + user.getEmail());
			logger.info("가입완료");
			UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(details, details.getUser().getPassword(),cuser.getAuthorities("ROLE_USER"));
			HttpServletRequest nativeReq = request;
			HttpServletResponse nativeRes = response;
			nativeReq.setAttribute("_spring_security_remember_me", "1");
			
			SecurityContextHolder.getContext().setAuthentication(newAuth);
			rememberMeServices.setKey("itsplace");
			rememberMeServices.setParameter("_spring_security_remember_me");
			rememberMeServices.setAlwaysRemember(true);
			rememberMeServices.loginSuccess(nativeReq,nativeRes,newAuth);
			
		}
		return "redirect:/places";
	}
	
	@RequestMapping(value = "/user/saveUser", method = RequestMethod.POST)
	public String saveUser(@Validated({AddUser.class}) User user, BindingResult result, Model model) 
	{
		logger.info("user.getEmail():{}",user.getEmail());
		userService.saveUser(user);
		return "redirect:/";
	}
	
	// 안드로이드 Json용 회원가입	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public @ResponseBody JsonResponse register(@Validated({AddUser.class}) User user, BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) {
		logger.info("register--------------------->");
		
		if (result.hasErrors()) {
			logger.debug("필드에러:"+result.getObjectName() +": "+ result.getFieldError().getDefaultMessage());
			json =  json.getValidationErrorResult(result, json);
		} else {	
			userService.saveUser(user);
			json.setResult(user);
			json.setSuccess();
			User dbUser = userService.getUser(user.getEmail());
			CustomUserDetailsService cuser = new CustomUserDetailsService();
			CustomUserDetails details = new CustomUserDetails(
					dbUser, 
					dbUser.getEmail(),						
					dbUser.getPassword(),
					true,
					true,
					true,
					true,
					cuser.getAuthorities("ROLE_USER"));
			System.out.println("password:"+details.getUser().getPassword() + user.getEmail());
			logger.info("가입완료");
			UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(details, details.getUser().getPassword(),cuser.getAuthorities("ROLE_USER"));
			HttpServletRequest nativeReq = request;
			HttpServletResponse nativeRes = response;
			nativeReq.setAttribute("_spring_security_remember_me", "1");
			
			SecurityContextHolder.getContext().setAuthentication(newAuth);
			rememberMeServices.setKey("itsplace");
			rememberMeServices.setParameter("_spring_security_remember_me");
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
			}*/
		}		
		return json;
	}
	
	@RequestMapping(value = "/user/getUser", method = RequestMethod.POST)
	public  @ResponseBody  User login(User user) {
		logger.info("email:{}",user.getEmail());
		return userService.getUser(user.getEmail());
	}
}
