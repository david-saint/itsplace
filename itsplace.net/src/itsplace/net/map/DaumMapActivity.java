package itsplace.net.map;

import itsplace.net.R;
import itsplace.net.util.L;

import java.io.IOException;
import java.util.List;

import net.itsplace.domain.PlaceListAdapter;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DaumMapActivity extends  Activity {
    
	protected static final String TAG = DaumMapActivity.class.getSimpleName();
	   
	private WebView webView;
	
	Location  gpsLocation=null;
	Location networkLocation=null;
	LocationManager locationManager;
	String currentLocation="";
	String currentAddress="";
	
	
	private final int ACTIVITY_REQUEST_CODE_QRCODE	= 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	 super.onCreate(savedInstanceState);    	
    	 setContentView(R.layout.webview_map);  
        
    	 webView = (WebView)findViewById(R.id.webView);
        
    	 final String MAP_URL = getString(R.string.map_uri);
         webView.loadUrl(MAP_URL);
         
        
         webView.setHorizontalScrollBarEnabled(false);
         webView.setVerticalScrollBarEnabled(false);
         webView.setVerticalScrollbarOverlay(true);
         webView.setHorizontalScrollbarOverlay(true);
        
         L.i(TAG,  getIntent().getStringExtra("latitude"));

         //터치햇을때 스크롤 살리기
         webView.setWebViewClient(new WebViewClient(){ 
        	 @Override
        	 public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		 if (url.startsWith("sms:")) {
        		     Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
        		     startActivity(i);
        		     return true;
        		    }
        		    if (url.startsWith("tel:")) {
        		     Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(url));
        		     startActivity(i);
        		     return true;
        		    }
        		    if (url.startsWith("mailto:")) {
        		     Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
        		     startActivity(i);
        		     return true;
        		   }
	        	 view.loadUrl(url);//
	        	 return true;
        	 }
        	
         });
         
         WebSettings set = webView.getSettings();
         set.setJavaScriptEnabled(true);
         set.setBuiltInZoomControls(false);
         
         //자바스크립트에서 안드로이드 콜은 클래스를 등록하고 등록한 클래스 알리아스로 메소드를 호출한다 windows.CallAndroid + 안드로이드함수명 
     //    webView.addJavascriptInterface(new CallAndroid(), "CallAndroid");
         
        
     
        
    }
    
  
    	
}