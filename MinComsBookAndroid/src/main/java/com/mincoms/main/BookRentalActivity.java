package com.mincoms.main;

import java.util.Date;

import net.itsplace.zxing.client.android.CaptureActivity;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mincoms.domain.BookInfo;

import com.mincoms.restful.RestClient;
import com.mincoms.restful.RestClient.RequestMethod;

public class BookRentalActivity  extends Activity {
	protected static final String TAG = BookRentalActivity.class.getSimpleName();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(BookRentalActivity.this,CaptureActivity.class);
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent,1);
  	 /* BookInfo book = new BookInfo();
	  book.setIsbn("9788996276593");
	  new BookRentalAsyncTask().execute(book);*/
    }
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
	        if(requestCode == 1)
	        {
	              if(resultCode == RESULT_OK)
	              {
	            	  String ISBN = intent.getStringExtra("SCAN_RESULT");	            	 
	            	  BookInfo book = new BookInfo();
	            	  book.setIsbn(ISBN);
	            	  new BookRentalAsyncTask().execute(book);
	            	 // this.finish();
	              }
	              else if(resultCode == RESULT_CANCELED)
	              {
	            	  this.finish();
	                  
	              }
	        }
	}
	public void showResult(BookInfo book){
		if(book != null){
			//Toast.makeText(BookRentalActivity.this, book.getTitle() + "등록가능", Toast.LENGTH_SHORT).show();
			  Intent intent = new Intent(this,BookRentalResultActivity.class);			
			  intent.putExtra("book",book);			  
			  startActivity(intent);
		}
		
		this.finish();
	}
	
	private class BookRentalAsyncTask extends  AsyncTask<BookInfo, Void, RestClient> {
		@Override
		protected void onPreExecute() {
			
		}
	  
		@Override
		protected RestClient doInBackground(BookInfo... params) {
			RestClient restClient = null;
			BookInfo scan = (BookInfo)params[0]	;
			
			try {
				String url = getString(R.string.base_uri) + "/book/isrental";
				restClient = new RestClient(url);
				restClient.AddParam("isbn",scan.getIsbn());
				restClient.AddParam("decorator","exception");
				restClient.AddHeader("Accept", "application/json");
				restClient.Execute(RequestMethod.POST);
				
			} catch (Exception e) {
				e.printStackTrace();				
			}
			
			return restClient;
		}
	
		
		protected void onPostExecute(RestClient restClient) {
			BookInfo book = null;
			int statusCode = restClient.getResponseCode();
			String result = restClient.getResponse();
			Log.i(TAG,"RESULT:" + result);
			Log.i(TAG,"statusCode:" + statusCode);
			
			if(statusCode==200){
				JSONObject jsonResponse = restClient.getJsonObject();
//				String status = jsonResponse.getString("status");
				String status =restClient.getJson("status");
			
				
				if(status.equals("SUCCESS")){
					GsonBuilder gsonb = new GsonBuilder();
					DateDeserializer ds = new DateDeserializer();
					gsonb.registerTypeAdapter(Date.class, ds);
					Gson gson = gsonb.create();
					book =  gson.fromJson(restClient.getJson("result").toString(), BookInfo.class);	
					Log.i(TAG,"book"+book.getTitle());
				}else{
					Log.i(TAG, "result failed:"+statusCode+result);				
					Toast.makeText(BookRentalActivity.this, restClient.getJson("result"), Toast.LENGTH_SHORT).show();
				}
					
				
			}else{
				Log.i(TAG, "result exception:"+statusCode+result);				
				Toast.makeText(BookRentalActivity.this, restClient.getJson("result"), Toast.LENGTH_SHORT).show();
				
				//ExceptionReport e = new ExceptionReport();
				//e.SendErrorMail(getApplicationContext(), "ddddddd");
			}
			showResult(book);
		}
	}
}
