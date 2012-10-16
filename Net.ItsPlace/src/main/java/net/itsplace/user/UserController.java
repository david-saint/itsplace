package net.itsplace.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
import net.itsplace.user.User.AddUser;
import net.itsplace.user.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/signup2", method = RequestMethod.GET)
	public String signup( Model model,HttpServletRequest request) {
		model.addAttribute("user",new User());
		return "user/register";
	}
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signupForm( Model model, WebRequest request) {
		System.out.println("사인업");
		User user = new User();
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			//request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Spring Social Showcase account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			//return SignupForm.fromProviderUser(connection.fetchUserProfile());
			UserProfile providerUser =	connection.fetchUserProfile();
			System.out.println("사인업"+providerUser.getEmail()+providerUser.getName()+providerUser.getUsername());
			user.setPassword("itsplace");
			user.setEmail(providerUser.getEmail());
			user.setName(providerUser.getName());
			user.setProfileImageUrl(connection.getProfileUrl());
			user.setRole("ROLE_USER");
		
			userService.saveUser(user);
			CustomUserDetailsService cuser = new CustomUserDetailsService();
			
			
				CustomUserDetails details = new CustomUserDetails(
						user, 
						user.getEmail(),						
						user.getPassword().toLowerCase(),
						true,
						true,
						true,
						true,
						null);
				UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(details, null);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
			
			ProviderSignInUtils.handlePostSignUp(providerUser.getEmail(), request);
			//이미 회원가입했다면 사인어댑터 실행함
			return "redirect:/";
			//회원가입후 로그인 시키고 
		} else {
			//return new SignupForm();
		}
		model.addAttribute("user", user);
		return "user/register";
	}
	@RequestMapping(value = "/user/saveUser", method = RequestMethod.POST)
	public String saveUser(@Validated({AddUser.class}) User user, BindingResult result, Model model) 
	{
		logger.info("user.getEmail():{}",user.getEmail());
		userService.saveUser(user);
		return "redirect:/";
	}
	@RequestMapping(value = "/user/saveUserJson", method = RequestMethod.POST)
	public @ResponseBody JsonResponse saveUserJson(@Validated({AddUser.class}) User user, BindingResult result, Model model) 
	{
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			 List<ObjectError> errorList = result.getAllErrors();
			 String errorMessages = "";
	            for(int i=0; i< errorList.size();i++){
	                ObjectError error = errorList.get(i);
	                System.out.println(error.toString());
	                errorMessages += error.toString();
	            }
			json.setResult(result.getFieldError().getDefaultMessage());
			json.setStatus("FAIL");
		} else {	
			userService.saveUser(user);
			json.setResult(user);
			json.setStatus("SUCCESS");
			
		}		
		return json;
	}
	
	@RequestMapping(value = "/user/getUser", method = RequestMethod.POST)
	public  @ResponseBody  User login(User user) {
		logger.info("email:{}",user.getEmail());
		return userService.getUser(user.getEmail());
	}
}
