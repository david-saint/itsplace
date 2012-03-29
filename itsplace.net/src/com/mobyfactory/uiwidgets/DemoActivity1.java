package com.mobyfactory.uiwidgets;
import java.util.ArrayList;



import itsplace.net.R;
import android.app.Activity;
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
public class DemoActivity1 extends Activity{
	public String TAG = "ListMore";
	private TextView mStatusTextView;
	private ListView mListView;
	
	public int itemNum;
	public int totalListNum;
	public ArrayList<String> Items;
	public ArrayAdapter<String> mAdapter;
	public TextView mTestText;
	public LinearLayout mTestLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.morelist);
        
        mStatusTextView = (TextView) findViewById(R.id.status);
        mStatusTextView.setText("idle");
      
//        MyAdapter mAdapter = new MyAdapter(this);
        
        Items = new ArrayList<String>();
        totalListNum = mStrings.length;
        Log.v(TAG, "Before " + totalListNum);
        
        if (totalListNum > 10) {
	        for (itemNum = 0; itemNum < 10; itemNum++ ) {
	        	Items.add(itemNum+")"+mStrings[itemNum]);
	        }
        } else {
        	for (itemNum = 0; itemNum < totalListNum; itemNum++ ) {
	        	Items.add(itemNum+")"+mStrings[itemNum]);
	        }
        }
        
        
        mAdapter = new ArrayAdapter<String>
        				(this, android.R.layout.simple_list_item_1, Items);
        
        mListView = (ListView) findViewById(R.id.list);
        mTestLayout = (LinearLayout) View.inflate(this, R.layout.mytext, null);
        mTestLayout.setVisibility(View.INVISIBLE);
        mListView.addFooterView(mTestLayout);
        mListView.setAdapter(mAdapter);

        mListView.setOnScrollListener(new OnScrollListener() {
			
			// @Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			// @Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				Log.v(TAG, "onScroll");
				Log.v(TAG, "firstVisibleItem = " + firstVisibleItem);
				Log.v(TAG, "visibleItemCount = " + visibleItemCount);
				Log.v(TAG, "totalItemCount = " + totalItemCount);
				//if(!isLoading){
					if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
						Log.v(TAG, "IN, totalItemCount = " + totalItemCount);
						
						if (totalListNum > itemNum) {
							mTestLayout.setVisibility(View.VISIBLE);
							new getMoreItems().execute(Items);
						} else {
							mTestLayout.setVisibility(View.INVISIBLE);
						}
						
						Log.v(TAG, "IN END");
					}
				//}
			
			}
		});        
    }
    
    private class getMoreItems extends AsyncTask<ArrayList<String>, Integer, Long> {

		@Override
		protected Long doInBackground(ArrayList<String>... params) {
			// TODO Auto-generated method stub
			Long result = 0L;
		
			if (totalListNum >= itemNum + 10) {
				int tmpEnd = itemNum + 10;
				
				Log.v(TAG, "if > itemNum = " + itemNum);
				for ( ; itemNum < tmpEnd; itemNum++) {
					Items.add(itemNum+")"+mStrings[itemNum]);
				}
				Log.v(TAG, "why");
			} else {
				Log.v(TAG, "else ");
				for ( ; itemNum < totalListNum; itemNum++) {
					Items.add(itemNum+")"+mStrings[itemNum]);
				}
			}
			Log.v(TAG, "changed!!");
			
			//try { Thread.sleep(3000); } catch (Exception e) { ; }	// 3�� ����. ��Ʈ��ũ �۾� ���� �ùķ��̼�
			// dialog.dismiss();

			return null;
		}
		
		// when data set changes, you need to call proper method here.
		// do not call notifyDataSetChanged() in 'doInBackground()'.
		protected void onPostExecute(Long result){
			mAdapter.notifyDataSetChanged();
	
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
            "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago" };
}