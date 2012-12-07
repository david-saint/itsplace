package itsplace.net;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;
import itsplace.net.common.DateDeserializer;
import itsplace.net.connection.RestClient;
import itsplace.net.connection.RestClient.RequestMethod;
import itsplace.net.stamp.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

public class MyStampActivity extends FragmentActivity {
	protected static final String TAG = MyStampActivity.class.getSimpleName();	
	MyStampFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stamp_pager);
        Log.i(TAG, "MyStampActivity======================onCreate+new getStampedList().execute()");
		Log.i(TAG, "MyStampActivity======================onCreate");
		Log.i(TAG, "MyStampActivity======================onCreate");
		Log.i(TAG, "MyStam" +
				"pActivity======================onCreate");
		Log.i(TAG, "MyStampActivity======================onCreate");
        MainApplication main = (MainApplication) getApplication();			
		if(main.isLogged()){
			user = main.getUser();
		}
		Log.i(TAG, "new getStampedList().execute();");
		new getStampedList().execute();
		
		//new getStampedList().execute();
        mAdapter = new MyStampFragmentAdapter(getSupportFragmentManager(), user, getApplicationContext());
//
//        mPager = (ViewPager)findViewById(R.id.pager);
//        mPager.setAdapter(mAdapter);
//
//        mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
//        mIndicator.setViewPager(mPager);
    }
    @Override
  	protected void onStart() {		
      	Log.i(TAG, "onStart");
  		super.onStart();
  		
  		
  	}
    private void setStampList(List<PlaceStamp> stampList){
    	//mPagerAdapter
    	if(stampList.isEmpty()){
    		Log.i(TAG, "적립한 스탬프가 없음");
    		
    	}else{
    		Log.i(TAG, "적립한 스탬프 가맹점 갯수:"+stampList.size());
    		mPager = (ViewPager)findViewById(R.id.pager);
    		mAdapter.addStampList(stampList);
    	    mPager.setAdapter(mAdapter);
            mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
            mIndicator.setViewPager(mPager);
    	}
    	
 		
	
    }
      
    
    private class getStampedList extends AsyncTask<Void, Void, List<PlaceStamp>> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<PlaceStamp> doInBackground(Void... params) {
			if(user == null){
				Log.i(TAG,"유저 널입니다");
			}
                final String url = getString(R.string.base_uri) + "/stamp/placeStampList";
            	RestClient restClient = new RestClient(url);
   			 List<PlaceStamp> placeStampList = new ArrayList();
   			try {
   				restClient.AddParam("email", user.getEmail());
				
				restClient.Execute(RequestMethod.POST);
				if (restClient.getResponseCode() == 200) {
					try {
						JSONObject jsonResponse = restClient.getJsonObject();
						JSONArray placesJson = jsonResponse.getJSONArray("result");
						GsonBuilder gsonb = new GsonBuilder();
						DateDeserializer ds = new DateDeserializer();
						gsonb.registerTypeAdapter(Date.class, ds);
						Gson gson = gsonb.create();
						
						for (int i = 0; i < placesJson.length(); i++) {
							PlaceStamp placeStamp = gson.fromJson(placesJson.getString(i), PlaceStamp.class);
							placeStampList.add(placeStamp);
						//	Log.i(TAG, placeStamp.getStampTitle());
						//	Log.i(TAG, placeStamp.getPlace().getFname());
						//	Log.i(TAG, placeStamp.getSaveDate().toGMTString());
						}
					} catch (JSONException e) {
						Log.i(TAG,"스탬프 가맹점 조회 익셉션 발생 ");
						e.printStackTrace();
					}
				} else {
					Log.i(TAG, "스탬프 조회 오류");
				}
            } catch (Exception e) {
            	Log.e(TAG, e.getMessage()+ e.getLocalizedMessage(), e);
				Log.i(TAG, restClient.getErrorMessage(), e);
            }
			return placeStampList;
		}
		protected void onPostExecute(List<PlaceStamp> result){
				setStampList(result);
		}
    }
}