package itsplace.net;

import itsplace.net.common.ActionBarMenu;
import itsplace.net.common.DateDeserializer;
import itsplace.net.connection.RestClient;
import itsplace.net.connection.RestClient.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.itsplace.domain.PlaceListAdapter;
import net.itsplace.domain.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PlaceActivity  extends SherlockListActivity{
	protected static final String TAG = PlaceActivity.class.getSimpleName();
	private ActionBarMenu actionBarMenu;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		actionBarMenu.createMenu(menu);
	    return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      
    	return actionBarMenu.selectMenuEvent(this, item);
    }
	
	private TextView mStatusTextView;
	private ListView mListView;
	
	public int itemNum;
	public int totalListNum;
	public ArrayList<String> Items;
	public ArrayAdapter<String> mAdapter;
	public TextView mTestText;
	private PlaceListAdapter adapter;
	public LinearLayout  mTestLayout;
	private int pageSize = 10;
	private Boolean isLoading = true;
	private int currentPage = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBarMenu = new ActionBarMenu();
        adapter = new PlaceListAdapter(this);
        
        
        mTestLayout = (LinearLayout) View.inflate(this, R.layout.mytext, null);
        mTestLayout.setVisibility(View.INVISIBLE);
      //  super.setContentView(mTestLayout);
      //  setContentView(R.layout.list);
        //mListView = (ListView)
     //   mStatusTextView = (TextView) findViewById(R.id.status);
     //   mStatusTextView.setText("idle");
      
//        MyAdapter mAdapter = new MyAdapter(this);
        
        Items = new ArrayList<String>();    
        totalListNum =10;
        Log.v(TAG, "Before:" + totalListNum);
        

        this.getListView().addFooterView(mTestLayout);
        
      //  new DownloadStatesTask().execute();
        
		new GetPlaceListAsyncTask().execute();
		
        getListView().setOnScrollListener(new OnScrollListener() {
			
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Log.v(TAG,"onScrollStateChangedonScrollStateChangedonScrollStateChanged");
			}
			
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int count = totalItemCount - visibleItemCount;
				Log.v(TAG, "firstVisibleItem = " + firstVisibleItem);
				Log.v(TAG, "visibleItemCount = " + visibleItemCount);
				Log.v(TAG, "totalItemCount = " + totalItemCount);
				//if(!isLoading){
					if (firstVisibleItem >= count && totalItemCount !=0 && isLoading==false) {
						Log.v(TAG, "onScroll");
						Log.v(TAG, "mTestLayout.getVisibility(): "+mTestLayout.getVisibility()+"");
						Log.v(TAG, "IN, totalItemCount = " + totalItemCount + " firstVisibleItem:"+firstVisibleItem+ " visibleItemCount:"+visibleItemCount);
							//mTestLayout.setVisibility(View.VISIBLE);
							isLoading = true;
							
							new GetPlaceListAsyncTask().execute();
							currentPage++;
						} else {
						//	mTestLayout.setVisibility(View.INVISIBLE);
							Log.v(TAG, "mTestLayout.getVisibility(): "+mTestLayout.getVisibility()+"");
						
						}
						
						Log.v(TAG, "IN END");
					
				//}
			
			}
		});        
    }
    
   
    
    @Override
    public void onStart() {
        super.onStart();
       //액티비티 시작할때 
        Log.i(TAG,"액티비티 onStart");
        
    }

   
    private void refreshStates(List<Place> placeList) {
        if (placeList.isEmpty()) {
        	Toast.makeText(this, "검색 결과가 없습니다", Toast.LENGTH_LONG).show();
        }else{
        	
        	adapter.addStatesListAdapter(placeList);
        	//StatesListAdapter adapter = new StatesListAdapter(this, states);
        	if(getListView().getAdapter()==null){
        		getListView().setAdapter(adapter);
        	}
        }
        
    	isLoading = false;
    }

    private class GetPlaceListAsyncTask extends AsyncTask<Void, Void, List<Place>> {

        @Override
        protected void onPreExecute() {
            // before the network request begins, show a progress indicator
        	//showLoadingProgressDialog();
        	mTestLayout.setVisibility(View.VISIBLE);
        	
        }

        @Override
        protected  List<Place> doInBackground(Void... params) {
        	final String url = getString(R.string.base_uri) + "/search/placeList";
			Log.i(TAG, url);
			
			RestClient restClient = new RestClient(url);
			 List<Place> placeList = new ArrayList();
			try {
				
//                // The URL for making the GET request
//                final String url = getString(R.string.base_uri)+"/partner/franchiserListJson/web/동인/"+currentPage+"/10/10";
//                //getString(R.string.base_uri)
//                Log.i(TAG,"유알엘i:"+url);
//                
//                // Set the Accept header for "application/json"
//                HttpHeaders requestHeaders = new HttpHeaders();
//                List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
//                acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
//                requestHeaders.setAccept(acceptableMediaTypes);
//
//                // Populate the headers in an HttpEntity object to use for the
//                // request
//                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
//
//                // Create a new RestTemplate instance
//                RestTemplate restTemplate = new RestTemplate();
//
//                // Perform the HTTP GET request
//                ResponseEntity<FranchiserMember[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, FranchiserMember[].class);
//
//                // convert the array to a list and return it
//               
//                return Arrays.asList(responseEntity.getBody());
//				@RequestParam(required=false, defaultValue="1") Integer currentPage,
//					@RequestParam(required=false, defaultValue="10") Integer pageSize ,
//					@RequestParam(required=false, defaultValue="10") Integer pageGroupSize ,
//					@RequestParam(required=false, defaultValue="") String searchWord 
//					){
				restClient.AddParam("currentPage", Integer.toString(currentPage));
				restClient.AddParam("pageSize", "10");
				restClient.AddParam("pageGroupSize", "0");
				restClient.AddParam("searchWord", "");
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
							Place place = gson.fromJson(placesJson.getString(i), Place.class);
							placeList.add(place);
							Log.i(TAG, place.getFname());
							Log.i(TAG, place.getSaveDate().toGMTString());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					Log.i(TAG, "가맹점 조회 오류");
				}
            } catch (Exception e) {
            	Log.e(TAG, e.getMessage()+ e.getLocalizedMessage(), e);
				Log.i(TAG, restClient.getErrorMessage(), e);
               
            }
			
		
		
			return placeList;
		}
		
		// when data set changes, you need to call proper method here.
		// do not call notifyDataSetChanged() in 'doInBackground()'.
		protected void onPostExecute(List<Place> result){
			//Items.add(result.get(0).getName()+"kkkkkkkkkkkkkkkkk");
			//mAdapter.notifyDataSetChanged();
			//dismissProgressDialog();
			mTestLayout.setVisibility(View.INVISIBLE);
			
			refreshStates(result);
		
			
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