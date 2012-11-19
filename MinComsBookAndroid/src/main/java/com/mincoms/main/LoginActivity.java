package com.mincoms.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mincoms.domain.UserInfo;

public class LoginActivity  extends Activity  {
	protected static final String TAG = LoginActivity.class.getSimpleName();

	public static Activity exitActiviry;
	EditText usernameEditText;
	EditText passwordEditText ;
	TextView signUpTextView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		exitActiviry = this;
		
		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		usernameEditText.setText("dhkim");
		passwordEditText.setText("1111");

		Button loginButton = (Button) findViewById(R.id.btnLogin);
		
		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				UserInfo userInfo = new UserInfo();
				userInfo.setUserName(usernameEditText.getText().toString().trim());
				userInfo.setPassword(passwordEditText.getText().toString().trim());

				if (!userInfo.getUserName().equals("") || !userInfo.getPassword().equals("")) {
					  Intent intent = new Intent(LoginActivity.this, LoginAsyncActivity.class);
				      intent.putExtra("userInfo", userInfo);
				      startActivityForResult(intent,1);
				}
			}
		});
		
		if(MainActivity.topActivity==null){
			init();
		}
		
	
		
	}
	private void init(){
		MainApplication main = (MainApplication) getApplication();
		
		
			UserInfo userInfo = main.getUser();
			Intent intent = null;
			
			if(userInfo==null){
				Log.i(TAG, " 로그인 정보가 없다");
				//intent = new Intent(this, LoginActivity.class);
				//startActivity(intent);
			}
			else if (userInfo.getUserName().length() > 0 && userInfo.getPassword().length() > 0) {
				try {
					Log.i(TAG,"자동로그인:"+userInfo.getUserName()+":"+ userInfo.getPassword());
	
					userInfo.setPassword(Encrypt.decrypt("MinCommunication", userInfo.getPassword()));
					Log.i(TAG,"복호화 password:"+userInfo.getPassword());
					//user.setPassword("11");
					
					//startActivity(new Intent(this,(LoginAsyncActivity.class)));
					intent = new Intent(LoginActivity.this,LoginAsyncActivity.class);
					intent.putExtra("userInfo", userInfo);
				    startActivityForResult(intent,1);
	
				} catch (Exception e) {
					// 앱이 종료되지 않았다면 main의 user는 이미 복호화 되어있음..
					Log.i(TAG+"자동로그인 exception", e.getMessage(), e);
					
				}
	
			}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		if(requestCode == 1){
			if(resultCode == RESULT_OK){
				Log.i(TAG,"로그인결과 메인실행함");
				
					startActivity(new Intent(this,(MainActivity.class)));					  
				
					
				
				this.finish();
			}
			else if(resultCode == RESULT_CANCELED){
				Toast.makeText( this, "로그인 할 수 없습니다",Toast.LENGTH_LONG).show();
	      }
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	
		       	 moveTaskToBack(true); // 본Activity finish후 다른 Activity가 뜨는 걸 방지.
		         finish();
		         android.os.Process.killProcess(android.os.Process.myPid());
	    	
	 
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
