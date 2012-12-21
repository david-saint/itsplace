package itsplace.net;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import itsplace.library.restful.AsyncClient;
import itsplace.net.common.DateDeserializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceListAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class PlaceListFragment extends ListFragment {
	private  AsyncHttpClient client = AsyncClient.getInstance();
	protected static final String TAG = PlaceListFragment.class.getSimpleName();
	
	private int pageSize = 10;
	private Boolean isLoading = true;
	private int currentPage = 1;
	PlaceListAdapter adapter = null;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new PlaceListAdapter(getActivity());
	
		GetPlaceListAsyncTask();
		
		
	}

	
	private void GetPlaceListAsyncTask(){
		final String url = getString(R.string.base_uri) + "/search/placeList";
		RequestParams params = new RequestParams();
	
		params.put("currentPage", Integer.toString(currentPage));
		params.put("pageSize", "10");
		params.put("pageGroupSize", "0");
		params.put("searchWord", "");
	    	client.post(url,  new AsyncHttpResponseHandler() {
	    		@Override
		  	    public void onStart() {
		  		  	Log.i(TAG,"onStart");
		  	    }
	
		  	    @Override
		  	    public void onSuccess(String response) {
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
							Log.i(TAG, place.getFname());
							Log.i(TAG, place.getSaveDate().toGMTString());
						}
						 
					} catch (JSONException e) {
						// TODO Auto-generated catch block
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
	  	    	 Log.i(TAG,"onFinish");
	  	     }
	  	 });
	}
	private void refresh(List<Place> placeList) {
	    if (placeList.isEmpty()) {
	    	Toast.makeText(getActivity(), "검색 결과가 없습니다", Toast.LENGTH_LONG).show();
	    }else{
	    	//adapter.addStatesListAdapter(placeList);
	    	//getListView().setAdapter(adapter);
	    	setListAdapter(adapter);
	    	
	    }
	    
		isLoading = false;
	}


	
}
