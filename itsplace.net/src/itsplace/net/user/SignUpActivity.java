package itsplace.net.user;

import itsplace.net.MainApplication;
import itsplace.net.R;
import itsplace.net.common.AbstractAsyncActivity;
import itsplace.net.common.TestMenu;
import itsplace.net.util.L;

import java.util.ArrayList;
import java.util.List;

import net.itsplace.domain.User;

import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AbstractAsyncActivity{
	protected static final String TAG = SignUpActivity.class.getSimpleName();
	
	
	String errorMessage;
	EditText emailEditText;
	EditText passwordEditText ;
	EditText nameEditText ;
	EditText mobileEditText ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_activity);
		emailEditText = (EditText) findViewById(R.id.email);
		passwordEditText = (EditText) findViewById(R.id.password);
		nameEditText = (EditText) findViewById(R.id.name);
		mobileEditText = (EditText) findViewById(R.id.mobile);

		Button button = (Button) findViewById(R.id.btnSignUp);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				User user = new User();
				user.setEmail(emailEditText.getText().toString());
				user.setPassword(passwordEditText.getText().toString());
				user.setName(nameEditText.getText().toString());
				user.setMobile(mobileEditText.getText().toString());

				if (user.getEmail() == "" || user.getPassword() == "") {
					Toast.makeText(getApplicationContext(),
							" itsplace 이메일 또는 비밀번호흫 입력해주세요", 1000).show();
				} else {
					 new SignUpAsync().execute(user);

				}
			}
		});
		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);				
				startActivity(intent);
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
		AccountManager mgr = AccountManager.get(this);
        Account[] accts = mgr.getAccounts();
        final int count = accts.length;
        Account acct = null;
              
        for(int i=0;i<count;i++) {
           acct = accts[i];
           L.i("ANDROES", "Account - name="+acct.name+", type="+acct.type);
        }       
	}

	private class SignUpAsync extends AsyncTask<User, Void, User> {

		@Override
		protected void onPreExecute() {
			showLoadingProgressDialog();
		}

		@Override
		protected User doInBackground(User... params) {
			String result="false";
			User user = (User)params[0]	;
			try {				
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				//acceptableMediaTypes.add(MediaType.);
			
				Log.i(TAG, "rest11111111111111111111111");
				RestTemplate restTemplate = new RestTemplate();
				String url = getString(R.string.base_uri)+"/user/saveUser";
				
				Log.i(TAG, url);
				List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
				messageConverters.add(new FormHttpMessageConverter());
				messageConverters.add(new StringHttpMessageConverter());
				messageConverters.add(new MappingJacksonHttpMessageConverter());
				restTemplate.setMessageConverters(messageConverters);
				
				MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
				mvm.add("email", user.getEmail());
				mvm.add("password", user.getPassword());
				mvm.add("name", user.getName());
				mvm.add("mobile", user.getMobile());
				
				Log.i(TAG, "rest post");
			
				result =  (String)restTemplate.postForObject(url, mvm, String.class);
				L.i(TAG, result);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}

			if(result.equals("true")){
				return user;
			}else{
				errorMessage = result;
				//Toast.makeText(getBaseContext(),  result,1000).show();
				//Toast.makeText(SignUpActivity.this,  result,1000).show();
				return null;
			}
		}

		// when data set changes, you need to call proper method here.
		// do not call notifyDataSetChanged() in 'doInBackground()'.
		@Override
		protected void onPostExecute(User user) {
			dismissProgressDialog();
		//	AlertDialog.Builder alert = new AlertDialog.Builder(getBaseContext());
		//	alert.setTitle("회원가입");
			
			
			if(user == null){
				
				 Toast.makeText(SignUpActivity.this,  errorMessage,1000).show();
				//alert.setMessage("오류발생");
			
			}else{ Toast.makeText(getBaseContext(),  "가입성공",1000).show();
				L.i(TAG, "Login onPostExecute: 가입성공") ;
				Intent intent;
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
				//화원가입후 메세지 뿌리고 메일인증 하도로 ㄱ 유도
				startActivity(intent);
				MainApplication main = (MainApplication) getApplication();
				main.setUser(user);
				intent = new Intent(SignUpActivity.this, LoginActivity.class);
				//intent.putExtra("Email", user.getEmail());
				startActivity(intent);
				
				
			//	new LoginAsyncActivity(getBaseContext(), user);
				/*
				alert.setMessage("완료");
				alert.setPositiveButton( "확인", new DialogInterface.OnClickListener() {
				    public void onClick( DialogInterface dialog, int which) {
				        dialog.dismiss();   //닫기
				        
				    }
				});\
				*/
				 //Toast.makeText(getBaseContext(),  "가입완료",1000).show();
				    
			}	
			 
		}

	}
}
