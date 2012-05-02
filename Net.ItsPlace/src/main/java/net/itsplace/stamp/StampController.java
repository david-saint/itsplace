package net.itsplace.stamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itsplace.user.UserService;

@Controller
@RequestMapping("/user")
public class StampController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@RequestParam(value="error", required=false) boolean error,Device device,SitePreference sitePreference, Model model,HttpServletRequest request) {
		
		return "user/register";
	}
}
