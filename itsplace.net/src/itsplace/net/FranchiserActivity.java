package itsplace.net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import net.itsplace.domain.FranchiserMember;
import net.itsplace.domain.FranchiserMemberListAdapter;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



import itsplace.net.R;
import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import itsplace.net.R;
import itsplace.net.common.AbstractAsyncListActivity;
import itsplace.net.util.L;

public class FranchiserActivity extends AbstractAsyncListActivity{
	protected static final String TAG = FranchiserActivity.class.getSimpleName();
	 
	
	private TextView mStatusTextView;
	private ListView mListView;
	
	public int itemNum;
	public int totalListNum;
	public ArrayList<String> Items;
	public ArrayAdapter<String> mAdapter;
	public TextView mTestText;
	private FranchiserMemberListAdapter adapter;
	public LinearLayout  mTestLayout;
	private int pageSize = 10;
	private Boolean isLoading = true;
	private int currentPage = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FranchiserMemberListAdapter(this);
        
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
        

        getListView().addFooterView(mTestLayout);
        
      //  new DownloadStatesTask().execute();
        
		new getMoreItems().execute();
		
        getListView().setOnScrollListener(new OnScrollListener() {
			
			// @Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				Log.v(TAG,"onScrollStateChangedonScrollStateChangedonScrollStateChanged");
			}
			
			// @Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
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
							
							new getMoreItems().execute();
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

        // when this activity starts, initiate an asynchronous HTTP GET request
     ///   new DownloadStatesTask().execute();
    }

    // ***************************************
    // Private methods
    // ***************************************
    private void refreshStates(List<FranchiserMember> franchiserMembers) {
        if (franchiserMembers == null) {
            return;
        }
        adapter.addStatesListAdapter(franchiserMembers);
        //StatesListAdapter adapter = new StatesListAdapter(this, states);
        if(getListView().getAdapter()==null){
        	getListView().setAdapter(adapter);
        }
        
    	isLoading = false;
    }

    private class getMoreItems extends AsyncTask<Void, Void, List<FranchiserMember>> {

        @Override
        protected void onPreExecute() {
            // before the network request begins, show a progress indicator
        	//showLoadingProgressDialog();
        	mTestLayout.setVisibility(View.VISIBLE);
        	
        }

        @Override
        protected List<FranchiserMember> doInBackground(Void... params) {
			try {
                // The URL for making the GET request
                final String url = getString(R.string.base_uri)+"/partner/franchiserListJson/web/동인/"+currentPage+"/10/10";
                //getString(R.string.base_uri)
                Log.i(TAG,"유알엘i:"+url);
                
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
                ResponseEntity<FranchiserMember[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, FranchiserMember[].class);

                // convert the array to a list and return it
               
                return Arrays.asList(responseEntity.getBody());
            } catch (Exception e) {
                L.e(TAG, e.getMessage(), e);
               
            }

		
			return null;
		}
		
		// when data set changes, you need to call proper method here.
		// do not call notifyDataSetChanged() in 'doInBackground()'.
		protected void onPostExecute(List<FranchiserMember> result){
			//Items.add(result.get(0).getName()+"kkkkkkkkkkkkkkkkk");
			//mAdapter.notifyDataSetChanged();
			//dismissProgressDialog();
			mTestLayout.setVisibility(View.INVISIBLE);
			if(result != null){
				refreshStates(result);
			}else{
				 Toast.makeText(getApplicationContext(), "결과가 없습니다", Toast.LENGTH_LONG).show();
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