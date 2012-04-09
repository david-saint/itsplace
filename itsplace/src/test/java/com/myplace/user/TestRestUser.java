package com.myplace.user;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.myplace.partner.franchiser.FranchiserMember;
  
public class TestRestUser {

	private static final String BASE_URL = "http://localhost:8080/MyPlace";  
	private static final Logger logger =  LoggerFactory.getLogger(TestRestUser.class);  


	
	private RestTemplate restTemplate;
	
	@Before
	public void setUp() throws Exception {
		restTemplate = new RestTemplate();
	}

	public void test111(){

	    DefaultHttpClient client = new DefaultHttpClient();
	    HttpPost requestLogin = new HttpPost(
	            "http://localhost:8080/MyPlace/signin/authenticate?");
	    HttpResponse response = null;
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("j_username", "faye12005@gmail.com"));
	   params.add(new BasicNameValuePair("j_password", "hoon1014"));
	    params.add(new BasicNameValuePair("_spring_security_remember_me","true"));
		    try {
		        requestLogin.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		        response = client.execute(requestLogin);
		        String s = response.getFirstHeader("Location").toString();
		        
		        boolean isError = s.contains("error=true");
		        if(isError){
		        	System.out.println("로그인 에러Location:"+s);
		        }else{
		        	System.out.println("로그인 성공Location:"+s);
		        }
		        for (org.apache.http.Header h : response.getAllHeaders()) {
		            System.out.println(h.getName() + " " + " " + h.getValue() + "");
		        }
		    } catch (UnsupportedEncodingException e) {
		       // e.printStackTrace();
		    } catch (ClientProtocolException e) {
		       // e.printStackTrace();
		    } catch (IOException e) {
		       // e.printStackTrace();
		    }
		   
	}

	public void testUser(){
		//rest style로 했을경우 소숫점이 잘린다 이유는  http://please.noroutine.me/2011/01/rest-with-spring-strict-resource-urls.html
		 final String url =BASE_URL+ "/user/isUserEmail?email={email}";
         HttpHeaders requestHeaders = new HttpHeaders();
         List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
         acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
         requestHeaders.setAccept(acceptableMediaTypes);

         HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
     
         ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, "faye12005@gmail.com");
         logger.info(responseEntity.getBody());
         assertEquals(responseEntity.getBody(), "true");
       
        
       
	}

	public void testRegisterUser(){

		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		//requestHeaders.setAccept(acceptableMediaTypes);
		
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		
		restTemplate = new RestTemplate();
		
		 final String url =BASE_URL+ "/user/newAccountJson";
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("email", "faye120dg06@gmail.com");
	//	mvm.add("password", "faye");
		mvm.add("name", "faye");
		String result = restTemplate.postForObject(url ,mvm, String.class, requestEntity);
		logger.info(result);
		logger.info("post={}","ttttt");
        
       
	}
	
	public void testRegisterUser2(){
		   
       	HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		requestHeaders.setAccept(acceptableMediaTypes);
		   requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		User user = new User();
		user.setEmail("faye120044@gmail.com");
		
		HttpEntity<User> requestEntity = new HttpEntity<User>(user, requestHeaders);
         
		
		restTemplate = new RestTemplate();
		
		 final String url =BASE_URL+ "/user/newAccountJson";
	
		 ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
       
		logger.info(response.getBody());
		logger.info("post={}","ttttt");
	}
	

	public void testFranchiser(){
		  final String url ="http://localhost:8080/MyPlace/partner/franchiserListJson/web/다빈치/1/10/10";
          
         
          
          // Set the Accept header for "application/json"
		   HttpHeaders requestHeaders = new HttpHeaders();
           List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
           acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
           requestHeaders.setAccept(acceptableMediaTypes);

           // Populate the headers in an HttpEntity object to use for the
           // request
           HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

           // Create a new RestTemplate instance
           RestTemplate restTemplate = new RestTemplate();

          // Perform the HTTP GET request
         // try{
        	  ResponseEntity<FranchiserMember[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, FranchiserMember[].class);
        	  FranchiserMember f[] = responseEntity.getBody();
              for(int i=0;i<f.length;i++){
      			 logger.info("사용자:" +f[i].getName());
      			logger.info("사용자:" +f[i].getAddress().getDoroname());
      		  }
         // }catch(Exception e){
        //	  logger.info(e.getMessage());
        //  }
          

         
	}
	
	@Test
	public void getUser() {
		 //RestTemplate restTemplate = new RestTemplate();
//	      Object user = restTemplate.getForObject("http://localhost:8080/r/users/{username}", Object.class, "test_username");
		//User user = restTemplate.getForObject(BASE_URL + "/user/user.json", User.class);
		//User user = restTemplate.postForObject(BASE_URL + "/user/user.json", User.class);
		
		//logger.info("ddddddddddddddd" + user.to);
		// Set a custom MappingJacksonHttpMessageConverter that supports the text/javascript media type
		
		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(acceptableMediaTypes);

		// Populate the headers in an HttpEntity object to use for the request
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

		// Perform the HTTP GET request
		//String url="http://localhost:8080/spring-android-showcase-server/states";
		//User user = restTemplate.getForObject("http://localhost:8080/MyPlace/m/login?email=faye12005@gmail.com&password=hoon1014", User.class);
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("email", "faye12005@gmail.com");
		mvm.add("password", "hoon1014");
		User user = restTemplate.postForObject("http://192.168.123.186:8080/MyPlace/m/login",mvm ,User.class);
		logger.info("post:{}",user.getEmail());
		//ResponseEntity<State[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, State[].class);
		//ResponseEntity<User[]> responseEntity = restTemplate.exchange(BASE_URL + "/user/user.json", HttpMethod.GET, requestEntity, User[].class);
		
		//User user[] = responseEntity.getBody();
		
		//for(Integer i=0;i<user.length;i++){
		//	 logger.info("사용자:" +user[i].getName());
		//}
		//User user = (User)restTemplate.getForObject(BASE_URL + "/user/userList.json", UserList.class);
		
	//	 User user = responseEntity.getBody();
	//	 logger.info("싱글사용자:"+user.getName());
		 //logger.info(user.getPassword());
		
		// State state[] = responseEntity.getBody();
		// for(Integer i=0;i<state.length;i++){
		//	 logger.info(state[i].getName());
		// }
		 
		
		
		
	    
	}
	
	public void getTTTT() {
		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(acceptableMediaTypes);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(BASE_URL + "/partner/flocation/lat/35.869528291402524/lot/128.605347126656334/", HttpMethod.GET, null, String.class);
		String s = responseEntity.getBody().toString();
		
		logger.info(s.replace("\"", ""));
		logger.info(s);
		

	}
	
		public void getUserList() {
	
		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(acceptableMediaTypes);

		// Populate the headers in an HttpEntity object to use for the request
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJacksonHttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		// Perform the HTTP GET request
		ResponseEntity<User[]> responseEntity = restTemplate.exchange(BASE_URL + "/user/userList", HttpMethod.GET, null, User[].class);

		// convert the array to a list and return it
		//return Arrays.asList(responseEntity.getBody());
	
		User user[] = responseEntity.getBody();
				
		for(Integer i=0;i<user.length;i++){
			 logger.info("사용자리스트:" +user[i].getName());
		}
	}
		
		public void getUserListXML() {
			
			
			HttpHeaders requestHeaders = new HttpHeaders();
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(acceptableMediaTypes);

			// Populate the headers in an HttpEntity object to use for the request
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			
			// Perform the HTTP GET request
			String url="http://localhost:8090/spring-android-showcase-server/states";
			//ResponseEntity<State[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, State[].class);
			ResponseEntity<UserList> responseEntity = restTemplate.exchange(BASE_URL + "/user/userList", HttpMethod.GET, requestEntity, UserList.class);
			
			UserList userList = responseEntity.getBody();
			
			for(Integer i=0;i<userList.getUsers().size();i++){
				logger.info("사용자리스트xml:" +userList.getUsers().get(i).getName());
			}
			
		
		
		
		
	}
	private  RestTemplate getRestTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		//marshaller.setClassesToBeBound(Device.class, DeviceCollection.class);
		
		MarshallingHttpMessageConverter marshallingHttpConverter = new MarshallingHttpMessageConverter(marshaller);
		marshallingHttpConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML));
		restTemplate.getMessageConverters().add(marshallingHttpConverter);
		
		MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		restTemplate.getMessageConverters().add(converter);
		
		return restTemplate;
				
	}	
}
