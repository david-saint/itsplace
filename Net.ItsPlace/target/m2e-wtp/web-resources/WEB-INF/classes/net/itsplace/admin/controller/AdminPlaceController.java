package net.itsplace.admin.controller;

import java.util.Locale;

import net.itsplace.admin.service.AdminPlaceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/place")
public class AdminPlaceController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceController.class);
	@Autowired
	private AdminPlaceService adminPlaceService;
	
	/**
	 * 가맹점 승인관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public String place(Locale locale, Model model) {
		return "admin/place/request";
	}
	
	/**
	 * 가맹점 삭제/등록
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		return "admin/place/list";
	}
	
	/**
	 * 가맹점별 회원관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String userList(Locale locale, Model model) {
		return "admin/place/user/list";
	}
	
	/**
	 * 가맹점 이벤트관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/event", method = RequestMethod.GET)
	public String event(Locale locale, Model model) {
		return "admin/place/user/event";
	}
}
