package itsplace.net;




import com.facebook.LoggingBehaviors;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.loopj.android.http.AsyncHttpClient;

import itsplace.library.restful.AsyncClient;
import itsplace.library.util.*;
import itsplace.net.user.LoginActivity;
import itsplace.net.user.LoginAsyncActivity;


import net.itsplace.domain.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ItsplaceActivity extends Activity {
  
	protected static final String TAG = ItsplaceActivity.class.getSimpleName();
	private User user;
	public static Activity exitActiviry;
	
	private AsyncHttpClient client = AsyncClient.getInstance();
	Session.StatusCallback statusCallback = new SessionStatusCallback();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		exitActiviry = this;
		L.i(TAG, "onCreate");
		// splash 및 초기화 작업
		//startActivity(new Intent(this, SplashActivity.class));

	//	TestMenu header = (TestMenu) findViewById(R.id.header);
	 //   header.initHeader();
		
		
		init(savedInstanceState);
	}

	@Override
	protected void onStart() {
		
		super.onStart();
		L.i(TAG, "onStart 흡니다");
		//init();
	}
	@Override
	protected void onResume() {
		super.onResume();
		L.i(TAG, "onResume 합니다");
		//init();
	}
	
	private void init(Bundle savedInstanceState){
	//	MainApplication main = (MainApplication) getApplication();
	//	user = main.getUser();
	//	Intent intent;
		
		if(AsyncClient.isValidCookie(getApplicationContext(), "10.0.2.2")){
			L.i(TAG, " 현재 로그인중입니다");
			   
		    startActivity( new Intent(this, MainActivity.class));
		    finish();
    	}else{
    		 startActivity( new Intent(this, MainActivity.class));
 		    finish();
    		//loginForm();
    		// 페이스북  로그인 체크
    		/*L.i(TAG, "페이스북 로그인 체크");
    		 	Settings.addLoggingBehavior(LoggingBehaviors.INCLUDE_ACCESS_TOKENS);

    	        Session session = Session.getActiveSession();
    	        if (session == null) {
    	        	L.i(TAG, "페이스북 로그인 체크 1");
    	            if (savedInstanceState != null) {
    	            	L.i(TAG, "페이스북 로그인 체크 2");
    	                session = Session.restoreSession(this, null, statusCallback, savedInstanceState);
    	            }
    	            if (session == null) {
    	            	L.i(TAG, "페이스북 로그인 체크 3");
    	                session = new Session(this);
    	            }
    	            Session.setActiveSession(session);
    	            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
    	            	L.i(TAG, "페이스북 로그인 체크 4");
    	                session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
    	            }else{
    	            	 L.i(TAG, "페이스북 로그인 체크5");
    	            	loginForm();
    	            }
    	           
    	        }*/
	        }	         
    		
    		
    	}
		 	
		/*
		if(main.isLogged()){
			L.i(TAG, " 현재 로그인중입니다");
		}else{
			if(user==null){
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			}
			else if (user.getEmail().length() > 0 && user.getPassword().length() > 0) {
				try {
					L.i(TAG,"자동로그인:"+user.getEmail()+":"+ user.getPassword());
	
					user.setPassword(Encrypt.decrypt("itsplace", user.getPassword()));
					Log.i(TAG,"복호화:"+user.getPassword());
					
					//new LoginAsyncActivity(getApplicationContext(), user);
					startActivity(new Intent(this,(LoginAsyncActivity.class)));
	
				} catch (Exception e) {
					// 앱이 종료되지 않았다면 main의 user는 이미 복호화 되어있음..
					L.i(TAG+"자동로그인 exception", e.getMessage(), e);
					
				}
	
			}else{
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			}
		}*/
	
	private void loginForm(){
		startActivity( new Intent(this,LoginActivity.class));
	}
	
	private class SessionStatusCallback implements Session.StatusCallback {
	        @Override
	        public void call(Session session, SessionState state, Exception exception) {
	            Log.i(TAG,"세션 콜백");
	            updateView();
	        }
	    }
	private void updateView() {
        Session session = Session.getActiveSession();
        if (session.isOpened()) {
           
            Log.i(TAG,"로그인성공:"+session.getAccessToken());
        } else {
        	
        	Log.i(TAG,"로그인실패:");
        	loginForm();
        }
}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	     
	    	 moveTaskToBack(true); // 본Activity finish후 다른 Activity가 뜨는 걸 방지.
	         finish();
	         android.os.Process.killProcess(android.os.Process.myPid()); 
	         // -> 해당 어플의 프로세스를 강제 Kill시킨다.
	    }
	    return super.onKeyDown(keyCode, event);
	}
}