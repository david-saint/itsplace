/**
 * 
 */
package com.myplace.user;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.myplace.common.CommonService;
import com.myplace.common.mail.MailService;
import com.myplace.util.Encrypt;
import com.myplace.util.PagingManager;
import com.myplace.util.StandardOrMobile;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
public class UserController {
        
	private static final Logger logger =  LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReCaptcha reCaptcha;
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private MailService mailService;
	/*
	 * 로그인 사용자
	 */
	
	public String getUserEmail(){
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth = securityContext.getAuthentication();
		return auth.getName();
	}
	
	/* 메일사용여부 확인 */
	@RequestMapping(value = "/user/isUserEmail", method = RequestMethod.GET)
 	public @ResponseBody Boolean isUsetEmail(@RequestParam String email) {
		
		logger.info("email:{}",email);
		User user= userService.getUser(email);		
		if(user == null){
			return false;
		}else{
			return true;
		}
				
	}
	
	/* 회원가입 폼 페이지 */
	@RequestMapping(value = "/user/newAccount", method = RequestMethod.GET)
 	public String signUpForm(Map model) {
		User user = new User();
		model.put("user", user);
		logger.debug("회원가입 폼 페이지");		
		
		return "user/newAccount";
	}
	
	/* 회원가입 신청*/
	@RequestMapping(value = "/user/newAccount", method = RequestMethod.POST)
 	public String newAccountSubmit(@Valid User user, BindingResult result,HttpServletRequest request,Device device,SitePreference sitePreference, Model model) {
		
		logger.info("newAccountSubmit:"+user.toString());
		logger.info("Device:{}",device.isMobile());
		 java.util.Enumeration names = request.getHeaderNames();
         while (names.hasMoreElements()) {
         String hname = (String)names.nextElement();
		 logger.info("getHeaderName:{} value: {}",hname,request.getHeader(hname));
         }
         logger.info("sitePreference:"+sitePreference.isMobile()+"");
		/* 리캣차 사용중지
		ReCaptchaResponse response = reCaptcha.checkAnswer(request
                .getRemoteAddr(), request
                .getParameter("recaptcha_challenge_field"), request
                .getParameter("recaptcha_response_field"));

        if (response.isValid()) {
           logger.info("리캣차 성공");
        } else {
           logger.info("리캣차 실패: " + response.getErrorMessage());
           return "user/newAccount";
        }
        */
		if (result.hasErrors()) {
			logger.info(result.getObjectName() + result.getFieldError().getDefaultMessage() +"------------발생");
			return "user/newAccount";
		} else {	
			logger.info("사용자 저장");
				userService.saveUser(user);		
		
			//return "redirect:/user/login?account=true";
			
			model.addAttribute("user",user);
			return StandardOrMobile.getPageName(device, sitePreference,  "user/m_loginpage",   "user/loginpage");
		}
	
	}
	
	/* 회원가입 */
	@RequestMapping(value = "/user/newAccountJson", method = RequestMethod.POST)
 	public @ResponseBody String newAccountJson(@Valid User user, BindingResult result,HttpServletRequest request) {
		
		logger.info("newAccountSubmitJson:"+user.toString());
		
		 java.util.Enumeration names = request.getHeaderNames();
         while (names.hasMoreElements()) {
         String hname = (String)names.nextElement();
		 logger.info("getHeaderName:{} value: {}",hname,request.getHeader(hname));
         }
       
		if (result.hasErrors()) {
			logger.info(result.getFieldError().getField()+result.getObjectName() + result.getFieldError().getDefaultMessage() +"------------발생");
			return result.getFieldError().getDefaultMessage();
		} else {	
			logger.info("사용자 저장");
				user.setEmailToken(UUID.randomUUID().toString());
				userService.saveUser(user);		
				String authUrl = "http://localhost:8080/MyPlace/User/confirmEmail?email="+user.getEmail()+"&token="+user.getEmailToken();
				String authTag="<a href=\""+ authUrl +"\">" + authUrl +"</a>";
				mailService.sendMail("admin@itsplace.net", user.getEmail(), "[signUp]"+user.getName(), "signup email token:"+ authTag);
						
			//return "redirect:/user/login?account=true";
			
		
			return "true";
		}
	
	}
	/* Email confirm */
	@RequestMapping(value = "/user/confirmEmail", method = RequestMethod.GET)
	public String confirmEmail(@RequestParam String email,@RequestParam String token,HttpServletRequest request) {
		logger.info("Email:"+email);
		logger.info("token:"+token);	
		User user= userService.getUser(email,token);		
		if(user != null){
			request.getSession().setAttribute("USERSESSION",user);
		}
		request.getSession().setAttribute("confirmEmail", true);
		//로그인 되었으면 메인페이지로 이메일 확인되었다는 메세지창
		//아니면 로그인 화면이동하고 ㅇ이메일 확인메세지창
		//노티피케이션 띄우
		//request.getSession().setAttribute("confirmEmail", true);
		//RedirectView rv = new RedirectView(request.getContextPath());
		//rv.setExposeModelAttributes(false);
		
		//return new ModelAndView(rv).;
		return "redirect:/";//+request.getContextPath();
	}
	
