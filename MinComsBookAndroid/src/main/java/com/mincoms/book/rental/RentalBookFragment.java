package com.mincoms.book.rental;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;



import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.actionbarsherlock.app.SherlockListFragment;

import com.fedorvlasov.lazylist.ImageLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mincoms.domain.BookInfo;
import com.mincoms.domain.BookRental;
import com.mincoms.main.BookRentalResultActivity;
import com.mincoms.main.MainActivity;
import com.mincoms.main.R;
import com.mincoms.main.BookRentalResultActivity.DayOnItemSelectedListener;
import com.mincoms.restful.RestClient;
import com.mincoms.restful.RestClient.RequestMethod;
import com.mincoms.util.DateUtil;
import com.mincoms.util.DurationFromNow;


public class RentalBookFragment extends Fragment{
	private View v;
	private BookRental bookRental = null;
	Button btnReturn ;
	public RentalBookFragment() {}
	protected static final String TAG = RentalBookFragment.class.getSimpleName();
	
	
	public  RentalBookFragment (BookRental bookRental) {
		Log.i(TAG,"프래그먼트  생성자 콜 ");
		RentalBookFragment fragment = new RentalBookFragment();
		this.bookRental = bookRental;
	   
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//bookRental = (BookRental) getArguments().getSerializable("bookRental");
		//  Log.i(TAG,"프래그맨트 onCreate"+bookRental.getBookInfo().getTitle());
		if (savedInstanceState != null) {
			
	           // this.fid = savedInstanceState.getInt(KEY_FID);
	         //   this.stampid = savedInstanceState.getInt(KEY_STAPMID);
	           // this.email = savedInstanceState.getString(KEY_EMAIL);
	           // Log.i(TAG,"프래그먼트 oncreate fid:" + fid );
	          //  Log.i(TAG,"프래그먼트 oncreate stampid:" + stampid );
	          //  Log.i(TAG,"프래그먼트 oncreate email:" + email );
	    }
	} 
	@Override
	public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	       // outState.putInt(KEY_FID, fid );
	      //  outState.putInt(KEY_STAPMID, stampid);
	      //  outState.putString(KEY_EMAIL, email);
	       // Log.i(TAG,"프래그먼트 onSaveInstanceState fid:" + fid );
           
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	 v = inflater.inflate(R.layout.fragment_rentalbook, container, false);
    	if(bookRental == null){
    		Log.i(TAG," 북렌탈 널");
    	}else{

    	       Log.i(TAG,"프래그맨트 내부 oncreateview"+bookRental.getBookInfo().getTitle());
    	       Log.i(TAG,"프래그맨트 내부 oncreateview"+bookRental.getBookInfo().getTitle());
    	       Log.i(TAG,"프래그맨트 내부 oncreateview"+bookRental.getBookInfo().getTitle());
    	       Log.i(TAG,"프래그맨트 내부 oncreateview"+bookRental.getBookInfo().getTitle());
    	       LinearLayout  booklinear= (LinearLayout) v.findViewById(R.id.booklinear);
    	        booklinear.setOnClickListener(new View.OnClickListener() {
    	            public void onClick(View v) {
    	            	showDialog();
    	            }
    	        });
    	        
    	        //TextView title = (TextView) v.findViewById(R.id.title);
    	       // title.setText(bookRental.getBookInfo().getTitle());
    	        
    	        TextView endDate = (TextView) v.findViewById(R.id.endDate);
    	        long tetsd = ((bookRental.getEndDate().getTime() - new Date().getTime()) /1000/60/60/24);
    	        int endDay = (int) ((bookRental.getEndDate().getTime() - new Date().getTime()) /1000/60/60/24);
    	        String returnMessage = "";
    	        if(endDay<0){ 
    	        	returnMessage = "반납일 "+ -endDay + "일 지났습니다";
    	        }else{
    	        	int result = DateUtil.DateCompareToday(bookRental.getEndDate());
    	        	if(result==0){
    	        		returnMessage = "오늘은 반납일입니다";
    	        	
    	        	}else{
    	        		returnMessage = endDay+1 + "일 남았습니다" ;
    	        	}
    			/*}else if(endDay == 0){
    	        	returnMessage = "오늘은 반납일입니다"+endDay+":"+tetsd;
    	        }else if(endDay >0 && endDay <1){
    	        	
    	        	returnMessage = "1일 남았습니다"+endDay+":"+tetsd;
    	        }else{
    	        	endDay = Math.round(endDay)+1 ;
    	        	returnMessage = endDay + "일 남았습니다"+endDay+":"+tetsd;*/
    	        }
    	        endDate.setText(returnMessage);
    	      
    	        ImageLoader imageLoader = new ImageLoader(getActivity().getApplicationContext()); 
    	        ImageView imageView = (ImageView) v.findViewById(R.id.thumbnail);
    			imageLoader.DisplayImage( bookRental.getBookInfo().getThumbnail(), imageView);

    			 btnReturn = (Button)v.findViewById(R.id.btnRental);
    		   //  btnReturn.setOnClickListener(onBtnClickListener);
    			
    			//btnReturn.setOnClickListener(onBtnClickListener);
    			
    	}
       
       
    
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       
    }
    public void showDialog(){
		FragmentTransaction ft = getFragmentManager().beginTransaction();
	    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
	    if (prev != null) {
	    	ft.remove(prev);
	    }
	    ft.addToBackStack(null);

	    DialogFragment newFragment = ReturnOrExtendFragment.newInstance(1,bookRental); // no title
	    newFragment.show(ft, "dialog");
	  
	    
	}
 
	 
	
    public static class ReturnOrExtendFragment extends com.actionbarsherlock.app.SherlockDialogFragment {
        int mNum;
        int extendDay;
        String extendReason;
        BookRental bookRental;
        static ReturnOrExtendFragment newInstance(int num, BookRental bookRental) {
        	ReturnOrExtendFragment f = new ReturnOrExtendFragment();

            Bundle args = new Bundle();
            args.putInt("num", num); // 프래그먼트 테마번호
            args.putSerializable("bookRental", bookRental);
            f.setArguments(args);

            return f;
        }
        public void finish(){
        	
        	this.dismiss();
        }
        @Override
        public void onCreate(Bundle savedInstanceState) { 
            super.onCreate(savedInstanceState);
            mNum = getArguments().getInt("num");
            bookRental = (BookRental) getArguments().getSerializable("bookRental");
            // Pick a style based on the num.
            int style = DialogFragment.STYLE_NO_TITLE;
            int theme = android.R.style.Theme_Panel;
         /*   switch ((mNum-1)%6) {
                case 1:  break;
                case 2: style = DialogFragment.STYLE_NO_FRAME; break;
                case 3: style = DialogFragment.STYLE_NO_INPUT; break;
                case 4: style = DialogFragment.STYLE_NORMAL; break;
                case 5: style = DialogFragment.STYLE_NO_TITLE; break;
                case 6: style = DialogFragment.STYLE_NO_FRAME; break;
                case 7: style = DialogFragment.STYLE_NORMAL; break;
            }
            switch ((mNum-1)%6) {
                case 2: theme = android.R.style.Theme_Panel; break;
                case 4: theme = android.R.style.Theme; break;
                case 5: theme = android.R.style.Theme_Light; break;
                case 6: theme = android.R.style.Theme_Light_Panel; break;
                case 7: theme = android.R.style.Theme_Light; break;
            }*/
            setStyle(style, theme);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_returnorextend, container, false);
           
            Button btnReturn = (Button)v.findViewById(R.id.btnReturn);
            final EditText editTextExtendReason = (EditText)v.findViewById(R.id.editTextExtendReason);
            
            btnReturn.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	new BookReturnAsyncTask().execute(bookRental);
                }
            });
            Button btnExtend = (Button)v.findViewById(R.id.btnExtend);
            btnExtend.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	String text = editTextExtendReason.getText().toString();
                	if(text.trim().equals("")){
                		Toast.makeText(getActivity(), "연기사유를 입력하세요", Toast.LENGTH_SHORT).show();
                	}else{
                		extendReason = text;
                		new BookExtendAsyncTask().execute(bookRental);
                	}
                	
                }
            });
            Button btnCancel = (Button)v.findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new OnClickListener() {
            	public void onClick(View v) {
            		finish();
            	}
            });
            Spinner spinner = (Spinner)v.findViewById(R.id.spinner);
            spinner.setPrompt("연기일수");
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.day_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setSelection(6);
         
            spinner.setOnItemSelectedListener(new DayOnItemSelectedListener());
            return v;
        }
        public class DayOnItemSelectedListener implements OnItemSelectedListener{
  		  @Override
  		  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
  			 extendDay = position+1;
  		  }
  		  @Override
  		  public void onNothingSelected(AdapterView<?> arg0) {
  		   // Do nothing
  		  }     
  		
        }
        //반납
        public class BookReturnAsyncTask extends  AsyncTask<BookRental, Void, RestClient> {
    		@Override
    		protected RestClient doInBackground(BookRental... params) {
    			RestClient restClient = null;
    			BookRental bookRental = (BookRental)params[0]	;
    			try {
    				String url = getString(R.string.base_uri) + "/book/return";
    				restClient = new RestClient(url);
    				restClient.AddParam("id", Long.toString(bookRental.getId()));
    				restClient.AddParam("decorator","exception");
    				restClient.AddHeader("Accept", "application/json");
    				restClient.Execute(RequestMethod.POST);
    				
    			} catch (Exception e) {
    				e.printStackTrace();				
    			}
    			return restClient;
    		}
    		protected void onPostExecute(RestClient restClient) {
    			int statusCode = restClient.getResponseCode();
    			String result = restClient.getResponse();
    			Log.i(TAG,"RESULT:" + result);
    			Log.i(TAG,"statusCode:" + statusCode);
    			
    			if(statusCode==200){
    				JSONObject jsonResponse = restClient.getJsonObject();
    				String status =restClient.getJson("status");
    				if(status.equals("SUCCESS")){
    					finish();
    					Toast.makeText(getActivity(), restClient.getJson("result"), Toast.LENGTH_SHORT).show();
    					Intent intent = new Intent("com.mincoms.refresh");
    					intent.setData(Uri.parse("sample:"));
    					getActivity().sendBroadcast(intent);
    				}else{
    					//Toast.makeText(BookRentalResultActivity.this, result, Toast.LENGTH_SHORT).show();
    				}
    				
    			}else{
    				Log.i(TAG, "result exception:"+statusCode+result);				
    				//Toast.makeText(BookRentalResultActivity.this, result, Toast.LENGTH_SHORT).show();
    			}
    			
    		}
    	}//BookReturnAsyncTask
        //연장 
        public class BookExtendAsyncTask extends  AsyncTask<BookRental, Void, RestClient> {
    		@Override
    		protected RestClient doInBackground(BookRental... params) {
    			RestClient restClient = null;
    			BookRental bookRental = (BookRental)params[0]	;
    			try {
    				String url = getString(R.string.base_uri) + "/book/extend";
    				restClient = new RestClient(url);
    				restClient.AddParam("id", Long.toString(bookRental.getId()));
    				restClient.AddParam("day", Integer.toString(extendDay));
    				restClient.AddParam("extendReason", extendReason);
    				restClient.AddParam("decorator","exception");
    				restClient.AddHeader("Accept", "application/json");
    				restClient.Execute(RequestMethod.POST);
    				
    			} catch (Exception e) {
    				e.printStackTrace();				
    			}
    			return restClient;
    		}
    		protected void onPostExecute(RestClient restClient) {
    			int statusCode = restClient.getResponseCode();
    			String result = restClient.getResponse();
    			Log.i(TAG,"RESULT:" + result);
    			Log.i(TAG,"statusCode:" + statusCode);
    			
    			if(statusCode==200){
    				JSONObject jsonResponse = restClient.getJsonObject();
    				String status =restClient.getJson("status");
    				if(status.equals("SUCCESS")){
    					finish();
    					Toast.makeText(getActivity(), restClient.getJson("result"), Toast.LENGTH_SHORT).show();
    					Intent intent = new Intent("com.mincoms.refresh");
    					intent.setData(Uri.parse("sample:"));
    					getActivity().sendBroadcast(intent);
    				}else{
    					//Toast.makeText(BookRentalResultActivity.this, result, Toast.LENGTH_SHORT).show();
    				}
    				
    			}else{
    				Log.i(TAG, "result exception:"+statusCode+result);				
    				//Toast.makeText(BookRentalResultActivity.this, result, Toast.LENGTH_SHORT).show();
    			}
    			
    		}
    	}
    }
	
}   
