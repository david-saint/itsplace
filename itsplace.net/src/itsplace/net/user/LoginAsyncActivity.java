package itsplace.net.user;

import itsplace.net.MainActivity;
import itsplace.net.R;
import itsplace.net.common.AbstractAsyncActivity;
import itsplace.net.common.Tdialog;
import itsplace.net.util.Encrypt;
import itsplace.net.util.L;
import itsplace.net.util.SharedPreference;

import java.util.ArrayList;
import java.util.List;

import net.itsplace.domain.User;

import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginAsyncActivity extends AbstractAsyncActivity {   
	protected static final String TAG = LoginAsyncActivity.class.getSimpleName();
	private Integer progressBarValue=0;
	private AsyncTask<User, Void, User> loginAsyncTask; 
//	protected Context context;
//	public LoginAsyncActivity(Context context,User user){
//		 this.context = context;
//		 new LoginAsync().execute(user);
//	}

	@Override
	public void onCreate(Bundle savedInstanceState) {	//new LoginAsync.execute(getApplicationContext().getUser());
	        super.onCreate(savedInstanceState);	        
	}
	@Override
	public void onStart() {
	
		super.onStart();
		if(getApplicationContext().getUser()==null){
			 Toast.makeText( this,  "getApplicationContext null!",Toast.LENGTH_LONG).show();
		}else{
			 Toast.makeText( this,  getApplicationContext().getUser().getEmail()+"",Toast.LENGTH_LONG).show();
			 loginAsyncTask = new LoginAsyncTask().execute(getApplicationContext().getUser());	
		}
		//
	
	}
	private void showResult(User user){
		if(user.getEmail()==null){
			//L.i(TAG, "Login onPostExecute:로그인할 수 없음");
		     Toast.makeText( this,  "Email 또는 비밀번호를 확인해주세요",Toast.LENGTH_LONG).show();
		     getApplicationContext().setLogged(false);
		 	finish();
		}else{
			
			try {
				//SharedPreference.putSharedPreference(context, "email", user.getEmail());
				//SharedPreference.putSharedPreference(context, "password",  Encrypt.encrypt("itsplace", user.getPassword()));
				SharedPreferences userInfo = getSharedPreferences("USERINFO", android.content.Context.MODE_PRIVATE);
				L.i(TAG, "Login onPostExecute:USERINFO저장");
				L.i(TAG, "Login onPostExecute:"+user.toString());
		        Editor editor = userInfo.edit();
		        editor.putString("email", getApplicationContext().getUser().getEmail());      			        
		        editor.putString("password", Encrypt.encrypt("itsplace", getApplicationContext().getUser().getPassword()));				 
		        editor.commit();
		        Toast.makeText( this,  "로그인완료 메인화면으로 이동"+user.getName()+user.getPassword(),Toast.LENGTH_LONG).show();
		   //   Toast.makeText(this, "", Toast.LENGTH_LONG).show();
		        getApplicationContext().setLogged(true);
		        final Intent intent = new Intent(this, MainActivity.class);
		        startActivity(intent);
		        finish();
			} catch (Exception e) {					
				L.i(TAG+"_showResult",e.getMessage(),e);
			} finally{
				
			}
		}	
		
		
		
	}
	private class LoginAsyncTask extends  AsyncTask<User, Void, User> {
		@Override
		protected void onPreExecute() {
			// before the network request begins, show a progress indicator
			showLoadingProgressDialog();
		}
		
		
		//@Override
		 // protected void onProgressUpdate(Integer... values) {
		   // TODO Auto-generated method stub
			//setProgressBar(values[0]);
		  //}

		 // 외부에서 강제로 취소할때 호출되는 메소드
	   //   @Override
	   //   protected void onCancelled()
	   //   {
	       
	    //    publishProgress(0);
	    //    Toast.makeText(LoginAsyncActivity.this, "취소됨", Toast.LENGTH_SHORT).show();
	    //  }
	  
		@Override
		protected User doInBackground(User... params) {
			User loginUser = null;
			try {
				int numberOfParams = params.length;

				/*for (int i = 0; i < numberOfParams; i++) {
					SystemClock.sleep(1000);

					publishProgress((int) (((i + 1) / (float) numberOfParams) * 100));
				}*/
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			
				RestTemplate restTemplate = new RestTemplate();
			//	List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
			//	messageConverters.add(new FormHttpMessageConverter());
			//	messageConverters.add(new StringHttpMessageConverter());// 리스폰스 객체가(responsebody) String 일때 한글깨짐현상이 있으면 컨벝너 사용할껏
			//	restTemplate.setMessageConverters(messageConverters);
				String url = getString(R.string.base_uri)+"/m/login";
				L.i(TAG, "Login URL"+url);
				User user = (User)params[0]	;
				MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
				mvm.add("email", user.getEmail());
				mvm.add("password", user.getPassword());
				L.i(TAG, "user.getEmail()"+user.getEmail());
				L.i(TAG, "user.getPassword()"+user.getPassword());
			//	Toast.makeText(getApplicationContext(),  "d"+user.getEmail(),1000).show();
				
				 loginUser = restTemplate.postForObject(url, mvm, User.class);
				
			}catch (Exception e) {
				Log.e(TAG+"_doInBackground", e.getMessage(), e);
			
				
			}
	
			return loginUser;
		}
	
		// when data set changes, you need to call proper method here.
		// do not call notifyDataSetChanged() in 'doInBackground()'.
		protected void onPostExecute(User user) {
			//loginAsyncTask.cancel(true);
			dismissProgressDialog();
			L.i(TAG, "종료");
			if(user != null){
				showResult(user);
			}else{
				 Toast.makeText(LoginAsyncActivity.this, "로그인 할 수 없습니다", Toast.LENGTH_SHORT).show();				 
			}
		//	cancel(true);
		}

	}
}