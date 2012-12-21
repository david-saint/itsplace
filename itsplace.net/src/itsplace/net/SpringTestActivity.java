package itsplace.net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



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
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import itsplace.net.R;
public class SpringTestActivity extends ListActivity{
	public String TAG = "ListMore";
	private TextView mStatusTextView;
	private ListView mListView;
	
	public int itemNum;
	public int totalListNum;
	public ArrayList<String> Items;
	public ArrayAdapter<String> mAdapter;
	public TextView mTestText;
	private StatesListAdapter adapter;
	public LinearLayout  mTestLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new StatesListAdapter(this);
        mTestLayout = (LinearLayout) View.inflate(this, R.layout.loading_list, null);
        mTestLayout.setVisibility(View.INVISIBLE);
      //  super.setContentView(mTestLayout);
      //  setContentView(R.layout.list);
        //mListView = (ListView)
     //   mStatusTextView = (TextView) findViewById(R.id.status);
     //   mStatusTextView.setText("idle");
      
//        MyAdapter mAdapter = new MyAdapter(this);
        
        Items = new ArrayList<String>();
        totalListNum = mStrings.length;
      //  Log.v(TAG, "Before " + totalListNum);
        

        mTestLayout = (LinearLayout) View.inflate(this, R.layout.loading_list, null);
        mTestLayout.setVisibility(View.INVISIBLE);
        
        getListView().addFooterView(mTestLayout);
        
      //  new DownloadStatesTask().execute();
        
        
        getListView().setOnScrollListener(new OnScrollListener() {
			
			// @Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			// @Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				Log.v(TAG, "onScroll");
			//	Log.v(TAG, "firstVisibleItem = " + firstVisibleItem);
			//	Log.v(TAG, "visibleItemCount = " + visibleItemCount);
			//	Log.v(TAG, "totalItemCount = " + totalItemCount);
				//if(!isLoading){
					if ((firstVisibleItem + visibleItemCount) == totalItemCount && totalItemCount !=0) {
					//	Log.v(TAG, "IN, totalItemCount = " + totalItemCount);
						
						if (totalListNum > itemNum) {
							mTestLayout.setVisibility(View.VISIBLE);
							new getMoreItems().execute();
						} else {
							mTestLayout.setVisibility(View.INVISIBLE);
						}
						
					//	Log.v(TAG, "IN END");
					}
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
    private void refreshStates(List<State> states) {
        if (states == null) {
            return;
        }
        adapter.addStatesListAdapter(states);
        //StatesListAdapter adapter = new StatesListAdapter(this, states);
        if(getListView().getAdapter()==null){
        	getListView().setAdapter(adapter);
        }
        
    }
    
    private class getMoreItems extends AsyncTask<Void, Void, List<State>> {

        @Override
        protected void onPreExecute() {
            // before the network request begins, show a progress indicator          
        }

        @Override
        protected List<State> doInBackground(Void... params) {
			try {
                // The URL for making the GET request
                final String url = getString(R.string.base_uri) + "/states";
                
            //    Log.i(TAG,"유알엘i:"+url);
                
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
                ResponseEntity<State[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, State[].class);

                // convert the array to a list and return it
               
                return Arrays.asList(responseEntity.getBody());
            } catch (Exception e) {
              //  Log.e(TAG, e.getMessage(), e);
            }

		
			return null;
		}
		
		// when data set changes, you need to call proper method here.
		// do not call notifyDataSetChanged() in 'doInBackground()'.
		protected void onPostExecute(List<State> result){
			//Items.add(result.get(0).getName()+"kkkkkkkkkkkkkkkkk");
			//mAdapter.notifyDataSetChanged();
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
    
    public String[] mStrings = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh",
            "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan",
            "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh",
            "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan",
            "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh",
            "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan",
            "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh",
            "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan",
            "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh",
            "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan",
            "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh",
            "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan",
            "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh",
            "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan",
            "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh",
            "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan",
            "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh",
            "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan",
            "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago"
            };
    
   
}