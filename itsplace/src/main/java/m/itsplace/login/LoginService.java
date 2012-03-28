/**
 * 
 */
package m.itsplace.login;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;



import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
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

import com.myplace.user.User;
import com.myplace.user.UserService;
import com.myplace.util.Encrypt;
import com.myplace.util.StandardOrMobile;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
public class LoginService {
        
	private static final Logger logger =  LoggerFactory.getLogger(LoginService.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReCaptcha reCaptcha;
	
	

	@RequestMapping(value = "/m/login", method = RequestMethod.GET, headers="Accept=application/json")
	public  @ResponseBody  User login(Model model,@RequestParam String email,@RequestParam String password) {
		Map<String, Object> param;
		param = new HashMap<String, Object>();
		
		logger.info("Mobile Login Email:{} Password:{}",email,password);
		User user = userService.getUser(email);
		
		
		//model.addAttribute("userList",userList);
		
		
	    return user;
		
	}
	@RequestMapping(value = "/m/login", method = RequestMethod.POST, headers="Accept=application/json")
	public  @ResponseBody  User login(User user) {
		
		
		
		 if(springSecurityCheck(user.getEmail(),user.getPassword())){
			 //패스워드가 md5 되어 있어서 시큐리티를 적용했으나
			 //패스워드를  회원가입시 md5 인코딩 하니 로그인 할대도 인코딩해서 비교한 값으로 로그인 해도 될듯 될듯..
			 User dbUser = userService.getUser(user.getEmail());
			 dbUser.setPassword(user.getPassword());
			 logger.info("Mobile Login Email:{} Password:{}",user.getEmail(),user.getPassword()); 
			 return dbUser; 
		 }else{
			 return  new User();
		 }
		
	   
		
	}
	
	public Boolean springSecurityCheck(String email, String password) {

		 DefaultHttpClient client = new DefaultHttpClient();
		    HttpPost requestLogin = new HttpPost(
		            "http://localhost:8080/MyPlace/signin/authenticate?");
		    HttpResponse response = null;
		    List<NameValuePair> params = new ArrayList<NameValuePair>();
		    params.add(new BasicNameValuePair("j_username", email));
		    params.add(new BasicNameValuePair("j_password", password));
		    params.add(new BasicNameValuePair("_spring_security_remember_me","true"));
			    try {
			        requestLogin.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			        response = client.execute(requestLogin);
			        String s = response.getFirstHeader("Location").toString();
			        
			        boolean isError = s.contains("error=true");
			        if(isError){
			        	return false;
			        }else{
			        	System.out.println("로그인 성공Location:"+s);
			        	return true;
			        	
			        }
			   //     for (org.apache.http.Header h : response.getAllHeaders()) {
			   //         System.out.println(h.getName() + " " + " " + h.getValue() + "");
			   //     }
			    } catch (Exception e){
			    	return false;
			    }
			   
	}
}