package itsplace.net;

import itsplace.net.util.L;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.itsplace.domain.FranchiserMember;
import net.itsplace.domain.Stamp;
import net.itsplace.domain.StampFragmentAdpter;
import net.itsplace.domain.User;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


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
    ViewPagerIndicator mIndicator;
   
	User user;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stamp_activity);
        
        
        MainApplication main = (MainApplication) getApplication();			
		if(main.isLogged()){
			user = main.getUser();
		}
        // Create our custom adapter to supply pages to the viewpager.
        mPagerAdapter = new StampFragmentAdpter(getSupportFragmentManager(), user, getApplicationContext());
        
        new getStampedList().execute();
        
        mViewPager = (ViewPager)findViewById(R.id.pager);
      //  mViewPager.setAdapter(mPagerAdapter);
        
        // Start at a custom position
        mViewPager.setCurrentItem(0);
        
        // Find the indicator from the layout
        mIndicator = (ViewPagerIndicator)findViewById(R.id.indicator);
		
        // Set the indicator as the pageChangeListener
        mViewPager.setOnPageChangeListener(mIndicator);
        
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
		super.onStart();
		
		
	}
    
    private void setStampList(List<Stamp> stampList){
    	//mPagerAdapter
    	mPagerAdapter.addStampList(stampList);
    	mViewPager.setAdapter(mPagerAdapter);
    	mIndicator.init(0, mPagerAdapter.getCount(), mPagerAdapter);
 		Resources res = getResources();
 		Drawable prev = res.getDrawable(R.drawable.indicator_prev_arrow);
 		Drawable next = res.getDrawable(R.drawable.indicator_next_arrow);
 		mIndicator.setFocusedTextColor(new int[]{255, 0, 0});
 		
 		// Set images for previous and next arrows.
 		mIndicator.setArrows(prev, next);
 		
 		mIndicator.setOnClickListener(new OnIndicatorClickListener());
 		
	
    }
    
    class OnIndicatorClickListener implements ViewPagerIndicator.OnClickListener{
		@Override
		public void onCurrentClicked(View v) {
			Toast.makeText(StampActivity.this, "Hello", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void onNextClicked(View v) {
			mViewPager.setCurrentItem(Math.min(mPagerAdapter.getCount() - 1, mIndicator.getCurrentPosition() + 1));
			Toast.makeText(StampActivity.this, "Hellon", Toast.LENGTH_SHORT).show();	
		}

		@Override
		public void onPreviousClicked(View v) {
			mViewPager.setCurrentItem(Math.max(0, mIndicator.getCurrentPosition() - 1));
		}
    	
    }
    
   
    
    
    private class getStampedList extends AsyncTask<Void, Void, List<Stamp>> {

        @Override
        protected void onPreExecute() {
            // before the network request begins, show a progress indicator
        	//showLoadingProgressDialog();
        	//mTestLayout.setVisibility(View.VISIBLE);
        	
        }

        @Override
        protected List<Stamp> doInBackground(Void... params) {
			try {
                // The URL for making the GET request
                final String url = getString(R.string.base_uri)+"/stamp/myStampJson?email=" + user.getEmail(); 
                		
                //getString(R.string.base_uri)
                Log.i(TAG,"URL:"+url);
                
                // Set the Accept header for "application/json"
                HttpHeaders requestHeaders = new HttpHeaders();
                List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
                acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
                requestHeaders.setAccept(acceptableMediaTypes);

                // Populate the headers in an HttpEntity object to use for the
                // request
                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

                // Create a new RestTemplate instance
                RestTemplate restTemplate = new RestTemplate();

                // Perform the HTTP GET request
                ResponseEntity<Stamp[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Stamp[].class);

                // convert the array to a list and return it
               
                return Arrays.asList(responseEntity.getBody());
            } catch (Exception e) {
                L.e(TAG, e.getMessage(), e);
               
            }

		
			return null;
		}
		
		// when data set changes, you need to call proper method here.
		// do not call notifyDataSetChanged() in 'doInBackground()'.
		protected void onPostExecute(List<Stamp> result){
			//Items.add(result.get(0).getName()+"kkkkkkkkkkkkkkkkk");
			//mAdapter.notifyDataSetChanged();
			//dismissProgressDialog();
			//mTestLayout.setVisibility(View.INVISIBLE);
			if(result != null){
				//refreshStates(result);
				setStampList(result);
			}else{
				 Toast.makeText(getApplicationContext(), "스탬프가 없습니다", Toast.LENGTH_LONG).show();
			}
			
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