package net.itsplace.admin.controller;

import net.itsplace.admin.service.AdminPlaceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminPlaceController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceController.class);
	@Autowired
	private AdminPlaceService adminPlaceService;
}
