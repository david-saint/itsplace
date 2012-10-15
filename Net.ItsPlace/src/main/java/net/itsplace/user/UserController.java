package net.itsplace.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



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
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup( Model model,HttpServletRequest request) {
		model.addAttribute("user",new User());
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
