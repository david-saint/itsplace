/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.android.basicauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.android.basicauth.RestClient.RequestMethod;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Roy Clarkson
 */
public class MainActivity extends SherlockActivity {
	protected static final String TAG = MainActivity.class.getSimpleName();
	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_layout);
		  
		// Initiate the JSON POST request when the JSON button is clicked
		final Button buttonJson = (Button) findViewById(R.id.submit);
		Intent intent = new Intent(this ,NextActivity.class);
		intent.putExtra("client", "");
		buttonJson.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//new FetchSecuredResourceTask().execute();
				new StampSaveTask().execute();
				// 
			//	intent.s
//				  intent.putExtra("latitude", f.getLatitude());
//				  startActivity(intent);
//				  
//				  context.startActivity(intent);
			//	startActivity(new Intent(this,MainActivity.class));
			}
		});
		DefaultHttpClient client = HttpClientFactory.getThreadSafeClient();
		
		    HttpPost requestLogin = new HttpPost(
		            "http://10.0.2.2:8080/signin/authenticate?");
		    HttpResponse response = null;
		    List<NameValuePair> param = new ArrayList<NameValuePair>();
		    param.add(new BasicNameValuePair("j_username", "faye12005@gmail.com"));
		    param.add(new BasicNameValuePair("j_password", "hoon1014"));
		    param.add(new BasicNameValuePair("_spring_security_remember_me","true"));
			    try {requestLogin.addHeader("X-Ajax-call", "true");
			        requestLogin.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
			        response = client.execute(requestLogin);
			        String s = response.getFirstHeader("Location").toString();
			        
			        boolean isError = s.contains("error=true");
			        if(isError){
			        	Toast.makeText(this, "로그인실패", Toast.LENGTH_LONG).show();
			        }else{
			        	Toast.makeText(this, "로그인성공", Toast.LENGTH_LONG).show();
			        	
			        	
			        }
			   //     for (org.apache.http.Header h : response.getAllHeaders()) {
			   //         System.out.println(h.getName() + " " + " " + h.getValue() + "");
			   //     }
			    } catch (Exception e){
			    	
			    }
	
	}
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
		 SubMenu sub2 =menu.addSubMenu("tet");
		 sub2.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	        sub2.getItem().setIcon(R.drawable.ic_title_share_default);
	        
	        SubMenu sub = menu.addSubMenu("Theme");
	        sub.add(0, 11111111, 0, "Default");
	        sub.add(0, 2222, 1, "Light");
	        sub.add(0, R.style.Theme_Sherlock_Light_DarkActionBar, 0, "Light (Dark Action Bar---------------)");
	        sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	        sub.getItem().setIcon(R.drawable.ic_title_share_default);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        if (item.getItemId() == android.R.id.home || item.getItemId() == 0) {
	            return false;
	        }
	       // THEME = item.getItemId();
	        Toast.makeText(this, " item.getItemId()"+ item.getItemId()+"----Theme changed to \"" + item.getTitle() + "\"", Toast.LENGTH_SHORT).show();
	        return true;
	    }
	
	// ***************************************
	// Private methods
	// ***************************************
	private void displayResponse(String response) {
		Toast.makeText(this, response, Toast.LENGTH_LONG).show();
	}

	// ***************************************
	// Private classes
	// ***************************************
	private class FetchSecuredResourceTask extends AsyncTask<Void, Void, RestClient> {

		private String username;

		private String password;

		@Override
		protected void onPreExecute() {
			showLoadingProgressDialog();

			// build the message object
			EditText editText = (EditText) findViewById(R.id.username);
			this.username = editText.getText().toString();

			editText = (EditText) findViewById(R.id.password);
			this.password = editText.getText().toString();
	
		}

		@Override
		protected RestClient doInBackground(Void... params) {
			final String url = getString(R.string.base_uri) + "/places";
			Log.d(TAG, url);
			
			RestClient restClient = new RestClient(url);
			try {			
			
				restClient.Execute(RequestMethod.POST);
				
			} catch (Exception e) {
				Log.e(TAG, e.getMessage()+ e.getLocalizedMessage(), e);
				Log.i(TAG, restClient.getErrorMessage(), e);
				
			}
			return restClient;
		}

		@Override
		protected void onPostExecute(RestClient restClient) {
			dismissProgressDialog();
		
		//	String query = jsonResponse.getString("result"); // 전체결과물 
			 
           
           if(restClient.getResponseCode()==200){
        	   try {
   				JSONObject jsonResponse  = restClient.getJsonObject();
   				JSONArray	placesJson = jsonResponse.getJSONArray("result");
   				Gson gson = new Gson();
   		            for (int i = 0; i < placesJson.length(); i++) {
   		                Place place =  gson.fromJson(placesJson.getString(i), Place.class);
   		                Log.i(TAG,place.getFname());
   		            } 
   			} catch (JSONException e) {
   				e.printStackTrace();
   			}
           }else{
        	   
           }
			
           
			displayResponse(restClient.getResponseCode()+"");
			new StampSaveTask().execute();
		}

	}
	// ***************************************
		// Private classes
		// ***************************************
		private class StampSaveTask extends AsyncTask<Void, Void, RestClient> {
			@Override
			protected void onPreExecute() {
				showLoadingProgressDialog();
			}

			@Override
			protected RestClient doInBackground(Void... params) {
				final String url = getString(R.string.base_uri) + "/place/stamp/save";
				RestClient restClient = new RestClient(url);
				
				restClient.AddParam("authcode", "1111");
				restClient.AddParam("stampid", "2");
				restClient.AddParam("email", "faye12005@gmail.com");
				try {			
					restClient.Execute(RequestMethod.POST);
					
				} catch (Exception e) {
					Log.e(TAG, e.getMessage()+ e.getLocalizedMessage(), e);
					Log.i(TAG, restClient.getErrorMessage(), e);
					
				}
				return restClient;
			}

			@Override
			protected void onPostExecute(RestClient restClient) {
				dismissProgressDialog();
			
			//	String query = jsonResponse.getString("result"); // 전체결과물 
				 
	           String result;
	         
	           try {
	   				JSONObject jsonResponse  = restClient.getJsonObject();
	   				result = jsonResponse.getString("result");
	   				if(jsonResponse.getString("status")=="SUCCESS"){
	   					result = jsonResponse.getString("result");
	   					Log.i(TAG,jsonResponse.getString("result"));
	   					Log.i(TAG,jsonResponse.getString("status"));
	   				}else{
	   					Log.i(TAG,jsonResponse.getString("result"));
	   				}
	   			   
	   				
	   			} catch (JSONException e) {
	   				e.printStackTrace();
	   				result = restClient.getErrorMessage();
	   			}
	          
				
	           
				displayResponse(result);
				//new FetchSecuredResourceTask2().execute();
			}

		}
	
		 
		    
	
}
