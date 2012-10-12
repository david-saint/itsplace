package com.mincoms.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.google.android.gcm.GCMRegistrar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mincoms.main.R;
import com.mincoms.domain.Authority;
import com.mincoms.domain.UserInfo;
import com.mincoms.restful.RestClient;
import com.mincoms.restful.RestClient.RequestMethod;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginAsyncActivity extends Activity {   
	protected static final String TAG = LoginAsyncActivity.class.getSimpleName();
	private Integer progressBarValue=0;
	private AsyncTask<UserInfo, Void, UserInfo> loginAsyncTask; 
	protected Context context;
//	public LoginAsyncActivity(Context context,User user){
//		 this.context = context;
//		 new LoginAsync().execute(user);
//	}
	
	private String ValidationMessage;
	MainApplication main = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {	//new LoginAsync.execute(getApplicationContext().getUser());
	        super.onCreate(savedInstanceState);	

	         main = (MainApplication) getApplicationContext();
	         context = this;
	        UserInfo userInfo = (UserInfo) getIntent().getSerializableExtra("userInfo");
	    	if(userInfo==null){
				 //Toast.makeText( this,  "getApplicationContext null!",Toast.LENGTH_LONG).show();
				Log.i(TAG,"getApplicationContext().getUser()==null");
			}else{
				Log.i(TAG,"main.getUser():"+userInfo.getUserName());
				 //Toast.makeText( this,  getApplicationContext().getUser().getEmail()+"",Toast.LENGTH_LONG).show();
				loginAsyncTask = new LoginAsyncTask().execute(userInfo);	
			}
	}
	@Override
	public void onStart() {
		super.onStart();
	}
	
	private class LoginAsyncTask extends  AsyncTask<UserInfo, Void, UserInfo> {
		private ProgressDialog progress;
		@Override
		protected void onPreExecute() {
			progress = new ProgressDialog(context);
			progress.setMessage("로그인중입니다...");
			progress.setCancelable(true);
			progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					loginAsyncTask.cancel(true);
				}
			});
			
			progress.show();	
		}	
	    @Override
	    protected void onCancelled()
	    {	
	    	finish();
	    }
	  
		@Override
		protected UserInfo doInBackground(UserInfo... params) {
			UserInfo signedUser = null;
			UserInfo user = (UserInfo)params[0]	;
			String url = getString(R.string.base_uri) + "/signin/authenticate";
			RestClient restClient = new RestClient(url);
			
			Log.i(TAG,"로그인 시도:"+user.getUserName() + ":" + user.getPassword());
			restClient.AddParam("j_username", user.getUserName());
			restClient.AddParam("j_password", user.getPassword());
			restClient.AddParam("_spring_security_remember_me", "true");
			restClient.AddHeader("X-Ajax-call", "true");
			try {			
				restClient.Execute(RequestMethod.POST);
				if(restClient.getResponseCode() == 200){
					String result = restClient.getResponse();
					Log.i(TAG, "roles:"+result);
					
					if(!result.trim().equals("LoginFailed")){
						url = getString(R.string.base_uri) + "/user/getUser";
						
						String PROJECT_ID = "357588365971";
						GCMRegistrar.checkDevice(getApplicationContext());
						GCMRegistrar.checkManifest(getApplicationContext());
						if(GCMRegistrar.getRegistrationId(getApplicationContext()).equals("")){
							GCMRegistrar.register(getApplicationContext(), PROJECT_ID);
						}else{
							//이미 GCM 을 상요하기위해 등록ID를 구해왔음
							Log.i("test","이미등록함:"+GCMRegistrar.getRegistrationId(getApplicationContext()));
							//GCMRegistrar.unregister(getApplicationContext());
							//GCMRegistrar.register(getApplicationContext(), PROJECT_ID);
						}
						user.setGcmId(GCMRegistrar.getRegistrationId(getApplicationContext()));	
						restClient = new RestClient(url);
						restClient.AddParam("userName", user.getUserName());
						restClient.AddParam("gcmId", user.getGcmId());
						restClient.Execute(RequestMethod.POST);
						
						
						GsonBuilder gsonb = new GsonBuilder();
						DateDeserializer ds = new DateDeserializer();
						gsonb.registerTypeAdapter(Date.class, ds);
						Gson gson = gsonb.create();
						
						Log.i(TAG, "userinfo:"+restClient.getResponse());
						
						
						signedUser =  gson.fromJson(restClient.getResponse(), UserInfo.class);
				
						SharedPreferences userInfo = getSharedPreferences("MINCOMSUSERINFO", android.content.Context.MODE_PRIVATE);
				        Editor editor = userInfo.edit();
				        editor.putString("username", user.getUserName());      			        
				        editor.putString("password", Encrypt.encrypt("MinCommunication", user.getPassword()));		
				        Log.i(TAG, "암호화:"+Encrypt.encrypt("MinCommunication", user.getPassword() ));
				        editor.commit();
				        main.setLogged(true);
				        main.setUser(signedUser);
					}else{
						
					}
						
				}
				
			}catch (Exception e) {
				Log.i(TAG,"로그인:"+e.getMessage());
				e.printStackTrace();
			}
	
			return signedUser;
		}
		protected void onPostExecute(UserInfo loginUser) {
			progress.dismiss();
			showResult(loginUser);
		}

	}
	
	private void showResult(UserInfo user){
		Intent intent = getIntent();
		if(user==null){
			setResult(RESULT_CANCELED, intent);
		}else{
			if(user.getAuthlevel() == Authority.ADMIN.ordinal()){
				Log.i(TAG, "관리자");
			}
			 setResult(RESULT_OK, intent);
		}	
		finish();
	}	
	public String getValidationMessage() {
		return ValidationMessage;
	}
	public void setValidationMessage(String validationMessage) {
		ValidationMessage = validationMessage;
	}
	
	
}