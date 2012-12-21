package itsplace.net;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import itsplace.library.restful.AsyncClient;
import itsplace.net.common.ActionBarMenu;
import itsplace.net.common.DateDeserializer;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.RequestParams;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


public class MainActivity extends BaseActivity {
	
	private  AsyncHttpClient client = AsyncClient.getInstance();
	protected static final String TAG = MainActivity.class.getSimpleName();
	private ListView listView;

	private int pageSize = 10;
	private Boolean isLoading = true;
	private int currentPage = 1;
	PlaceListAdapter adapter = null;
	
	public LinearLayout  lodingLayout;
	
	public MainActivity(){
		super(R.string.app_name);
	}

	 
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
       
        //컨텐츠슬라이딩
        setSlidingActionBarEnabled(false);
       
        listView = (ListView)findViewById(R.id.listView);
        adapter = new PlaceListAdapter(this);
        GetPlaceListAsyncTask();
   
        lodingLayout = (LinearLayout) listView.inflate(this, R.layout.loading_list, null);
        lodingLayout.setVisibility(View.INVISIBLE);
        

        listView.addFooterView(lodingLayout);
        
        listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				//Log.i(TAG, "firstVisibleItem = " + firstVisibleItem);
				//Log.i(TAG, "visibleItemCount = " + visibleItemCount);
			//	Log.i(TAG, "totalItemCount = " + totalItemCount);
				if(!isLoading){
					int count = totalItemCount - visibleItemCount;
						if (firstVisibleItem >= count && totalItemCount !=0 && isLoading==false) {
							Log.v(TAG, "onScroll");
							Log.v(TAG, "lodingLayout.getVisibility(): "+lodingLayout.getVisibility()+"");
							Log.v(TAG, "IN, totalItemCount = " + totalItemCount + " firstVisibleItem:"+firstVisibleItem+ " visibleItemCount:"+visibleItemCount);
								isLoading = true;
								
								GetPlaceListAsyncTask();
								
							} else {
								Log.v(TAG, "lodingLayout.getVisibility(): "+lodingLayout.getVisibility()+"");
							
							}
							
							Log.v(TAG, "스크롤  END");
					
				}
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
        });
       /* 
        getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, new PlaceListFragment())
		.commit();
        */
        
      /*  final String url = getString(R.string.base_uri) + "/search/test";
        client.post(url,  new AsyncHttpResponseHandler() {
    		@Override
    	     public void onStart() {
    		  	//Log.i(TAG,"onStart");
    	     }

    	     @Override
    	     public void onSuccess(String response) {
    	    	
    	    	 try {
					JSONObject json =  new JSONObject(response);
					String result = json.get("result").toString();
					String status = json.get("status").toString();
					 Log.i(TAG,"onSuccess:"+result+status);
					
					 
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	     }
    	 
    	     @Override
    	     public void onFailure(Throwable e, String response) {
    	    	// Log.i(TAG,"onFailure"+response);
    	    	 Log.i(TAG,e.getMessage());
    	     }

    	     @Override
    	     public void onFinish() {
    	    	// Log.i(TAG,"onFinish");
    	     }
    	 });*/
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {        
		MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.search_view, menu);
        
       
        menu.add(0,100,0,"Refresh")//그룹,아이디
        .setIcon( R.drawable.ic_refresh)
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
	}
	
	private void GetPlaceListAsyncTask(){
		final String url = getString(R.string.base_uri) + "/search/placeList";
		RequestParams params = new RequestParams();
	
		params.put("currentPage", Integer.toString(currentPage));
		params.put("pageSize", "10");
		params.put("pageGroupSize", "0");
		params.put("searchWord", "");
	    	client.post(url, params, new AsyncHttpResponseHandler() {
	    		@Override
		  	    public void onStart() {
		  		  	Log.i(TAG,"onStart place list");
		  		  	lodingLayout.setVisibility(View.VISIBLE);
		  	    }
	
		  	    @Override
		  	    public void onSuccess(String response) {
		  	  	Log.i(TAG,"onSuccess place list");
		  	    	List<Place> placeList = new ArrayList();
		  	    	try {
						JSONObject jsonResponse = new JSONObject(response);
						JSONArray placesJson = jsonResponse.getJSONArray("result");
						GsonBuilder gsonb = new GsonBuilder();
						DateDeserializer ds = new DateDeserializer();
						gsonb.registerTypeAdapter(Date.class, ds);
						Gson gson = gsonb.create();
						for (int i = 0; i < placesJson.length(); i++) {
							Place place = gson.fromJson(placesJson.getString(i), Place.class);
							placeList.add(place);
							Log.i(TAG, "place fname:"+place.getFname());
							Log.i(TAG, place.getSaveDate().toGMTString());
						}
						 
					} catch (JSONException e) {
						Log.i(TAG,"GetPlaceListAsyncTask 실패");
						e.printStackTrace();
					}
		  	    	
		  	    	refresh(placeList);
	  	     }
	  	 
	  	     @Override
	  	     public void onFailure(Throwable e, String response) {
	  	    	 Log.i(TAG,"onFailure"+response);
	  	    	 Log.i(TAG,e.getMessage());
	  	     }

	  	     @Override
	  	     public void onFinish() {
	  	    	 Log.i(TAG,"onFinish place list");
	  	    	 lodingLayout.setVisibility(View.INVISIBLE);
	  	     }
	  	 });
	}
	private void refresh(List<Place> placeList) {
	    if (placeList.isEmpty()) {
	    	Toast.makeText(this, "검색 결과가 없습니다", Toast.LENGTH_LONG).show();
	    }else{
	    	currentPage++;
	    	//adapter.addStatesListAdapter(placeList);
	    	//getListView().setAdapter(adapter);
	    	 Log.i(TAG,"어댑트 삽입");
	    	adapter.addStatesListAdapter(placeList);
	    	isLoading = false;
	    	listView.setAdapter(adapter);
	    }
	    
		
	}
	   
}
 
 
