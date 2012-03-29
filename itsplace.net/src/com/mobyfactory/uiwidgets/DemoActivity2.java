package com.mobyfactory.uiwidgets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.itsplace.domain.FranchiserMember;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import itsplace.net.FranchiserActivity;
import itsplace.net.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import itsplace.net.R;
import itsplace.net.map.DaumMapActivity;

public class DemoActivity2 extends Activity{
	
	Context context;
	 EditText editText1 ;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity2);
        context = this;
    	
        Button button = (Button)findViewById(R.id.Button01);
        button.setOnClickListener(
            	new OnClickListener()
            	{
    				public void onClick(View v) {
    					Intent intent = new Intent(context, DaumMapActivity.class);
    			        context.startActivity(intent);
    				}	
            	}
         );
        
        Button button2 = (Button)findViewById(R.id.Button02);
         editText1 = (EditText)findViewById(R.id.editText1);
        
        button2.setOnClickListener(
            	new OnClickListener()
            	{
    				public void onClick(View v) {
    					//Intent intent = new Intent(ScrollableTabActivity.ACTION_CHANGE_TAB);
    					//intent.putExtra(ScrollableTabActivity.CURRENT_TAB_INDEX, 0);
    					//sendBroadcast(intent);
    					 new getMoreItems().execute();
    				}	
            	}
         );
        
       
        Toast.makeText(this, "작업이 끝났습니다.", Toast.LENGTH_SHORT);
    }
	
	
	private class getMoreItems extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            // before the network request begins, show a progress indicator          
        }

        @Override
        protected String doInBackground(Void... params) {
			try {
				  
		        HttpHeaders requestHeaders = new HttpHeaders();
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
				//requestHeaders.setAccept(acceptableMediaTypes);
				
				HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
				
				RestTemplate restTemplate = new RestTemplate();
				List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
				messageConverters.add(new FormHttpMessageConverter());
				messageConverters.add(new StringHttpMessageConverter());//한글깨짐현상 때문에 컨버터?
				
				restTemplate.setMessageConverters(messageConverters);
				  String url = "http://10.0.2.2:8080/MyPlace/user/newAccountJson";
				// url="http://10.0.2.2:8080/MyPlace/partner/franchiserListJson/web/동인/1/10/10";
				MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
				mvm.add("email", "faye12006@gmail.com");
				mvm.add("password", "faye");
				mvm.add("name", "faye");
			String result = restTemplate.postForObject(url ,mvm, String.class);
		

			return result;
			 //  ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
               // return responseEntity.getBody();
            } catch (Exception e) {
                Log.e("ffffffffffffffff", e.getMessage(), e);
            }

		
			return null;
		}
		
		// when data set changes, you need to call proper method here.
		// do not call notifyDataSetChanged() in 'doInBackground()'.
		protected void onPostExecute(String result){
			Log.i("ddddddddddddddd",result);
			editText1.setText(result);
		}
	
    }
}

