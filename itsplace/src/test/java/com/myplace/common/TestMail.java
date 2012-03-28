package com.myplace.common;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import com.myplace.TestApplicationContext;
import com.myplace.common.*;
import com.myplace.common.mail.MailService;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.myplace.user.TestRestUser;
import com.myplace.user.UserController;

public class TestMail extends TestApplicationContext {
	  
	private static final Logger logger = Logger.getLogger(TestMail.class);

	@Autowired
	CommonService cService;
	
	@Autowired
	MailService mailService;
	
	@Test
	public void sendMail() {
		mailService.sendMail("event@itsplace.net", "faye12005@gmail.com", "hi", "zzzz");
		
		
	}
}
