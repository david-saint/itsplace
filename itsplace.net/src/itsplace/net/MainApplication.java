package itsplace.net;
import itsplace.net.util.L;
import itsplace.net.util.SharedPreference;

import net.itsplace.domain.User;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/* 전역 객체 */
public class MainApplication extends Application {

	private User user;

	private boolean isLogged = false;
	protected static final String TAG = MainApplication.class.getSimpleName();
	@Override
	public void onCreate() {
		
		L.i(TAG,"MainApplication start");
		SharedPreferences userInfo = getSharedPreferences("USERINFO", MODE_PRIVATE);
		String email = userInfo.getString("email","");
		String password = userInfo.getString("password","");
		//String email = SharedPreference.getSharedPreference(getBaseContext(),"email");
		//String password = SharedPreference.getSharedPreference(getBaseContext(),"password");
		
		if(email!=""){
			user = new User();
			user.setEmail(email);
			user.setPassword(password);
			L.i(TAG,"USERINFO[email]:"+email);
		    L.i(TAG,"USERINFO[password]:"+password);
		}else{
			L.i(TAG,"최초 설치 또는 로그인 한적 없음");
			
		}
			
		
		
      
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	
	
}
