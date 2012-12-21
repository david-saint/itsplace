package itsplace.net.user;

import java.util.ArrayList;
import java.util.List;



import org.apache.http.cookie.Cookie;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.actionbarsherlock.app.SherlockActivity;
import com.facebook.FacebookActivity;
import com.facebook.LoggingBehaviors;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.model.GraphUser;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import net.itsplace.domain.User;

import itsplace.net.ItsplaceActivity;
import itsplace.net.MainActivity;
import itsplace.net.MainApplication;
import itsplace.net.QRcodeActivity;
import itsplace.net.R;

import itsplace.net.common.AbstractAsyncActivity;
import itsplace.net.common.Tdialog;

import itsplace.library.restful.AsyncClient;
import itsplace.library.util.*;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity  extends SherlockActivity  {
	protected static final String TAG = LoginActivity.class.getSimpleName();
	 Session.StatusCallback statusCallback = new SessionStatusCallback();
	private  AsyncHttpClient client = AsyncClient.getInstance();
	public static Activity exitActiviry;
	EditText emailEditText;
	EditText passwordEditText ;
	TextView signUpTextView;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		exitActiviry = this;
		if(ItsplaceActivity.exitActiviry!=null){
			ItsplaceActivity.exitActiviry.finish();
		}
		
		emailEditText = (EditText) findViewById(R.id.email);
		passwordEditText = (EditText) findViewById(R.id.password);
		emailEditText.setText("faye12005@gmail.com");
		passwordEditText.setText("hoon1014");
		// showLoadingProgressDialog();
		Button btnFacebookLogin = (Button) findViewById(R.id.btnFacebookLogin);
		/*Button btnFacebooklogout = (Button) findViewById(R.id.btnFacebooklogout);
		btnFacebooklogout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onClickFaceBookLogout();
				
			}
		});*/
		btnFacebookLogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onClickFaceBookLogin();
			}
		});
		
		
		Button loginButton = (Button) findViewById(R.id.btnLogin);
		final Intent intent = new Intent(this, (LoginAsyncActivity.class));
		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				User user = new User();
				user.setEmail(emailEditText.getText().toString().trim());
				user.setPassword(passwordEditText.getText().toString().trim());

				MainApplication main = (MainApplication) getApplication();
				main.setUser(user);
				if (user.getEmail().equals("") || user.getPassword().equals("")) {
					Toast.makeText(getApplicationContext(),
							" It's place 에 이메일 또는 비밀번호흫 입력해주세요", 1000).show();
				} else {
					startActivity(intent);
					// new LoginAsync().execute(user);
					// new LoginAsyncActivity(getBaseContext(),user);
				}
			}
		});
		 final LinearLayout aniLayout = (LinearLayout)this.findViewById(R.id.aniLayout);
		signUpTextView = (TextView)findViewById(R.id.signUp);
		signUpTextView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//startActivity(new Intent(getApplicationContext(), (SignUpActivity.class)));
				
				setLayoutAnim_slidedownfromtop(aniLayout);
			}
		});
		
		
		
		   Settings.addLoggingBehavior(LoggingBehaviors.INCLUDE_ACCESS_TOKENS);

	        Session session = Session.getActiveSession();
	        if (session == null) {
	            if (savedInstanceState != null) {
	            	Log.i(TAG,"세션 리스토어");
	                session = Session.restoreSession(this, null, statusCallback, savedInstanceState);
	            }
	            if (session == null) {
	            	Log.i(TAG,"세션 생성");
	                session = new Session(this);
	            }
	            Session.setActiveSession(session);
	            Log.i(TAG,"세션 액티브");
	            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
	            	Log.i(TAG,"세션 로그인폼");
	                session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
	            }
	        }else{
	        	Log.i(TAG,"세션널");
	        }
		
	}
    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
        	Log.i(TAG,"콜백 세션실행");
        	if(session.isOpened()){
        		Log.i(TAG,"페시으스북 로그인 성공:"+state.name());
        		Log.i(TAG,"페시으스북 로그인 성공:"+"https://graph.facebook.com/me/friends?access_token=" + session.getAccessToken());
        		String url = "https://graph.facebook.com/me/?access_token=" + session.getAccessToken();      
        		final String token = session.getAccessToken();
        		
				client.get(url,  new AsyncHttpResponseHandler() {
		    		@Override
		    	     public void onStart() {
		    		  	Log.i(TAG,"onStart");
		    	     }

		    	     @Override
		    	     public void onSuccess(String response) {
		    	    	 Log.i(TAG,"onSuccess:"+response);
		    	    	 try {
							JSONObject json =  new JSONObject(response);
							String email = json.get("email").toString();
							String name = json.get("name").toString();
							
							socialLogin(email, name, token);
							 
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    	     }
		    	 
		    	     @Override
		    	     public void onFailure(Throwable e, String response) {
		    	    	 Log.i(TAG,"onFailure"+response);
		    	    	 Log.i(TAG,e.getMessage());
		    	     }

		    	     @Override
		    	     public void onFinish() {
		    	    	 Log.i(TAG,"onFinish");
		    	     }
		    	 });
		    	 
        		//최초가입시 회원 등록
        		
        		
        	
        	}
        	
            //updateView();
        }
       
        
    }
    private void socialLogin(final String email, final String name ,final String token){
    	final Intent intent = new Intent(this, (FacebookSignupActivity.class));
    	String url = getString(R.string.base_uri) + "/user/facebooklogin";
		RequestParams params = new RequestParams();
    	params.put("email", email);
    	params.put("token", token);
		client.post(url, params, new AsyncHttpResponseHandler() {
    		@Override
    	     public void onStart() {
    		  	Log.i(TAG,"onStart");
    	     }

    	     @Override
    	     public void onSuccess(String response) {
    	    	 //회원이 아니면 회원가입 창
    	    	 Log.i(TAG,"로그인 성공 "+response);
    	    		JSONObject json;
					try {
						json = new JSONObject(response);
						String status = json.get("status").toString();
						Log.i(TAG,"결과:"+status);
						if(status.equals("FAIL")){
							
							intent.putExtra("email", email);
							intent.putExtra("name", name);
							intent.putExtra("token", token);
							startActivityForResult(intent,0);
						}else{
							 saveCookie();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
    	    	
    	    	
    	     }
    	 
    	     @Override
    	     public void onFailure(Throwable e, String response) {
    	    	 Log.i(TAG,"onFailure"+response);
    	    	 Log.i(TAG,e.getMessage());
    	     }

    	     @Override
    	     public void onFinish() {
    	    	 Log.i(TAG,"onFinish");
    	     }
    	 });
		
		
    }
    private void saveCookie(){
    	Log.i(TAG,"쿠키저장");
    	 PersistentCookieStore myCookieStore = new PersistentCookieStore(getApplicationContext());
    	 client.setCookieStore(myCookieStore);
    	/* for( Cookie  c : myCookieStore.getCookies() ){
        	   	Log.i(TAG,"도메인============== :"+c.getDomain());
        	   	Log.i(TAG,c.getDomain()+"==============getName :"+c.getName());
        	   	Log.i(TAG,c.getDomain()+"==============getPath:"+c.getPath());
        	   	Log.i(TAG,c.getDomain()+"==============getValue :"+c.getValue());
        	   	Log.i(TAG,c.getDomain()+"==============getVersion :"+c.getVersion());
        	   	Log.i(TAG,c.getDomain()+"==============getPorts :"+c.getPorts());
        	   	Log.i(TAG,c.getDomain()+"==============getExpiryDate :"+c.getExpiryDate());
        	        	   	
        	 }*/
    	 startActivity( new Intent(this, MainActivity.class));
    	 finish();
    }
    private void onClickFaceBookLogin() {
        Session session = Session.getActiveSession(); 
        if (!session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
        } else {
            Session.openActiveSession(this, true, statusCallback);
        }
    }
    private void onClickFaceBookLogout() {
        Session session = Session.getActiveSession();
        if (!session.isClosed()) {
            session.closeAndClearTokenInformation();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    	Log.i(TAG,"온스타트 콜백");
        Session.getActiveSession().addCallback(statusCallback);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"온스탑콜백");
        Session.getActiveSession().removeCallback(statusCallback);
    }
  
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG,"리절트 콜백 resultCode"+resultCode);
        Log.i(TAG,"리절트 콜백 requestCode"+requestCode);
        if(requestCode==RESULT_OK){
        	//회원가입성공
        	
        	socialLogin(data.getStringExtra("email").toString(),data.getStringExtra("name").toString(), data.getStringExtra("token").toString());
        }else{
        	 Log.i(TAG,"페이스북 로그인 성공");
        	Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
        }
        //페이스북 로그인인증시 -1, 64206
        //회원가입되어있는지  확인한다. 
        // 회원가입은 페이스북 아이디로 회원가입하고 호그인을 거친다.
       // Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }

	/*@Override
	public void onStart() {
		super.onStart();
		
		try {
			MainApplication main = (MainApplication) getApplication();
			if (main == null) {
				L.i(TAG + "onStart", "LoginActivity Main null");
			} else {

				if(main.getUser()!=null){
				
					emailEditText.setText(main.getUser().getEmail());
					
				}

			}
		} catch (Exception e) {
			L.i(TAG + "onStart", "onStart");
		}
	}*/

//	private class LoginAsync extends AsyncTask<User, Void, User> {
//
//		@Override
//		protected void onPreExecute() {
//			// before the network request begins, show a progress indicator
//			//  showLoadingProgressDialog();
//		}
//
//		@Override
//		protected User doInBackground(User... params) {
//			try {
//				
//				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
//				acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
//			
//				RestTemplate restTemplate = new RestTemplate();
//			//	List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//			//	messageConverters.add(new FormHttpMessageConverter());
//			//	messageConverters.add(new StringHttpMessageConverter());// 리스폰스 객체가(responsebody) String 일때 한글깨짐현상이 있으면 컨벝너 사용할껏
//			//	restTemplate.setMessageConverters(messageConverters);
//			
//				String url = getString(R.string.base_uri)+"/m/login";
//				
//				User user = (User)params[0]	;
//				MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
//				mvm.add("email", user.getEmail());
//				mvm.add("password", user.getPassword());
//				
//				L.i(TAG, "doInBackground: user: "+user.getEmail()+"");
//				L.i(TAG, "doInBackground: user: "+user.getEmail()+"");
//				L.i(TAG, "doInBackground: user: "+user.getEmail()+"");
//				L.i(TAG, "doInBackground: user: "+user.getEmail()+"");
//				L.i(TAG, "doInBackground: user: "+user.getEmail()+"");
//			//	Toast.makeText(getApplicationContext(),  "d"+user.getEmail(),1000).show();
//
//				return (User)restTemplate.postForObject(url, mvm, User.class);
//				
//			} catch (Exception e) {
//				Log.e(TAG, e.getMessage(), e);
//			}
//
//			return null;
//		}
//
//		// when data set changes, you need to call proper method here.
//		// do not call notifyDataSetChanged() in 'doInBackground()'.
//		protected void onPostExecute(User user) {
//			  // hide the progress indicator when the network request is complete
//         
//			if(user == null){
//				Toast.makeText(getBaseContext(),  "로그인 할 수 없습니다!",1000).show();
//			}else{
//				
//				try {
//					SharedPreferences userInfo = getSharedPreferences("USERINFO", MODE_PRIVATE);
//			        Editor editor = userInfo.edit();
//			        editor.putString("email", user.getEmail());      			        
//			        editor.putString("password", Encrypt.encrypt("itsplace", user.getPassword()));				 
//			        editor.commit();
//			        
//			      //  Intent intent = new Intent(LoginActivity.this, MainActivity.class);					
//					//startActivity(intent);
//					
//				} catch (Exception e) {					
//					Log.e(TAG, e.getMessage(), e);
//				}      
//			}	
//			 
//		}
//
//	}
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
	
	 /* ViewGroup를 아규먼트로 받아서 해당 ViewGroup에 추가되는 뷰가 있을 경우에 적용하는 애니메이션을 정의함 */
    public static void setLayoutAnim_slidedownfromtop(ViewGroup panel) {
       AnimationSet set = new AnimationSet(true);
       Animation animation = new AlphaAnimation(0.0f, 1.0f);
       animation.setDuration(1000);
       set.addAnimation(animation);
       animation = new TranslateAnimation(
           Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
           Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f
       );
       animation.setDuration(1000);
       set.addAnimation(animation);
       /*
       ScaleAnimation scale = new ScaleAnimation(-1, 1, -1, 1, 
         ScaleAnimation.RELATIVE_TO_SELF, 1f, 
         ScaleAnimation.RELATIVE_TO_SELF, 1f); 
       set.addAnimation(scale);
       */
       panel.startAnimation(set);
    }
}
