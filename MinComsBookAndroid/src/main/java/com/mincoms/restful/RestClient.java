package com.mincoms.restful;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.apache.http.*;
import org.apache.http.message.*;
import org.apache.http.protocol.HTTP;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class RestClient {
	protected static final String TAG = RestClient.class.getSimpleName();
    public enum RequestMethod {
        GET,
        POST,
        POSTJSON        
    }
    
    private ArrayList <NameValuePair> params;
    private ArrayList <NameValuePair> headers;

    private String url;

    private int responseCode; //응답코드
    private String message;  // 응답메세지 // 에러메세지

    private String response;
    private JSONObject jsonResponse; 
    
    public String getResponse() {
        return response;
    }
  
    @Deprecated
    public JSONObject getJsonObject(){
    	
    	try {
    		jsonResponse = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonResponse = null;
		}
    	return jsonResponse;
    }
    public String getJson(String jsonName){
    	String json = null;
    	try {
    		jsonResponse = new JSONObject(response);
    		json = jsonResponse.get(jsonName).toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
    	return json;
    }
    public String getErrorMessage() {
        return message;
    }

    public int getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(int statusCode) {
        this.responseCode = statusCode;
    }
    public RestClient(String url)
    {
        this.url = url;
        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
    }

    public void AddParam(String name, String value)
    {
        params.add(new BasicNameValuePair(name, value));
    }

    public void AddHeader(String name, String value)
    {
        headers.add(new BasicNameValuePair(name, value));
    }

    public void Execute(RequestMethod method) throws Exception
    {
        switch(method) {
            case GET:
            {
                //add parameters
                String combinedParams = "";
                if(!params.isEmpty()){
                    combinedParams += "?";
                    for(NameValuePair p : params)
                    {
                        String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(),"UTF-8");
                        if(combinedParams.length() > 1)
                        {
                            combinedParams  +=  "&" + paramString;
                        }
                        else
                        {
                            combinedParams += paramString;
                        }
                    }
                }

                HttpGet request = new HttpGet(url + combinedParams);

                //add headers
                request.addHeader("user-agent","android");
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }

                executeRequest(request, url);
                break;
            }
            case POSTJSON:
            {
                HttpPost request = new HttpPost(url);
                request.addHeader("user-agent","android");
                //add headers
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }
                JSONObject jo = new JSONObject();


                if(!params.isEmpty()){
                    for (int i = 0; i < params.size();i++)
                    {
                        jo.put(params.get(i).getName(),params.get(i).getValue());


                    }
                    StringEntity se = new StringEntity(jo.toString());
                    se.setContentType("text/xml"); 
                    se.setContentEncoding( new BasicHeader(HTTP.CONTENT_TYPE, "application/json")); 

                    request.setEntity(se);
                    //request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                }

                executeRequest(request, url);
                break;
            }
            case POST:
            {
                HttpPost request = new HttpPost(url);
                request.addHeader("user-agent","android");
                //add headers
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }
               
                if(!params.isEmpty()){
                	UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");                	
                    request.setEntity(entity);
                }
              //  Log.i(TAG,"combinedParams-----------------------"+combinedParams);
                   // se.setContentType("text/xml"); 
                   //se.setContentEncoding( new BasicHeader(HTTP.CONTENT_TYPE, "application/json")); 

             
                    //request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                

                executeRequest(request, url);
                break;
            }
        }
    }

    private void executeRequest(HttpUriRequest request, String url)
    {
        //HttpClient client = new DefaultHttpClient();
        HttpClient client = HttpClientFactory.getThreadSafeClient();

        HttpResponse httpResponse;

        try {
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();
            
            HttpEntity entity = httpResponse.getEntity();
            	
            if (entity != null) {

                InputStream instream = entity.getContent();
                response = convertStreamToString(instream);

                // Closing the input stream will trigger connection release
                instream.close();
            }

        } catch (ClientProtocolException e)  {
           // client.getConnectionManager().shutdown();
            e.printStackTrace();
        } catch (IOException e) {
           // client.getConnectionManager().shutdown();
            e.printStackTrace();
        }
    }

 
    private static String convertStreamToString(InputStream is) {

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