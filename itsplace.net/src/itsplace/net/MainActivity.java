package itsplace.net;


import itsplace.net.common.ActionBarMenu;

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


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import org.springframework.web.client.HttpClientErrorException;

import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


public class MainActivity extends SherlockActivity{
	protected static final String TAG = MainActivity.class.getSimpleName();
	private ActionBarMenu actionBarMenu;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		actionBarMenu.createMenu(menu);

		
	       
	    return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      
    	return actionBarMenu.selectMenuEvent(getSherlock(),this, item);
    }
   
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBarMenu = new ActionBarMenu();
	}
	
  
}
//      
//        	 
//        	Intent intent = new Intent(this, DemoActivity2.class);
//        	this.addTab("PBook", R.drawable.icon, intent);//나의스탬프
//        	 
//        	intent = new Intent(this, SignUpActivity.class);
//        	this.addTab("Places", R.drawable.ic_search, intent);//내주변 가맹점
//        	
//        	intent = new Intent(this, StampActivity.class);
//        	this.addTab("Stamp", R.drawable.rss, intent);//Qrcode 찍으면 메뉴 -> 스탬프 받는 방법
//        	
//        	intent = new Intent(this, FranchiserActivity.class);
//        	this.addTab("Event", R.drawable.calendar, intent);//알림 이벤트 등
//        	
//        	intent = new Intent(this, DaumMapActivity.class);
//        	this.addTab("MapActivity", R.drawable.star, intent);
//        	
        	/*
        	 * This adds a title, and an image to the tab bar button
        	 * Image should be a PNG file with transparent background.
        	 * By default, opaque areas are shaded gray in off state, and yellow in on state 
        	 */
        
        	
    //    }
        
        /*
         * commit is required to redraw the bar after add tabs are added
         * if you know of a better way, drop me your suggestion please.
         */
       // commit();
    
 
 
