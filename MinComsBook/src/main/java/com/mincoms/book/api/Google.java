package com.mincoms.book.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.http.HttpClient;

public class Google {
	private static final Logger logger = LoggerFactory.getLogger(Google.class);
	
	public static BookInfo GetBookInfo(String isbn) throws URISyntaxException, ParseException, Exception{
		DefaultHttpClient client = new DefaultHttpClient();
		String url = "https://www.googleapis.com/books/v1/volumes?q="+isbn;
		BookInfo book = new BookInfo();
	    try {
	    	  HttpGet httpGet = new HttpGet();
			  httpGet.setURI( new URI(url));
			  HttpResponse httpResponse = client.execute(httpGet);
			  int status = httpResponse.getStatusLine().getStatusCode();
			  if(status == 200){
				  HttpEntity entity = httpResponse.getEntity();
				
		          if (entity != null) {
		                InputStream instream = entity.getContent();
		                String response = convertStreamToString(instream);
		            
		              
		                System.out.println(response);			               
		               // System.out.println(    new String( response.getBytes("utf-8"), "utf-8" ) );			               
		                instream.close();
		                	
		                JSONParser parser = new JSONParser();
		                JSONObject json = (JSONObject) parser.parse(response);
		                System.out.println("파싱");
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
		                //System.out.println("thumbnail:"+imageLink.get("thumbnail"));
		                //System.out.println("smallThumbnail:"+imageLink.get("smallThumbnail"));
		                
		               // System.out.println("publishedDate:"+volumeInfo.get("publishedDate"));
		                if(imageLink != null){
		                	book.setThumbnail(imageLink.get("thumbnail").toString());
		                }
		              
		              
		               book.setTitle(volumeInfo.get("title").toString());
		               book.setAuthors(authorList);
		               book.setPublishedDate(volumeInfo.get("publishedDate").toString());
		               
		               book.setIsbn(isbn);
		               
		            }else{
		              book = null;
		            }
			  }else{
				  book = null;	  
		      }
		    } catch (UnsupportedEncodingException e) {
		       logger.debug("{}", e.getMessage());
		    } catch (ClientProtocolException e) {
		    	logger.debug("{}", e.getMessage());
		    } catch (IOException e) {
		    	logger.debug("{}", e.getMessage());
		    }

	   
	    return book;
    }
	
	private static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {

       BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
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
