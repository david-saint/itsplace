package itsplace.net;

import itsplace.net.common.DateDeserializer;
import itsplace.net.connection.RestClient;
import itsplace.net.connection.RestClient.RequestMethod;
import itsplace.net.util.L;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Stamp;
import net.itsplace.domain.StampFragmentAdpter;
import net.itsplace.domain.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viewpagerindicator.TitlePageIndicator;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StampActivity extends FragmentActivity {
	
	protected static final String TAG = StampActivity.class.getSimpleName();
	
	StampFragmentAdpter mPagerAdapter;
    ViewPager  mViewPager;
    PageIndicator mIndicator;
   
	User user;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stamp_pager);
        
        
        MainApplication main = (MainApplication) getApplication();			
		if(main.isLogged()){
			user = main.getUser();
		}
        // Create our custom adapter to supply pages to the viewpager.
        mPagerAdapter = new StampFragmentAdpter(getSupportFragmentManager(), user, getApplicationContext());
        
       // new getStampedList().execute();
        
        // Set the indicator as the pageChangeListener
       // mViewPager.setOnPageChangeListener(mIndicator);
        
        // Initialize the indicator. We need some information here:
        // * What page do we start on.
        // * How many pages are there in total
        // * A callback to get page titles
        
     /*   mIndicator.init(0, mPagerAdapter.getCount(), mPagerAdapter);
		Resources res = getResources();
		Drawable prev = res.getDrawable(R.drawable.indicator_prev_arrow);
		Drawable next = res.getDrawable(R.drawable.indicator_next_arrow);
		mIndicator.setFocusedTextColor(new int[]{255, 0, 0});
		
		// Set images for previous and next arrows.
		mIndicator.setArrows(prev, next);
		
		mIndicator.setOnClickListener(new OnIndicatorClickListener());
	*/	
    }
    
    @Override
	protected void onStart() {		
    	Log.i(TAG, "onStart");
		super.onStart();
		
		
	}
    
    private void setStampList(List<PlaceStamp> stampList){
    	//mPagerAdapter
    	if(stampList.isEmpty()){
    		Log.i(TAG, "스탬프없음");
    		Toast.makeText(getApplicationContext(), "init`````````````````1", Toast.LENGTH_LONG).show();
    		
    	}else{
    		Log.i(TAG, "스탬프없음1");
    		//mPagerAdapter.addStampList(stampList);
    		Log.i(TAG, "스탬프없음2");
    		if(mPagerAdapter==null){
    			Log.i(TAG, "mPagerAdapter null");
    		}
        	mViewPager.setAdapter(mPagerAdapter);
        	Log.i(TAG, "스탬프없음3");
        	//mIndicator.init(0, mPagerAdapter.getCount(), mPagerAdapter);
        	
        	Toast.makeText(getApplicationContext(), "init`````````````````", Toast.LENGTH_LONG).show();
        	
     		Resources res = getResources();
     		Drawable prev = res.getDrawable(R.drawable.indicator_prev_arrow);
     		Drawable next = res.getDrawable(R.drawable.indicator_next_arrow);
     	//	mIndicator.setFocusedTextColor(new int[]{255, 0, 0});
     		
     		// Set images for previous and next arrows.
     	//	mIndicator.setArrows(prev, next);
     		
     	//	mIndicator.setOnClickListener(new OnIndicatorClickListener());
    	}
    	
 		
	
    }
      
    
    private class getStampedList extends AsyncTask<Void, Void, List<PlaceStamp>> {

        @Override
        protected void onPreExecute() {
            // before the network request begins, show a progress indicator
        	//showLoadingProgressDialog();
        	//mTestLayout.setVisibility(View.VISIBLE);
        	
        }

        @Override
        protected List<PlaceStamp> doInBackground(Void... params) {
			
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
							Log.i(TAG, placeStamp.getStampTitle());
							Log.i(TAG, placeStamp.getSaveDate().toGMTString());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					Log.i(TAG, "스탬 조회 오류");
				}
            } catch (Exception e) {
            	Log.e(TAG, e.getMessage()+ e.getLocalizedMessage(), e);
				Log.i(TAG, restClient.getErrorMessage(), e);
               
            }
			
		
		
			return placeStampList;
		}
		
		// when data set changes, you need to call proper method here.
		// do not call notifyDataSetChanged() in 'doInBackground()'.
		protected void onPostExecute(List<PlaceStamp> result){
			//Items.add(result.get(0).getName()+"kkkkkkkkkkkkkkkkk");
			//mAdapter.notifyDataSetChanged();
			//dismissProgressDialog();
			//mTestLayout.setVisibility(View.INVISIBLE);
			
				setStampList(result);
			
			
            // �۾��� �Ϸ� �� �� ����
            // dialog.dismiss();
            // super.onPostExecute(result);
		}
		/*
        private ProgressDialog dialog;
        
		protected void onPreExecute() {
            // �۾��� �����ϱ� �� ����
            dialog = new ProgressDialog(Main.this);
            dialog.setTitle("Indeterminate");
            dialog.setMessage("Please wait while loading...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);
            dialog.show();
            super.onPreExecute();
        }
		*/
    }
}