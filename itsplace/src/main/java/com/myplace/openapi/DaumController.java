package com.myplace.openapi;
import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myplace.main.IndexController;
import com.myplace.user.User;
import com.myplace.user.UserController;
import com.myplace.user.UserService;

@Controller
public class DaumController {
	private static final Logger logger =  LoggerFactory.getLogger(DaumController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 다음 지도 오픈 api 테스트
	 * @author 김동훈
	 * @version 1.0, 2011.8.19 
	 * @param 
	 * @return  다음지도
	 * @throws 
	 * @see 
	 */
    @RequestMapping(value = "/openapi/map", method = RequestMethod.GET)
    public String getDaumMap() {
    	logger.info("다음맵 호출");
    	return "openapi/daummap";
	}
    
    @RequestMapping(value = "/openapi/address", method = RequestMethod.GET)
	public String getAddressPage() {
	
		return "openapi/address";
		
	}

}
