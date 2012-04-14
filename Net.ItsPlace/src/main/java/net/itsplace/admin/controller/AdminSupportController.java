package net.itsplace.admin.controller;

import net.itsplace.admin.service.AdminSupportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminSupportController {
	private static final Logger logger = LoggerFactory.getLogger(AdminSupportController.class);
	@Autowired
	private AdminSupportService adminSupportService;
}
