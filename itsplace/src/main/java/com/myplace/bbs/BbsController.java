package com.myplace.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myplace.bbs.Bbs;
import com.myplace.util.Encrypt;

@Controller
public class BbsController {

	private static final Logger logger = Logger.getLogger(BbsController.class);
	@Autowired
	private BbsService bbsService;
	
	
	
	/* 회원가입 폼 페이지 */
	@RequestMapping(value = "/bbs/list", method = RequestMethod.GET)
 	public String bbsList(Map model) {
		Bbs bbs = new Bbs();
		model.put("bbs",bbs);
		logger.debug("게시판 리스트 페이지");		
		
		return "bbs/list";
	}
}
