package com.mincoms.main;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import net.itsplace.zxing.client.android.CaptureActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mincoms.main.R;
import com.mincoms.main.BookRentalResultActivity.DayOnItemSelectedListener;
import com.mincoms.domain.BookCategory;
import com.mincoms.domain.BookCategoryRoot;
import com.mincoms.domain.BookCategorySub;
import com.mincoms.domain.BookInfo;
import com.mincoms.domain.BookRental;
import com.mincoms.exception.ExceptionReport;
import com.mincoms.restful.RestClient;
import com.mincoms.restful.RestClient.RequestMethod;


public class BookRegisterActivity extends Activity {
	protected static final String TAG = BookRegisterActivity.class.getSimpleName();
	List<String> bookCategoryRootSpinnerList,bookCategorySubSpinnerList,bookCategorySpinnerList;
	ArrayAdapter<String> bookCategoryRootAdapter, bookCategorySubAdapter,bookCategoryAdapter;
	TextView titleTextView;
	EditText bookCountEditText;
	String ISBN = ""; 
	Integer bookCategoryRootCode;
	Integer bookCategorySubCode;
	Integer bookCategoryCode;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookregister_activity);
        Spinner spinnerBookCategoryRoot = (Spinner)findViewById(R.id.spinnerBookCategoryRoot);
        Spinner spinnerBookCategorySub = (Spinner)findViewById(R.id.spinnerBookCategorySub);
        Spinner spinnerBookCategory = (Spinner)findViewById(R.id.spinnerBookCategory);
        
      //  titleTextView = (TextView) findViewById(R.id.title);
        
        bookCountEditText = (EditText) findViewById(R.id.bookCount);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        
		/* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.day_array, android.R.layout.simple_spinner_item);
		 
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       */
        bookCategoryRootSpinnerList = new ArrayList();
        bookCategoryRootAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bookCategoryRootSpinnerList);
        bookCategoryRootAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBookCategoryRoot.setAdapter(bookCategoryRootAdapter);
        spinnerBookCategoryRoot.setOnItemSelectedListener(new BookCategoryRootOnItemSelectedListener());

        bookCategorySubSpinnerList = new ArrayList();
        bookCategorySubAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bookCategorySubSpinnerList);
        bookCategorySubAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBookCategorySub.setAdapter(bookCategorySubAdapter);
        spinnerBookCategorySub.setOnItemSelectedListener(new BookCategorySubOnItemSelectedListener());
        
        bookCategorySpinnerList = new ArrayList();
        bookCategoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bookCategorySpinnerList);
        bookCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBookCategory.setAdapter(bookCategoryAdapter);
        spinnerBookCategory.setOnItemSelectedListener(new BookCategoryOnItemSelectedListener());
        
       // spinner.setSelection(6);
        
       // setContentView(R.layout.main);
        // Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        Intent intent = new Intent(BookRegisterActivity.this,CaptureActivity.class);
        //Intent intent = new Intent("net.itsplace.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent,1);
        
        
        
        btnRegister.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				 BookInfo book = new BookInfo();
				 BookCategory bookCategory = new BookCategory();
				 bookCategory.setId(bookCategoryCode);
				 BookCategoryRoot bookCategoryRoot = new BookCategoryRoot();
				 bookCategoryRoot.setId(bookCategoryRootCode);
				 BookCategorySub bookCategorySub = new BookCategorySub();
				 bookCategorySub.setId(bookCategorySubCode);
				 bookCategorySub.setBookCategoryRoot(bookCategoryRoot);
				 bookCategory.setBookCategorySub(bookCategorySub);
				 
				 
				 book.setBookCategory(bookCategory);
	           	 book.setIsbn(ISBN);
	           	 String count = bookCountEditText.getText().toString().trim();
	           	 book.setCount(Integer.parseInt(count));
	           	 if(count.isEmpty()){
	           		Toast.makeText(BookRegisterActivity.this, "수량을 입력하세요", Toast.LENGTH_SHORT).show();
	           	 }else{
	           		
		           	new SaveBookAsyncTask().execute(book); 
	           	 }
	           	 
			}
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		finishCall();
        	}
        });
    }
   public void finishCall(){
	   this.finish();
   }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		
		  Log.i(TAG,"requestCode:"+Integer.toString(requestCode));
		  Log.i(TAG,"resultCode:"+Integer.toString(resultCode));
	        if(requestCode == 1)
	        {
	        	
	        	// String contents = intent.getStringExtra("SCAN_RESULT");
                 //Log.i(TAG,contents);
	              if(resultCode == RESULT_OK)
	              {
	            	  ISBN = intent.getStringExtra("SCAN_RESULT");
	            	  
	            	 
	            	  new getBookCategoryRootAsyncTask().execute();
	            	 
	              }
	              else if(resultCode == RESULT_CANCELED)
	              {
	            	  
	            	  this.finish();
	                     /*Toast.makeText(QRcodeActivity.this, "qrcode RESULT_CANCELED", Toast.LENGTH_SHORT).show();*/
	              }
	        }
	}
	
	private class SaveBookAsyncTask extends  AsyncTask<BookInfo, Void, RestClient> {
		@Override
		protected void onPreExecute() {
		}
		@Override
		protected RestClient doInBackground(BookInfo... params) {
			
			BookInfo scanBook = (BookInfo)params[0]	;
			String url = getString(R.string.base_uri) + "/book/add/google";
			RestClient restClient = new RestClient(url);			
			restClient.AddParam("isbn",scanBook.getIsbn());
			restClient.AddParam("count",scanBook.getCount().toString());
			restClient.AddParam("bookCategory.id",bookCategoryCode.toString());
			restClient.AddParam("bookCategory.bookCategorySubid",bookCategorySubCode.toString());
			restClient.AddParam("decorator","exception");
			restClient.AddHeader("Accept", "application/json");
		
	
			try {
				restClient.Execute(RequestMethod.POST);
			} catch (Exception e) {
				e.printStackTrace();		
			}
			
			return restClient;
		}
	
		
		protected void onPostExecute(RestClient restClient) {
			JSONObject jsonResponse = null;
			BookInfo book = null;
			if(restClient.getResponseCode()==200){
				String result = restClient.getJson("status");
				
				if(result.trim().equals("login")){
					//Toast.makeText(BookRegisterActivity.this,"로그인 리다이렉트", Toast.LENGTH_SHORT).show();
				}else if(result.equals("SUCCESS")){
					GsonBuilder gsonb = new GsonBuilder();
					DateDeserializer ds = new DateDeserializer();
					gsonb.registerTypeAdapter(Date.class, ds);
					Gson gson = gsonb.create();
					book =  gson.fromJson(restClient.getJson("result"), BookInfo.class);	
					Log.i(TAG,"book title:" + book.getTitle());
				}else{
					Log.i(TAG, "result exception:"+result);				
					Toast.makeText(BookRegisterActivity.this, restClient.getJson("result"), Toast.LENGTH_SHORT).show();
				}
			}else{
				String result = restClient.getResponse();
				Log.i(TAG, "result exception:"+result);				
				Toast.makeText(BookRegisterActivity.this, restClient.getJson("result"), Toast.LENGTH_SHORT).show();
				
				//ExceptionReport e = new ExceptionReport();
				//e.SendErrorMail(getApplicationContext(), "ddddddd");
			}
			showResult(book);
		}
	}
	public void showResult(BookInfo book){
		if(book != null){
			
			Toast.makeText(BookRegisterActivity.this, book.getTitle() + "등록되었습니다", Toast.LENGTH_SHORT).show();
		}
			this.finish();
	}
	
	/*루트 카테고리 */
	private class getBookCategoryRootAsyncTask extends  AsyncTask<Void, Void, RestClient> {
		@Override
		protected void onPreExecute() {
		}
		@Override
		protected RestClient doInBackground(Void... params) {
			
			String url = getString(R.string.base_uri) + "/book/getBookCategoryRoot";
			RestClient restClient = new RestClient(url);			
			restClient.AddParam("decorator","exception");
			restClient.AddHeader("Accept", "application/json");
			try {
				restClient.Execute(RequestMethod.GET);
			} catch (Exception e) {
				e.printStackTrace();		
			}
			return restClient;
		}
		protected void onPostExecute(RestClient restClient) {
			if(restClient.getResponseCode()==200){
				String result = restClient.getResponse();
				Log.i(TAG,result);
				if(result.trim().equals("login")){
					//Toast.makeText(BookRegisterActivity.this,"로그인 리다이렉트", Toast.LENGTH_SHORT).show();
				}else{
					try {
						JSONArray jsonArray = new JSONArray(result);
						GsonBuilder gsonb = new GsonBuilder();
						DateDeserializer ds = new DateDeserializer();
						gsonb.registerTypeAdapter(Date.class, ds);
						Gson gson = gsonb.create();
						
						for (int i = 0; i < jsonArray.length(); i++) {
							BookCategoryRoot bookCategoryRoot = gson.fromJson(jsonArray.getString(i), BookCategoryRoot.class);
							bookCategoryRootSpinnerList.add(bookCategoryRoot.getName() +"                                                                                                 ,"+bookCategoryRoot.getId());
							Log.i(TAG,"카테고리:"+bookCategoryRoot.getName());
						}
						bookCategoryRootAdapter.notifyDataSetChanged();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				String result = restClient.getResponse();
				Log.i(TAG, "result exception:"+result);				
				Toast.makeText(BookRegisterActivity.this, result, Toast.LENGTH_SHORT).show();
			}
		}
	}
	/*서브 카테고리 */
	private class getBookCategorySubAsyncTask extends  AsyncTask<String, Void, RestClient> {
		@Override
		protected void onPreExecute() {
		}
		@Override
		protected RestClient doInBackground(String... params) {
			String root_id = params[0];
			String url = getString(R.string.base_uri) + "/book/getBookCategorySub";
			RestClient restClient = new RestClient(url);			
			restClient.AddParam("decorator","exception");
			restClient.AddHeader("Accept", "application/json");
			restClient.AddParam("root_id",root_id);
			try {
				restClient.Execute(RequestMethod.GET);
			} catch (Exception e) {
				e.printStackTrace();		
			}
			return restClient;
		}
		protected void onPostExecute(RestClient restClient) {
			if(restClient.getResponseCode()==200){
				String result = restClient.getResponse();
				Log.i(TAG,result);
				if(result.trim().equals("login")){
					//Toast.makeText(BookRegisterActivity.this,"로그인 리다이렉트", Toast.LENGTH_SHORT).show();
				}else{
					try {
						JSONArray jsonArray = new JSONArray(result);
						GsonBuilder gsonb = new GsonBuilder();
						DateDeserializer ds = new DateDeserializer();
						gsonb.registerTypeAdapter(Date.class, ds);
						Gson gson = gsonb.create();
						bookCategorySubSpinnerList.clear();
						for (int i = 0; i < jsonArray.length(); i++) {
							BookCategorySub bookCategorySub = gson.fromJson(jsonArray.getString(i), BookCategorySub.class);
							bookCategorySubSpinnerList.add(bookCategorySub.getName() +"                                                                                                 ,"+bookCategorySub.getId());
							Log.i(TAG,"카테고리:"+bookCategorySub.getName());
						}
						bookCategorySubAdapter.notifyDataSetChanged();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				String result = restClient.getResponse();
				Log.i(TAG, "result exception:"+result);				
				Toast.makeText(BookRegisterActivity.this, result, Toast.LENGTH_SHORT).show();
			}
		}
	}
	/*서카테고리 */
	private class getBookCategoryAsyncTask extends  AsyncTask<String, Void, RestClient> {
		@Override
		protected void onPreExecute() {
		}
		@Override
		protected RestClient doInBackground(String... params) {
			String sub_id = params[0];
			String url = getString(R.string.base_uri) + "/book/getBookCategory";
			RestClient restClient = new RestClient(url);			
			restClient.AddParam("decorator","exception");
			restClient.AddHeader("Accept", "application/json");
			restClient.AddParam("sub_id",sub_id);
			try {
				restClient.Execute(RequestMethod.GET);
			} catch (Exception e) {
				e.printStackTrace();		
			}
			return restClient;
		}
		protected void onPostExecute(RestClient restClient) {
			if(restClient.getResponseCode()==200){
				String result = restClient.getResponse();
				Log.i(TAG,result);
				if(result.trim().equals("login")){
					//Toast.makeText(BookRegisterActivity.this,"로그인 리다이렉트", Toast.LENGTH_SHORT).show();
				}else{
					try {
						JSONArray jsonArray = new JSONArray(result);
						GsonBuilder gsonb = new GsonBuilder();
						DateDeserializer ds = new DateDeserializer();
						gsonb.registerTypeAdapter(Date.class, ds);
						Gson gson = gsonb.create();
						bookCategorySpinnerList.clear();
						for (int i = 0; i < jsonArray.length(); i++) {
							BookCategory bookCategory = gson.fromJson(jsonArray.getString(i), BookCategory.class);
							bookCategorySpinnerList.add(bookCategory.getName() +"                                                                                                 ,"+bookCategory.getId());
							Log.i(TAG,"카테고리:"+bookCategory.getName());
						}
						bookCategoryAdapter.notifyDataSetChanged();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				String result = restClient.getResponse();
				Log.i(TAG, "result exception:"+result);				
				Toast.makeText(BookRegisterActivity.this, result, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
	
	public class BookCategoryRootOnItemSelectedListener implements OnItemSelectedListener{
		  @Override
		  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			 String text = (String) parent.getItemAtPosition(position);
			 StringTokenizer st = new StringTokenizer(text, ",");
			 String root_id = st.nextToken();
			 root_id = st.nextToken();
			 bookCategoryRootCode = Integer.parseInt(root_id);
			 new getBookCategorySubAsyncTask().execute(root_id);
		  }
		  @Override
		  public void onNothingSelected(AdapterView<?> arg0) {
		  }     
	}
	public class BookCategorySubOnItemSelectedListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			String text = (String) parent.getItemAtPosition(position);
			StringTokenizer st = new StringTokenizer(text, ",");
			String sub_id = st.nextToken();
			sub_id = st.nextToken();
			bookCategorySubCode = Integer.parseInt(sub_id);
			new getBookCategoryAsyncTask().execute(sub_id);
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}     
	}
	public class BookCategoryOnItemSelectedListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			String text = (String) parent.getItemAtPosition(position);
			StringTokenizer st = new StringTokenizer(text, ",");
			String categiory = st.nextToken();
			categiory = st.nextToken();
			bookCategoryCode = Integer.parseInt(categiory);
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}     
	}
}
