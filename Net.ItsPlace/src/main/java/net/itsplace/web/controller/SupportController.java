package net.itsplace.web.controller;

import net.itsplace.web.service.SupportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SupportController {
	private static final Logger logger = LoggerFactory.getLogger(SupportController.class);
	@Autowired
	private SupportService supportService;
}
