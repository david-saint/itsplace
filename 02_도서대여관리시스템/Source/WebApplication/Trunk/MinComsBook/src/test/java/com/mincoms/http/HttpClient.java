package com.mincoms.http;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.test.TestApplicationContext;
import com.mincoms.book.repository.*;
public class HttpClient  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
	
	
	@Autowired
	BookRepository bookRepo;
	
	@Test
	public void test() throws URISyntaxException, ParseException{
		 DefaultHttpClient client = new DefaultHttpClient();
		 String url = "http://www.googleapis.com/books/v1/volumes/9788979144086";
		  Object test = null;
		  try{
			  
		  }catch(Exception e){
			  
		  }
		  
		  
			    try {
			    	  HttpGet httpGet = new HttpGet();
					  httpGet.setURI( new URI("https://www.googleapis.com/books/v1/volumes?q=9788979144086"));
					  HttpResponse httpResponse = client.execute(httpGet);
					  int status = httpResponse.getStatusLine().getStatusCode();
					  System.out.println("status:"+status);
					  HttpEntity entity = httpResponse.getEntity();

			            if (entity != null) {

			                InputStream instream = entity.getContent();
			                String response = convertStreamToString(instream);
			                System.out.println(response);	
			                System.out.println(    new String( response.getBytes("utf-8"), "utf-8" ) );			        
			                instream.close();
			                
			                JSONParser parser = new JSONParser();
			                JSONObject json = (JSONObject) parser.parse(response);
			                String kind = (String) json.get("kind");
			                System.out.println(kind);
			                
			                JSONArray itemsArray = (JSONArray) json.get("items");
			                //JSONArray msg2 = (JSONArray)msg.get(0);
			                //System.out.println(msg2.get(0));
			                System.out.println(itemsArray.toJSONString());
			              
			                JSONObject obj = (JSONObject) itemsArray.get(0);
			                System.out.println(obj.get("saleInfo"));
			                
			                System.out.println(obj.get("volumeInfo"));
			                
			                JSONObject volumeInfo = (JSONObject) obj.get("volumeInfo");
			                System.out.println("page:"+volumeInfo.get("pageCount"));
			                
			                System.out.println(volumeInfo.get("authors"));
			                
			                String authorList = "";
			                JSONArray authors = (JSONArray) volumeInfo.get("authors");
			                System.out.println("작가:"+authors.get(0));
			                
			                if(authors.size()>0){
			                	for(int i=0; i<authors.size();i++){
			                		if(i>0){
			                			authorList += "," + authors.get(i).toString();
			                		}else{
			                			authorList = authors.get(0).toString();
			                		}
				        		}	
			                }
			                System.out.println("작가리스트:"+authorList);
			                /*Iterator<String> iterator = authors.iterator();
			        		while (iterator.hasNext()) {			        			
			        			System.out.println("authors:"+iterator.next());
			        		}*/
			                System.out.println("title:"+volumeInfo.get("title"));
			                System.out.println("description:"+volumeInfo.get("description"));
			                JSONObject imageLink = (JSONObject)(volumeInfo.get("imageLinks"));
			                System.out.println("thumbnail:"+imageLink.get("thumbnail"));
			                System.out.println("smallThumbnail:"+imageLink.get("smallThumbnail"));
			                
			                System.out.println("publishedDate:"+volumeInfo.get("publishedDate"));
			                
			              
			               BookInfo book = new BookInfo();
			               book.setTitle(volumeInfo.get("title").toString());
			               book.setAuthors(authorList);
			               book.setPublishedDate(volumeInfo.get("publishedDate").toString());
			               book.setThumbnail(imageLink.get("thumbnail").toString());
			               book.setIsbn("1");
			               bookRepo.save(book);
			            }
					   // ResponseHandler<String> responseHandler = new BasicResponseHandler();
					    //String responseBody  = client.execute(httpGet, responseHandler);
					    //System.out.println("r:"+ responseBody);
			      
			    } catch (UnsupportedEncodingException e) {
			        e.printStackTrace();
			    } catch (ClientProtocolException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
	}
	
	private  String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
