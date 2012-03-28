package com.itsplace.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import org.junit.Test;

public class TestStringUtil {

	
	public void  testEmail(){
		StringTokenizer st1 = new StringTokenizer("dddd@email.com","@");
		System.out.println(st1.nextToken());
	}
	@Test
	public void testu() throws IOException{
		URL url;
		URL url2;
		HttpURLConnection connection;
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		
		try {
			url = new URL("http://www.gobizmail.com/login.do?id=kayakiller&userid=kayakiller&domain=sungwon-it.com&pass=qpdlqlrlf&returnPath=http://www.gobizmail.com/welcome.do");
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setDoInput(true);
			url = new URL("http://www.gobizmail.com/listMessages.do?folder=Inbox");
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setDoInput(true);
			 is = connection.getInputStream();
		       br = new BufferedReader( new InputStreamReader( is ), 8192 );
		    String retVal = null;
		      char recv[] = new char[ 8192 ];
		      while( br.read( recv ) > 0 )
		         retVal += new String( recv );
		      br.close();
		      is.close();
		      
		     
		      if(retVal.matches("(?i).*kaya.*")){
		    	  System.out.println("있다");  
		      }else{
		    	  System.out.println(retVal);  
		      }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
