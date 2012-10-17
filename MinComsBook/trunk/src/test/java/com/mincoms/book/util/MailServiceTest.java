package com.mincoms.book.util;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.test.TestApplicationContext;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class MailServiceTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(MailServiceTest.class);
	@Autowired
    private VelocityEngine velocityEngine;
	@Autowired
	MailService mailService;
	
	@Test
	public void test() {
		
		mailService.sendMail("faye12005@gmail.com",  "faye12005@gmail.com", "aaaa","아우우웅아", "김동훈");
	}
	
}
