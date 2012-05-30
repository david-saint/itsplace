package itsplace.net.user;

import java.util.ArrayList;
import java.util.List;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import net.itsplace.domain.User;

import itsplace.net.ItsplaceActivity;
import itsplace.net.MainActivity;
import itsplace.net.MainApplication;
import itsplace.net.R;

import itsplace.net.common.AbstractAsyncActivity;
import itsplace.net.common.Tdialog;
import itsplace.net.util.Encrypt;
import itsplace.net.util.L;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity  extends Activity  {
	protected static final String TAG = LoginActivity.class.getSimpleName();


	EditText emailEditText;
	EditText passwordEditText ;
	TextView signUpTextView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		if(ItsplaceActivity.exitActiviry!=null){
			ItsplaceActivity.exitActiviry.finish();
		}
		
		emailEditText = (EditText) findViewById(R.id.email);
		passwordEditText = (EditText) findViewById(R.id.password);
		
		// showLoadingProgressDialog();
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

		signUpTextView = (TextView)findViewById(R.id.signUp);
		signUpTextView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), (SignUpActivity.class)));
			}
		});
	}

	@Override
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
	}

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
	       //   android.os.Process.killProcess(android.os.Process.myPid()); 
	         // -> 해당 어플의 프로세스를 강제 Kill시킨다.
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
