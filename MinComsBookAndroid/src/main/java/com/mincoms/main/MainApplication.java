package com.mincoms.main;
import com.mincoms.domain.UserInfo;



import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/* 전역 객체 */
public class MainApplication extends Application {

	private UserInfo user;

	private boolean isLogged = false;
	protected static final String TAG = MainApplication.class.getSimpleName();
	@Override
	public void onCreate() {
		
		
		SharedPreferences userInfo = getSharedPreferences("MINCOMSUSERINFO", MODE_PRIVATE);
		int userid = userInfo.getInt("userid", 0);
		String username = userInfo.getString("username","");
		String password = userInfo.getString("password","");
		//String email = SharedPreference.getSharedPreference(getBaseContext(),"email");
		//String password = SharedPreference.getSharedPreference(getBaseContext(),"password");
		
		if(username!=""){
			user = new UserInfo();
			user.setUserId(userid);
			user.setUserName(username);
			user.setPassword(password);
			Log.i(TAG,"USERINFO[email]:"+username);
			Log.i(TAG,"USERINFO[password]:"+password);
		}else{
			Log.i(TAG,"최초 설치 또는 로그인 한적 없음");
			
		}
			
		
		
      
	}
	
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}

	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	
	
}
