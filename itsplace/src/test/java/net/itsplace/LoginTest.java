package net.itsplace;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;







public class LoginTest {
	private static final String BASE_URL = "http://localhost:8080/MyPlace";  
	private static final Logger logger =  LoggerFactory.getLogger(LoginTest.class);  


	@Test
	public void getUser() {
		ArrayList hoonList = new ArrayList();
		
		Hoon h = createHoon("ㅇ22","ㅂㅂㅂ");
		hoonList.add(h);
		h = createHoon("11","ㅂㅂㅂ");
		hoonList.add(h);
		
		strToJson(hoonList);
	}
	public static String strToJson(List<?> list) {
	
		System.out.println(list.size());
		System.out.println(list.get(0).getClass().toString());
		
		return "";
	}

	public  Hoon createHoon(String id, String name){
		Hoon hoon = new Hoon();
		hoon.setId(id);
		hoon.setName(name);
		return hoon;
		
	}
	 public class Hoon{
		String id ="11";
		String name = "22";
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}

}
