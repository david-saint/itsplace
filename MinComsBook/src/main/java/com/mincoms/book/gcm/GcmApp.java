package com.mincoms.book.gcm;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;


public class GcmApp {

 static String gcmURL = "https://android.googleapis.com/gcm/send";
 static String gcmRegID = "APA91bGqfoV52YG0prnAxjfUO_RSgtGFYNB3SVWhWN--G72P9GJHjorr8NyjcTxYpeW7sMGhwSx1SOV9cd2IdAFNABBVomyvp6t2710e_Ic1Ez65IBSuJxLNb8PB-E570y6_fzzh1R5c";
 static String gcmAuthToken = "AIzaSyBpTW07vNhjvRuDgsg3qZvdNQW89aOYgac";
 
 public static void main(String[] args){
  
  try {
   sender (gcmRegID,
     gcmAuthToken,
     "테스트 MSG"
     );
  } catch (Exception e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 
 public static void sender(String regId, String authToken, String msg) throws Exception { 
        StringBuffer postDataBuilder = new StringBuffer();
        postDataBuilder.append("registration_id=" + regId); // 등록ID 
        postDataBuilder.append("&collapse_key=1"); 
        postDataBuilder.append("&delay_while_idle=1"); 
        postDataBuilder.append("&data.message=" + URLEncoder.encode(msg, "UTF-8"));
        
        
        byte[] postData = postDataBuilder.toString().getBytes("UTF8");
        URL url = new URL(gcmURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        HttpsURLConnection.setDefaultHostnameVerifier(new FakeHostnameVerifier());
        conn.setDoOutput(true); 
        conn.setUseCaches(false); 
        conn.setRequestMethod("POST"); 
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
        conn.setRequestProperty("Authorization", "key=" + gcmAuthToken);
        OutputStream out = conn.getOutputStream(); 
        out.write(postData); 
        out.close();
        conn.getInputStream();
        
        System.out.println("postData : " + postDataBuilder.toString());
 String reponseLine = new BufferedReader(new InputStreamReader(conn.getInputStream())).readLine();
  
  
  System.out.println("responseLine : " + reponseLine);
       }
 
 private static class FakeHostnameVerifier implements HostnameVerifier {
     public boolean verify(String hostname, SSLSession session) {
         return true;
     }
 }
} 