	/* 회원정보 수정폼*/
	@RequestMapping(value = "/user/accountDetail", method = RequestMethod.GET)
	public String accountDetail(Model model,HttpServletRequest request) {
		logger.info("Email:"+getUserEmail());		
		User user= userService.getUser(getUserEmail());		
		model.addAttribute("user",user);		
		return "user/accountDetail";
		
	}
	/* 회원정보 수정*/
	@RequestMapping(value = "/user/accountDetail", method = RequestMethod.POST)  
	public String accountDetailSubmit(User user, HttpServletRequest request) {
		
		logger.info("사용자정보 수정");
	
		userService.updateUser(user);	
		
		request.getSession().setAttribute("USERSESSION",userService.getUser(user.getEmail()));
		
		return "user/accountDetailConfirm";
	}
	/* me2day 사용자 인증 */
	@RequestMapping(value = "/user/me2dayUpdate", method = RequestMethod.GET)  
	public String me2dayUpdate(HttpServletRequest request,Model model) {
		logger.info("user_id: " + request.getParameter("user_id"));
		logger.info("user_key: " + request.getParameter("user_key"));
		logger.info("token: " + request.getParameter("token"));
		//nonce + md5(nonce + user_key)
		
		String nonce = "abcdefgh";
		String ukey = nonce + Encrypt.md5Encoding(nonce+request.getParameter("user_key"));
		String akey = "2db5ebe151fa46f4df6cc3ee3320ae4f";//hoon12005
		model.addAttribute("ukey",ukey);
		model.addAttribute("akey",akey);
		model.addAttribute("user_id",request.getParameter("user_id"));
		
		
		Social social = new Social();
		social.setEmail(getUserEmail());
		social.setSocial("me2day");
		social.setToken(akey);
		social.setUserkey(ukey);
		social.setUserid(request.getParameter("user_id"));
		
		
		userService.saveSocial(social);
		
		/*
				uid = user_id
				ukey = 인증키
				akey =  애플리케이션키
				http://me2day.net/api/noop?uid=codian&ukey=XXXXXXXXXXXXXXXX&akey=XXXXXXXXXXXXXXXX
				
		*/
		return "user/me2dayUpdate";
	}
	/* 회원리스트*/
	/*@RequestMapping(value = "/user/userList", method = RequestMethod.GET, headers="Accept=application/json")
	public  @ResponseBody List<User> getUserListJSON() {
		Map<String, Object> param;
		param = new HashMap<String, Object>();
		
		List<User> userList= userService.getUserList(param);
		
		
		//model.addAttribute("userList",userList);
		
		return userList;
		create table PSOCIAL(EMAIL VARCHAR(100), SOCIAL VARCHAR(10), USERID  VARCHAR(20), USERKEY VARCHAR(200), TOKEN VARCHAR(200))
	}
	*/
	/* 회원리스트 XML*/
	/*@RequestMapping(value = "/user/userList", method = RequestMethod.GET,  headers="Accept=application/xml")	
	public  @ResponseBody UserList getUserListXML() {
		Map<String, Object> param;
		param = new HashMap<String, Object>();
		
		List<User> users= userService.getUserList(param);
		
		UserList userList = new UserList();
		userList.setUsers(users);
		//model.addAttribute("userList",userList);
		
		return userList;
		
	}
	*/
	/*
	 * @RequestMapping(value="state/{abbreviation}", method=RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody State fetchStateJson(@PathVariable String abbreviation) {	
		logger.info("fetching JSON state");
		return getStateByAbbreviation(abbreviation);
	}
	 */
	/*@RequestMapping(value = "/user/user", method = RequestMethod.GET)
	public @ResponseBody User getUser(Model model) {
		Map<String, Object> param;
		param = new HashMap<String, Object>();
		
		User user= userService.getUser("faye12005@gmail.com");				
		//model.addAttribute("user",user);	
		return user;
		
	}
	*/
	/*뷰리졸브 확장자로 하는방법*/
	@RequestMapping(value = "/user/user", method = RequestMethod.GET)
	public ModelAndView getUser(Model model) {
		Map<String, Object> param;
		param = new HashMap<String, Object>();
		
		User user= userService.getUser("faye12005@gmail.com");				
		ModelAndView mav = new ModelAndView();
	    mav.addObject("user", user);
	    return mav;
		
		
	}
	
	@RequestMapping(value = "/user/userList", method = RequestMethod.GET, headers="Accept=application/json")
	public  @ResponseBody  List<User> getUserListJSON() {
		Map<String, Object> param;
		param = new HashMap<String, Object>();
		
		List<User> userList= userService.getUserList(param);
		
		
		//model.addAttribute("userList",userList);
		
		
	    return userList;
		
	}
	@RequestMapping(value = "/user/JqUserList", method = RequestMethod.GET)
	public  @ResponseBody  JqUser JQuserList(@RequestParam (value = "page", required = false, defaultValue = "1") String page,
											 @RequestParam (value = "rows", required = false, defaultValue = "10") String rows,
											 @RequestParam (value = "sidx", required = false, defaultValue = "name") String sidx,
											 @RequestParam (value = "sord", required = false, defaultValue = "desc") String sord
											 ) {
		//Map<String, Object> param;
		//param = new HashMap<String, Object>();
		logger.info("page:{} rows:{} ",page,rows);
		logger.info(" sidx:{} sord:{}" ,sidx,sord);
		
		
		PagingManager pagingManaer = new PagingManager();		
		Map<String, Object> param  = pagingManaer.createMysqlLimit(page, rows);
		
		List<User> userList= userService.getUserList(param);
		pagingManaer.setTotalCount(commonService.getFoundRows());
		
		JqUser jquser = new JqUser();		
		jquser.setPage(page);
		jquser.setTotal(pagingManaer.getTotalPage(rows));
		jquser.setRecords(pagingManaer.getRecords());
		jquser.setRows(userList);
		//model.addAttribute("userList",userList);
		
		
	    return jquser;
		
	}
	@RequestMapping(value = "/user/manager", method = RequestMethod.GET)
	public String userManager() {
	

		
		return "user/manager";
	}
	
}