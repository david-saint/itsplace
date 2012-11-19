package com.mincoms.main;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gcm.GCMRegistrar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mincoms.book.rental.RentalBookFragmentAdapter;
import com.mincoms.domain.Authority;
import com.mincoms.domain.BookRental;
import com.mincoms.domain.UserInfo;
import com.mincoms.restful.RestClient;
import com.mincoms.restful.RestClient.RequestMethod;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;


public class MainActivity  extends SherlockFragmentActivity {
	protected static final String TAG = MainActivity.class.getSimpleName();
	private ActionBarMenu actionBarMenu;
	private MainApplication main;
	
	public static Activity topActivity;
	
	RentalBookFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    public static String PROJECT_ID = "357588365971";
    
    BroadcastReceiver mBroadcast = null;
    
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        actionBarMenu = new ActionBarMenu();
        setContentView(R.layout.viewpager_title);
    	
		mAdapter = new RentalBookFragmentAdapter(getSupportFragmentManager());	
		 Log.i(TAG,"GetBookRentalAsyncTask().execute()");
		 Log.i(TAG,"GetBookRentalAsyncTask().execute()");
		 Log.i(TAG,"GetBookRentalAsyncTask().execute()");
		 Log.i(TAG,"GetBookRentalAsyncTask().execute()");
        new GetBookRentalAsyncTask().execute();		
        //mAdapter = new RentalBookFragmentAdapter(getSupportFragmentManager(),  getApplicationContext());
        
        Log.d("test","푸쉬 메시지를 받습니다.");
        topActivity = this;
        
        
        IntentFilter pkgFilter = new IntentFilter();
        pkgFilter.addAction("com.mincoms.refresh");
        pkgFilter.addDataScheme("sample");
        mBroadcast = new BroadcastReceiver(){
			@Override
			public void onReceive(Context context, Intent intent) {
				 Log.d(TAG,"메인 리시버 작동");
				refreshRentalsBook();
			}
        };
        registerReceiver(mBroadcast, pkgFilter);
        
    	
		String PROJECT_ID = "357588365971";
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		AsyncTask<Void, Void, Void> registerTask;
		if(GCMRegistrar.getRegistrationId(this).equals("")){
			GCMRegistrar.register(this, PROJECT_ID);
		}else{
			if (GCMRegistrar.isRegisteredOnServer(this)) {
				
			} else {
              
                final Context context = this;
                registerTask = new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                            GCMRegistrar.unregister(context);                        
                        return null;
                    }

                };
                registerTask.execute(null, null, null);
            }
		}
        
	}
	public void refreshRentalsBook(){
   	 	new GetBookRentalAsyncTask().execute();
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		
		actionBarMenu.createMenu(menu);
		main = (MainApplication) getApplication();
		UserInfo user = main.getUser(); 
		if(actionBarMenu!=null && user!=null){
			  Log.i(TAG,"사용자권한:"+user.getAuthlevel()); 
			  Log.i(TAG,"WORKER:"+Authority.WORKER.ordinal()); 
			  Log.i(TAG,"ADMIN:"+Authority.ADMIN.ordinal()); 
			if(user.getAuthlevel() == Authority.WORKER.ordinal()){
				 Log.i(TAG,"사용자권한 리무브"); 
				actionBarMenu.remove(20); //도서등록
			}
		
		}
	    return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	return actionBarMenu.selectMenuEvent(getSherlock(), this, item);
    }
    @Override
	protected void onStart() {
    	 
		super.onStart();
		Log.i(TAG,"onStart");
		Log.i(TAG,"onStart");
		Log.i(TAG,"onStart");
		Log.i(TAG,"onStart");
	}
	@Override
	protected void onResume() {
		super.onResume();
		//refreshRentalsBook();
		 Log.i(TAG,"onResume");
		 Log.i(TAG,"onResume");
		 Log.i(TAG,"onResume");
		 Log.i(TAG,"onResume");
		
		

	}
	
    public void  showResult(List<BookRental> bookRentalList){
    	Log.i(TAG, "showResult 1");
    	if(mAdapter.getCount()>0){
    		Log.i(TAG, "기존거 삭제");
    		
    		mAdapter.removeBookRentalList();
    		mAdapter.notifyDataSetChanged();
    		mPager.removeAllViews();			
    		mPager.refreshDrawableState();						
    		mIndicator.notifyDataSetChanged();
    		
    	}
    
    	if(bookRentalList!=null && bookRentalList.size()>0){
    	
    		mAdapter.addBookRentalList(bookRentalList);
    		mPager = (ViewPager)findViewById(R.id.pager);  
    	    mPager.setAdapter(mAdapter);
    	   
            mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
            mIndicator.setViewPager(mPager);
            mAdapter.notifyDataSetChanged();
            mPager.refreshDrawableState();	
            mIndicator.notifyDataSetChanged();
    	}
    		
    	
    	 //mAdapter = new RentalBookFragmentAdapter(getSupportFragmentManager(),  getApplicationContext());
    	
    	
    /*	if(mAdapter.getCount()>0){
    		Log.i(TAG, "추가하기전  어댑터 카운터:"+mAdapter.getCount());
			mAdapter.removeBookRentalList();
			mAdapter.notifyDataSetChanged();
			mPager.setAdapter(mAdapter);
			mPager.removeAllViews();			
			mPager.refreshDrawableState();						
    		mIndicator.notifyDataSetChanged();
    		mIndicator.setViewPager(mPager);
		}
    	*/
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
   
 
    public class GetBookRentalAsyncTask extends AsyncTask<BookRental, Void, RestClient> {

        @Override
        protected void onPreExecute() {
        }


		@Override
		protected RestClient doInBackground(BookRental... params) {
			RestClient restClient = null;
			try {
				String url = getString(R.string.base_uri) + "/book/rentals";
				restClient = new RestClient(url);
				restClient.AddParam("decorator","exception");
				restClient.AddHeader("Accept", "application/json");
				restClient.Execute(RequestMethod.POST);
				
			} catch (Exception e) {
				e.printStackTrace();				
			}
			return restClient;
		}
		protected void onPostExecute(RestClient restClient) {
			List<BookRental> bookRentalList = new ArrayList();
			int statusCode = restClient.getResponseCode();
			String result = restClient.getResponse();
			Log.i(TAG,"RESULT:" + result);
			Log.i(TAG,"statusCode:" + statusCode);
			
			if(statusCode==200){
				JSONObject jsonResponse = restClient.getJsonObject();
//				String status = jsonResponse.getString("status");
				String status =restClient.getJson("status");
				if(status.equals("SUCCESS")){
					try {
						JSONArray jsonArray;
						jsonArray = jsonResponse.getJSONArray("result");
						GsonBuilder gsonb = new GsonBuilder();
						DateDeserializer ds = new DateDeserializer();
						gsonb.registerTypeAdapter(Date.class, ds);
						Gson gson = gsonb.create();
						
						for (int i = 0; i < jsonArray.length(); i++) {
							BookRental bookRental = gson.fromJson(jsonArray.getString(i), BookRental.class);
							bookRentalList.add(bookRental);
							Log.i(TAG,"book"+bookRental.getBookInfo().getTitle());
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					Log.i(TAG, "result failed:"+statusCode+result);				
					Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
				}
			}else{
				Log.i(TAG, "result exception:"+statusCode+result);				
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
				//ExceptionReport e = new ExceptionReport();
				//e.SendErrorMail(getApplicationContext(), "ddddddd");
			}
			showResult(bookRentalList);
		}
    }
	
    

	
}
