package itsplace.library.restful;


import itsplace.library.util.DateUtil;

import org.apache.http.cookie.Cookie;

import android.util.Log;
import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

public class AsyncClient {
	private static AsyncHttpClient instance = null;
	protected static final String TAG = AsyncClient.class.getSimpleName();
	private AsyncClient() {
		instance = new AsyncHttpClient();
	}
	
	public static AsyncHttpClient getInstance() {
         if(instance == null ) {
                 instance = new AsyncHttpClient();
         }
         
         return instance;
	}
	 
	/*
	 * 특정 사이트의 쿠키가 존재하는지 존재한다면  만료기간 확인
	 */
	public static boolean isValidCookie(Context context, String domain){
		 boolean result = false;
		 PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
		 Cookie realCookie = null;
       	 for( Cookie  c : myCookieStore.getCookies() ){
       	   	Log.i(TAG,"도메인============== :"+c.getDomain());
       	   	Log.i(TAG,c.getDomain()+"==============getName :"+c.getName());
       	   	Log.i(TAG,c.getDomain()+"==============getPath:"+c.getPath());
       	   	Log.i(TAG,c.getDomain()+"==============getValue :"+c.getValue());
       	   	Log.i(TAG,c.getDomain()+"==============getVersion :"+c.getVersion());
       	   	Log.i(TAG,c.getDomain()+"==============getPorts :"+c.getPorts());
       	   	Log.i(TAG,c.getDomain()+"==============getExpiryDate :"+c.getExpiryDate());
       	   	if(domain.equals(c.getDomain()) && c.getName().equals("SPRING_SECURITY_REMEMBER_ME_COOKIE")){
       	   		realCookie = c;
       	   	}       	   	
       	 }
       	 
       	 if(realCookie == null){
       		Log.i(TAG,"최초 로그인 유휴한 쿠키 없음");
       	 }else{
       		if(DateUtil.DateCompareToday(realCookie.getExpiryDate()) == 1){
   	   			Log.i(TAG,"만료일 지났음 유휴한 쿠키 없음 로그인 필요함");
   	   			result =false;
   	   		}else{
   	   			Log.i(TAG,"유휴한 쿠키 있음");
   	   			Log.i(TAG,"도메인============== :"+realCookie.getDomain());
   	   			Log.i(TAG,realCookie.getDomain()+"==============getExpiryDate :"+realCookie.getExpiryDate());
       	   		result = true;
       	   		instance.setCookieStore(myCookieStore);
   	   		}
   	   	}
       	return result;
	}
 
}
