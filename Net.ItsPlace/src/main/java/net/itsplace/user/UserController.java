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

import com.google.gdata.util.common.base.StringUtil;




import net.itsplace.controller.partner.PlaceCommentController;
import net.itsplace.domain.Bascd;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.User;
import net.itsplace.domain.Bascd.AddBascd;
import net.itsplace.domain.User.AddUser;
import net.itsplace.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
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
	 * 소셜 로그인 최초등록(회원가입시 비밀번호가 공백임) 그리고 이메일 회원가입 
	 * @author 김동훈
	 * @version 1.0, 2013.3.19  
	 * @param 
	 * @return  접근권한 없음 페이지
	 * @see 
	 */
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup( Model model, WebRequest request,User signupEmail) {
		
		System.out.println("social login");
		
		User user = new User();
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			//request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Spring Social Showcase account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			//return SignupForm.fromProviderUser(connection.fetchUserProfile());
			UserProfile providerUser =	connection.fetchUserProfile();

//			System.out.println("사인업:email:"+providerUser.getEmail()+" name:"+providerUser.getName()+ "username:"+providerUser.getUsername()+connection.getProfileUrl());
			String userEmail = "";
			if(signupEmail.getEmail()!=null){
				System.out.println("email:" +signupEmail.getEmail());
				if(net.itsplace.util.StringUtil.patternMatch("email", signupEmail.getEmail())){
					user.setName(providerUser.getName());
					userEmail = signupEmail.getEmail();
					if(userService.getUser(userEmail) != null){
						model.addAttribute("error","email은 이미 사용중입니다.");	
						model.addAttribute("user", user);
						return "forward:/signup-email";
					}
				}else{
					user.setName(providerUser.getName());
					model.addAttribute("error","email을 잘못입력하셧습니다.");	
					model.addAttribute("user", user);
					return "forward:/signup-email";
				}
			}else{
				if(providerUser.getEmail() == null || providerUser.getEmail().isEmpty()){
					
					//개인정보에서 이메일 공개하지않을겨우
					
					user.setName(providerUser.getName());
//				    user.setProfileImageUrl(connection.getProfileUrl());
//				    user.setPassword(Math.random()+"itsplace");
					model.addAttribute("user", user);
				
					//이메일 입력후 가입 계속 진행
					return "forward:/signup-email";
					//return  "forward:/signup-email";
				}
				
			}
			if(userEmail.isEmpty()){
				userEmail = providerUser.getEmail();
			}
			
			user.setEmail(userEmail);
			user.setName(providerUser.getName());
			user.setProfileImageUrl(connection.getImageUrl());
			user.setRole("ROLE_USER");
			user.setPassword(Math.random()+"itsplace");
			
			if(userService.getUser(userEmail) != null){
				model.addAttribute("error","email은 이미 사용중입니다.");	
				model.addAttribute("user", user);
				return "forward:/signup-email";
			}
			userService.saveUser(user);
			
			User dbUser = userService.getUser(user.getEmail());
			CustomUserDetailsService cuser = new CustomUserDetailsService();
			CustomUserDetails details = new CustomUserDetails(
					dbUser, 
					dbUser.getEmail(),						
					dbUser.getPassword().toLowerCase(),
					true,
					true,
					true,
					true,
					cuser.getAuthorities("ROLE_USER"));
			//UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(details, null);
			UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(details, details.getUser().getPassword(),cuser.getAuthorities("ROLE_USER"));
			SecurityContextHolder.getContext().setAuthentication(newAuth);
			//ProviderSignInUtils.handlePostSignUp(providerUser.getEmail(), request);
			ProviderSignInUtils.handlePostSignUp(userEmail, request);
			return "redirect:/places";
			
			
			//model.addAttribute("user", user);
			//return "user/signup";
		} else {
			//return new SignupForm();
			//System.out.println("facebook login 회원가입으");
			//model.addAttribute("userForm", user);
			model.addAttribute("user", user);
			return "web/user/sign-in";
		}
	}
	
	/**
	 * facebook/트위터 개인계정에서 이메일 허용을 하지 않았을경우 이메일을 입력한다.
	 * @author 김동훈
	 * @version 1.0, 2013.3.19 
	 * @param 
	 * @return  접근권한 없음 페이지
	 * @see 
	 * @return
	 */
	@RequestMapping(value="/signup-email", method=RequestMethod.GET)
	public String signupemail( Model model, WebRequest request) {
		model.addAttribute("user",model);
		return "user/signup";
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
