package net.itsplace.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.itsplace.user.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
}
