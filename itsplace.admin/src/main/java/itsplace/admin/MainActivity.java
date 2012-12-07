package itsplace.admin;

import java.util.Date;

import org.apache.cordova.DroidGap;
import org.apache.http.cookie.Cookie;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.ActionBarSherlock.OnCreateOptionsMenuListener;
import com.actionbarsherlock.ActionBarSherlock.OnOptionsItemSelectedListener;
import com.actionbarsherlock.app.SherlockActivity;


import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.ClipData.Item;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.Secure;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import com.actionbarsherlock.widget.SearchView;
import com.google.android.gcm.GCMRegistrar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.loopj.android.http.*;
import itsplace.library.restful.RestClient;
import itsplace.library.restful.RestClient.RequestMethod;
import android.widget.TextView;
import android.widget.Toast;
import itsplace.library.util.*;
public class MainActivity  extends DroidGap implements OnCreateOptionsMenuListener, OnOptionsItemSelectedListener {
    ActionBarSherlock mSherlock = ActionBarSherlock.wrap(this);
	protected static final String TAG = MainActivity.class.getSimpleName();
	//private ActionBarMenu actionBarMenu;
	private static AsyncHttpClient client = new AsyncHttpClient();
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {        
		MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.search_view, menu);
        
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView)item.getActionView();
        
	    if(searchView == null){
	    	Log.i(TAG,"널이다 서치뷰");
	    }else{
	    	Log.i(TAG,"널이 아니다 서치뷰");
	    	//searchView.setSubmitButtonEnabled(true);
	    	searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
	            @Override public boolean onQueryTextSubmit(String searchword) {
	            	//Log.i(TAG,"검색:"+s);
	            	 search(searchword);
	                return true;
	              }
	
	              @Override public boolean onQueryTextChange(String s) {
	            	  Log.i(TAG,"검색 타이핑:"+s);
	                return false;
	              }
	            });
	    	searchView.setOnCloseListener(new SearchView.OnCloseListener() {
				
				@Override
				public boolean onClose() {
					 Log.i(TAG,"클로즈");
					search("");
					
					return true;
				}
			});
	    }
     
        menu.add(0,100,0,"Refresh")//그룹,아이디
            .setIcon( R.drawable.ic_refresh)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        return mSherlock.dispatchCreateOptionsMenu(menu);
    }
    @Override
    
	public boolean onOptionsItemSelected(MenuItem item) {
		return true;
	}
    private void search(String searchword){
    	super.loadUrl("javascript:search('"+searchword+"')");
    }
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
    	//Toast.makeText(this, "Got click: " + item.getItemId()+item.toString(), Toast.LENGTH_SHORT).show();
    	switch (item.getItemId()) {
	        case android.R.id.home:
	        	String android_id = Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
	        	//new LoginAsyncTask().execute("");
	        	
	        //로그인 쿠키 저장	
	        /*	RequestParams params = new RequestParams();
	        	params.put("j_username", "faye12005@gmail.com");
	        	params.put("j_password","1111");
	        	params.put("_spring_security_remember_me", "true");
	        	client.post("http://10.0.2.2:8080/signin/authenticate",params,new AsyncHttpResponseHandler() {
	        	     @Override
	        	     public void onSuccess(String response) {
	        	    	 Log.i(TAG,"로그인 :"+response);
	        	    	
	        	     }
	        	 });
	       	 PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
	       	
	    	 client.setCookieStore(myCookieStore);*/
	        	
	        
	        	//쿠키 가져오기
	       	 PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
	       	 for( Cookie  c : myCookieStore.getCookies() ){
	       	   	Log.i(TAG,"도메인============== :"+c.getDomain());
	       	   	Log.i(TAG,c.getDomain()+"============== :"+c.getName());
	       	   	Log.i(TAG,c.getDomain()+"도메인============== :"+c.getPath());
	       	 Log.i(TAG,c.getDomain()+"도메인============== :"+c.getValue());
	       	 Log.i(TAG,c.getDomain()+"도메인============== :"+c.getVersion());
	       	 Log.i(TAG,c.getDomain()+"도메인============== :"+c.getPorts());
	       	Log.i(TAG,c.getDomain()+"도메인============== :"+c.getExpiryDate());
	       	 }
	       	myCookieStore.addCookie(myCookieStore.getCookies().get(0));
	       	client.setCookieStore(myCookieStore);
	    
	        	client.get("http://10.0.2.2:8080/admin/nfc",new AsyncHttpResponseHandler() {
	        		@Override
	        	     public void onStart() {
	        	         // Initiated the request
	        	     }

	        	     @Override
	        	     public void onSuccess(String response) {
	        	         // Successfully got a response
	        	     }
	        	 
	        	     @Override
	        	     public void onFailure(Throwable e, String response) {
	        	         // Response failed :(
	        	     }

	        	     @Override
	        	     public void onFinish() {
	        	         // Completed the request (either success or failure)
	        	     }
	        	 });
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	        	//Toast.makeText(this, android_id, Toast.LENGTH_SHORT).show();
	        //	restClient.AddParam("j_username", "faye12005@gmail.com");
			//	restClient.AddParam("j_password","1111");
			//	restClient.AddParam("_spring_security_remember_me", "true");
				//Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
	        	 //super.loadUrl("http://place.cloudfoundry.com/admin/nfc");
	        	break;
	         //   imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	      
	        case  100 :
	        	Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show();
	        	break;
    	}
    	// startActivity(new Intent(this,(NfcActivity.class)));	
        return mSherlock.dispatchOptionsItemSelected(item);
    }
  


    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Theme_example);
    	super.setBooleanProperty("showTitle",true); // 액션바 애드전에 호출해야함
    	
        super.onCreate(savedInstanceState);
      //  EditText editText = (EditText)findViewById(R.id.search);
      //  editText.setText("dddd");
     //   AndroidCall androidCall = new AndroidCall(this, super.appView); 
        //setContentView(R.layout.main);
        
    
   //  super.loadUrl("file:///android_asset/www/index.html");
        super.loadUrl("http://10.0.2.2:8080/admin/nfc");
     //   super.loadUrl("http://place.cloudfoundry.com/admin/nfc");
        super.appView.setVerticalScrollBarEnabled(true);
    	super.appView.setHorizontalScrollBarEnabled(false);
    	
       // super.appView.addJavascriptInterface(androidCall, "AndroidCall");
        super.appView.addJavascriptInterface(this, "AndroidCall");
        
     //   mSherlock.setUiOptions(ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW); // 안드로이드 버전이 낮으면 액션바가 화면화단에 붙는다 
        mSherlock.getActionBar().setHomeButtonEnabled(true);
        mSherlock.getActionBar().setDisplayShowTitleEnabled(false);
      
    }
    public void nfcReady(String fid){
		//Toast.makeText(mGap, message, Toast.LENGTH_LONG).show();
    	try{
    		  Intent intent = new Intent(this, NfcActivity.class);
    	      intent.putExtra("nfckey", Encrypt.encrypt("itsplace",fid));
    	      startActivityForResult(intent,1);
    	      
    	}catch(Exception e){
    		
    	}
	
    
    	Toast.makeText(this, "Got click", Toast.LENGTH_SHORT).show();
	    //  startActivity(new Intent(MainActivity.this,(NfcActivity.class)));	
	}
	
    private class LoginAsyncTask extends  AsyncTask<String, Void, Void> {
		
		@Override
		protected void onPreExecute() {
				
		}	
	    @Override
	    protected void onCancelled()
	    {	
	    
	    }
	  
		
		protected void onPostExecute(String xx) {
		
		}
		@Override
		protected Void doInBackground(String... params) {
		/*	String url = "http://10.0.2.2:8080/signin/authenticate";
			RestClient restClient = new RestClient(url);
			//Log.i(TAG,"로그인 시도:"+user.getUserName() + ":" + user.getPassword());
			restClient.AddParam("j_username", "faye12005@gmail.com");
			restClient.AddParam("j_password","1111");
			restClient.AddParam("_spring_security_remember_me", "true");
			//restClient.AddHeader("X-Ajax-call", "true");
			try {			
				restClient.Execute(RequestMethod.POST);
			}catch(Exception e){
				Log.i(TAG,"로그인 실패:");
				e.printStackTrace();
			}*/
				String url = "http://10.0.2.2:8080/admin/nfc";
			RestClient restClient = new RestClient(url);
		
			try {			
				restClient.Execute(RequestMethod.GET);
			}catch(Exception e){
				Log.i(TAG,"겟 실패:");
				e.printStackTrace();
			}
			return null;
		}
	

	}

}

