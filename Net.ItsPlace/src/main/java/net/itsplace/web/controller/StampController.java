package net.itsplace.web.controller;

import net.itsplace.web.service.StampService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StampController {
	private static final Logger logger = LoggerFactory.getLogger(StampController.class);
	@Autowired
	private StampService stampService;
}
