package itsplace.net;




import com.facebook.Session;
import com.facebook.SessionState;
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
		
		Button btn = (Button) findViewById(R.id.btnCustomTab);
		final Intent intent = new Intent(this, MainActivity.class);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "작업이 끝났습니다.", Toast.LENGTH_LONG).show();
				startActivity(intent);

				// TODO Auto-generated method stub
				// Toast.makeText(getApplicationContext(),
				// main.getUser().getEmail(),1000).show();
				/*
				 * try { // The URL for making the GET request final String url
				 * = getString(R.string.base_uri) + "/state/{abbreviation}";
				 * 
				 * Log.i("","유알엘i:"+url);
				 * 
				 * // Set the Accept header for "application/json" HttpHeaders
				 * requestHeaders = new HttpHeaders(); List<MediaType>
				 * acceptableMediaTypes = new ArrayList<MediaType>();
				 * acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
				 * requestHeaders.setAccept(acceptableMediaTypes);
				 * 
				 * // Populate the headers in an HttpEntity object to use for
				 * the // request HttpEntity<?> requestEntity = new
				 * HttpEntity<Object>(requestHeaders);
				 * 
				 * // Create a new RestTemplate instance RestTemplate
				 * restTemplate = new RestTemplate();
				 * 
				 * ResponseEntity<State> responseEntity =
				 * restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				 * State.class, "al");
				 * 
				 * 
				 * Toast.makeText(getApplicationContext(),
				 * responseEntity.getBody().toString(),
				 * Toast.LENGTH_LONG).show(); // convert the array to a list and
				 * return it // return Arrays.asList(responseEntity.getBody());
				 * } catch (Exception e) { // Log.e(TAG, e.getMessage(), e); }
				 */
			}
		});
	}

	@Override
	protected void onStart() {
		
		super.onStart();
		L.i(TAG, "onStart 흡니다");
		init();
	}
	@Override
	protected void onResume() {
		super.onResume();
		L.i(TAG, "onResume 합니다");
		init();
	}
	
	private void init(){
	//	MainApplication main = (MainApplication) getApplication();
	//	user = main.getUser();
	//	Intent intent;
		
		if(AsyncClient.isValidCookie(this, "10.0.2.2")){
			L.i(TAG, " 현재 로그인중입니다");
			   
		    startActivity( new Intent(this, MainActivity.class));
		    finish();
    	}else{// 페이스북  로그인 체크
    		Session session = Session.getActiveSession();
    		if (session == null) {
	        	Log.i(TAG,"세션 생성");
	            session = new Session(this);
	           
	            Session.setActiveSession(session);
	    		if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
		           	Log.i(TAG,"세션 로그인폼");
		          // 	session.open
		         //   session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));    		    
		        }
	        }	         
    		else{
	        	
	        	loginForm();
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
	}
	private void loginForm(){
		startActivity( new Intent(this,LoginActivity.class));
	}
	 private class SessionStatusCallback implements Session.StatusCallback {
		 
	        @Override
	        public void call(Session session, SessionState state, Exception exception) {
	        	if(session.isOpened()){
	        		Log.i(TAG,"페시으스북 로그인 성공:"+state.name());
	        		Log.i(TAG,"페시으스북 로그인 성공:"+"https://graph.facebook.com/me/friends?access_token=" + session.getAccessToken());
	        	}else{
	        		Log.i(TAG,"콜백 로그인 실행");
	        		loginForm();
	        	}
	            //updateView();
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