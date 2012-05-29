package net.itsplace.user;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.itsplace.place.controller.PlaceCommentController;
import net.itsplace.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@RequestParam(value="error", required=false) boolean error,Device device,SitePreference sitePreference, Model model,HttpServletRequest request) {
		
		return "user/register";
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@RequestParam(value="error", required=false) boolean error,Device device,SitePreference sitePreference, Model model,HttpServletRequest request, User p) 
	{
		userService.saveUser(p);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public  @ResponseBody  User login(User user) {
		logger.info("email:{}",user.getEmail());
		return userService.getUser(user.getEmail());
	}
}
