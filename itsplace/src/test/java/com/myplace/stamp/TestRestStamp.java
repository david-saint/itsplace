package com.myplace.stamp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



import com.myplace.partner.franchiser.stamp.Stamp;
import com.myplace.partner.franchiser.stamp.StampJson;
import com.myplace.user.TestRestUser;

public class TestRestStamp {


	private static final String BASE_URL = "http://localhost:8080/MyPlace";  
	private static final Logger logger =  LoggerFactory.getLogger(TestRestStamp.class);  


	
	private RestTemplate restTemplate;
	
	@Before
	public void setUp() throws Exception {
		restTemplate = new RestTemplate();
	}  
	@Test
	public void saveStamp(){
		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		//requestHeaders.setAccept(acceptableMediaTypes);
		
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		
		restTemplate = new RestTemplate();
		
		final String url =BASE_URL+ "/stamp/saveStampJson";
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		//INSERT INTO PSTAMP(STAMPTYPE,  EMAIL, STATUS, REMARK, MOBILE)
        //VALUES (#stamptype#,  #email#,  'P', #remark#, #mobile#)
        
		mvm.add("email", "faye120dg06@gmail.com");
		mvm.add("sid", "2");
		mvm.add("remark", "faye");
		mvm.add("mobile", "01065141014");
		//String result = restTemplate.postForObject(url ,mvm, String.class);
		String result = restTemplate.postForObject(url ,mvm, String.class, requestEntity);
		logger.info(result);
		
	}
	
	public void test111(){
		 String url ="http://localhost:8080/MyPlace/stamp/myStampJson?email=faye12005@gmail.com";
		
		 String  url2 = "http://localhost:8080/spring-android-showcase-server/statesj";
           
		 String  url3 = "http://localhost:8080/MyPlace/statesj";
         
           
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
              ResponseEntity<Stamp[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Stamp[].class);

           Stamp s[] = responseEntity.getBody();
           for(int i=0; i<s.length ;i++){
        	   logger.info("i"+s[i].getEmail());
           }
     /*
          ResponseEntity<State[]> responseEntity22 = restTemplate.exchange(url3, HttpMethod.GET, requestEntity, State[].class);

           State s2[] = responseEntity22.getBody();
           for(int i=0; i<s2.length ;i++){
        	logger.info("i"+s2[i].getName());
           }
       */
        // convert the array to a list and return it
       
       // return Arrays.asList(responseEntity.getBody());
	}
}
/* 시큐리티 권한이 없어서 생기는 문제 1번
org.springframework.http.converter.HttpMessageNotReadableException: Could not read JSON: Can not deserialize instance of com.myplace.partner.franchiser.stamp.Stamp[] out of START_OBJECT token
at [Source: sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@9e37d7; line: 1, column: 1]; nested exception is org.codehaus.jackson.map.JsonMappingException: Can not deserialize instance of com.myplace.partner.franchiser.stamp.Stamp[] out of START_OBJECT token
at [Source: sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@9e37d7; line: 1, column: 1]
	at org.springframework.http.converter.json.MappingJacksonHttpMessageConverter.readInternal(MappingJacksonHttpMessageConverter.java:127)
	at org.springframework.http.converter.AbstractHttpMessageConverter.read(AbstractHttpMessageConverter.java:153)
	at org.springframework.web.client.HttpMessageConverterExtractor.extractData(HttpMessageConverterExtractor.java:81)
	at org.springframework.web.client.RestTemplate$ResponseEntityResponseExtractor.extractData(RestTemplate.java:619)
	at org.springframework.web.client.RestTemplate$ResponseEntityResponseExtractor.extractData(RestTemplate.java:1)
	at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:446)
	at org.springframework.web.client.RestTemplate.execute(RestTemplate.java:401)
	at org.springframework.web.client.RestTemplate.exchange(RestTemplate.java:377)
	at com.myplace.stamp.TestRestStamp.test111(TestRestStamp.java:69)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:601)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:44)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:41)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:28)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:76)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:193)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:52)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:191)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:42)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:184)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:236)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)
Caused by: org.codehaus.jackson.map.JsonMappingException: Can not deserialize instance of com.myplace.partner.franchiser.stamp.Stamp[] out of START_OBJECT token
at [Source: sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@9e37d7; line: 1, column: 1]
	at org.codehaus.jackson.map.JsonMappingException.from(JsonMappingException.java:163)
	at org.codehaus.jackson.map.deser.StdDeserializationContext.mappingException(StdDeserializationContext.java:198)
	at org.codehaus.jackson.map.deser.ArrayDeserializer.handleNonArray(ArrayDeserializer.java:177)
	at org.codehaus.jackson.map.deser.ArrayDeserializer.deserialize(ArrayDeserializer.java:97)
	at org.codehaus.jackson.map.deser.ArrayDeserializer.deserialize(ArrayDeserializer.java:18)
	at org.codehaus.jackson.map.ObjectMapper._readMapAndClose(ObjectMapper.java:2395)
	at org.codehaus.jackson.map.ObjectMapper.readValue(ObjectMapper.java:1655)
	at org.springframework.http.converter.json.MappingJacksonHttpMessageConverter.readInternal(MappingJacksonHttpMessageConverter.java:124)
	... 31 more

*/
