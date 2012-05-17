package net.itsplace.admin.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.itsplace.admin.service.AdminEventService;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.user.User;


import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
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
import org.springframework.web.bind.annotation.RequestParam;
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

	@TestedOn(ints = { 0 })
	public void testAdd() throws Exception {
		request.setMethod("GET");
		request.setParameter("fid","42");
		request.setRequestURI("/admin/place/event/add");
	    handlerAdapter.handle(request, response, controller); 
		// final ModelAndView mav = handlerAdapter.handle(request, response,  controller);
		// String result =response.;
		 //logger.info("result{}",result);
	
	}

	@TestedOn(ints = { 0 })
	public void testAddSubmit() throws Exception {
		request.setMethod("POST");
		PlaceEvent p = new PlaceEvent();
		p.setTitle("fffffffff");
		request.setParameter("title","dddddddd");
		request.setRequestURI("/admin/place/event/add");
	    handlerAdapter.handle(request, response, controller); 
	}
	@Test
	public void testGetPlaceEvent() throws Exception {
	//	request.setMethod("GET");
		// request.setContentType("application/json");
	//	request.setParameter("iDisplayLength","10");
	//    request.addParameter("iSortCol_0","1");
	//    request.addParameter("sSortDir_0","DESC");
	//	request.addParameter("iDisplayStart","1");
	//	request.addParameter("sSearch","");
	//	request.setRequestURI("/admin/place/event/getPlaceEventList");
		
	//	response.setContentType("application/json");
	    //handlerAdapter.handle(request, response, controller);
		
		
		
		 DataTable<PlaceEvent> dd =	controller.getPlaceEventList( 1, 10, 1, "desc", "");
		/// for(int i=0; i<dd.getiTotalRecords();i++){
			
			 List<PlaceEvent> ee = (List<PlaceEvent>) dd.getRows();
			 for(int i=0; i<ee.size();i++){
				 logger.info(i+""+ee.get(i).getEid()); 
			 }
			 
		// }
		
	  //  String result =response.getContentAsString();
	  //  logger.info("RESULT : "+result);
///		 HttpHeaders requestHeaders = new HttpHeaders();
//         List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
//         acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
//         requestHeaders.setAccept(acceptableMediaTypes);
//
//      
//         HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
//         String url ="http://localhost:8080/admin/place/event/getPlaceEventList?iDisplayStart=1&iDisplayLength=2&iSortCol_0=4&sSortDir_0=4";
//     
//        // Perform the HTTP GET request
//       // try{DataTable<User>
//      	  ResponseEntity<DataTable[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, DataTable[].class);
//      	DataTable f[] = responseEntity.getBody();
//            for(int i=0;i<f.length;i++){
//    			 logger.info("사용자:" +f[i].getRows().size());
//    		
//    		  }
	}

}
