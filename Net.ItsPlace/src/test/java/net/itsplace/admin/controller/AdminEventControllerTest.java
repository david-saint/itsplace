package net.itsplace.admin.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.itsplace.admin.service.AdminEventService;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.init.TestApplicationContext;


import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;





public class AdminEventControllerTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(AdminEventControllerTest.class);
	
	@Inject
	private ApplicationContext applicationContext;

	@Autowired
	AdminEventService service;
	
	@Autowired
	AdminEventController controller;
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	

	private HandlerAdapter handlerAdapter;
	private RestTemplate restTemplate;
	@Before
    public void setup() throws Exception {
		request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	    handlerAdapter =  new AnnotationMethodHandlerAdapter();
	    restTemplate = new RestTemplate();
	}	

	@Test
	public void testAdd() throws Exception {
		request.setMethod("GET");
		request.setParameter("fid","8888881");
		request.setRequestURI("/admin/place/event/add");
	    handlerAdapter.handle(request, response, controller); 
		// final ModelAndView mav = handlerAdapter.handle(request, response,  controller);
		// String result =response.;
		 //logger.info("result{}",result);
	
	}

	@Test
	public void testAddSubmit() throws Exception {
		request.setMethod("POST");
		PlaceEvent p = new PlaceEvent();
		p.setTitle("fffffffff");
		request.setParameter("title","dddddddd");
		request.setRequestURI("/admin/place/event/add");
	    handlerAdapter.handle(request, response, controller); 
	}

}
