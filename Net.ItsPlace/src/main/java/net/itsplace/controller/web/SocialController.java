package net.itsplace.controller.web;

import javax.inject.Inject;

import net.itsplace.domain.Social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SocialController {
	private static final Logger logger = LoggerFactory.getLogger(SocialController.class);
	@Inject
	private ConnectionRepository connectionRepository;
	
    @RequestMapping(value = "/social/fbProfile", method = RequestMethod.GET)
	public @ResponseBody Social fbProfile(){
    	Social json = new Social();
    	Connection<Facebook> facebook = connectionRepository.findPrimaryConnection(Facebook.class);
    	if (facebook == null) {
    		json.setStatus("FAIL");
    	}else{
    	//	model.addAttribute("facebook", facebook.getApi().userOperations().getUserProfile());
    		json.setDisplayName(facebook.getDisplayName());
    		json.setImageUrl(facebook.getImageUrl());
    		json.setStatus("SUCCESS");
    		logger.info("getDisplayName:{}",facebook.getDisplayName());
    		logger.info("getImageUrl:{}",facebook.getImageUrl());
    	}
    	
    	return json;
    }
	
}
