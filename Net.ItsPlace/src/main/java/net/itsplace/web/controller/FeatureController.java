package net.itsplace.web.controller;

import net.itsplace.web.service.FeatureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FeatureController {
	private static final Logger logger = LoggerFactory.getLogger(FeatureController.class);
	@Autowired
	private FeatureService featureService;
}
