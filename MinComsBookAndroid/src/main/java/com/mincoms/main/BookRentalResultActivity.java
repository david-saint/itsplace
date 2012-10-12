package com.mincoms.main;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.fedorvlasov.lazylist.ImageLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mincoms.domain.BookInfo;
import com.mincoms.restful.RestClient;
import com.mincoms.restful.RestClient.RequestMethod;

import net.itsplace.zxing.client.android.CaptureActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BookRentalResultActivity extends SherlockFragmentActivity {
	protected static final String TAG = BookRentalResultActivity.class.getSimpleName();
	private ImageLoader imageLoader; 
	int rentalDay = 7;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookrentalresult_activity);
		
		 final BookInfo book = (BookInfo) getIntent().getSerializableExtra("book");
		 
		 
		 Log.i(TAG,book.getTitle());
		 TextView textView = (TextView) findViewById(R.id.title);
		 ImageView imageView = (ImageView) findViewById(R.id.thumbnail);
		 Button btnRental = (Button)findViewById(R.id.btnRental);
		 Button btnCancel = (Button)findViewById(R.id.btnCancel);
		
		 textView.setText(book.getTitle());
		 
		 
		 imageLoader=new ImageLoader(this);
		 
		 imageLoader.DisplayImage( book.getThumbnail(), imageView);
		 
		 Spinner spinner = (Spinner)findViewById(R.id.spinner);
		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.day_array, android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.setAdapter(adapter);
         spinner.setSelection(6);
      
         spinner.setOnItemSelectedListener(new DayOnItemSelectedListener());
         
         btnRental.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new BookRentalAsyncTask().execute(book);
			}
         });
         btnCancel.setOnClickListener(new View.OnClickListener() {
        	 public void onClick(View v) {
        		 finish();
        	 }
         });
	}
	public void showResult(){
		 Intent intent = new Intent("com.mincoms.refresh");
		 intent.setData(Uri.parse("sample:"));
		 sendBroadcast(intent);
		
		this.finish();
	}
	
	public class DayOnItemSelectedListener implements OnItemSelectedListener{
		  @Override
		  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			
			   /*Toast.makeText(parent.getContext(), 
			       "선택한 66행성은 "+parent.getItemAtPosition(position), 
			       Toast.LENGTH_SHORT).show();*/
			 Log.i(TAG,"position"+position+id);
			 rentalDay=position+1;
			
		  }
		  
		  @Override
		  public void onNothingSelected(AdapterView<?> arg0) {
		   // Do nothing
		  }     
		
	}
	
	public class BookRentalAsyncTask extends  AsyncTask<BookInfo, Void, RestClient> {
		@Override
		protected void onPreExecute() {
			setSupportProgressBarIndeterminateVisibility(true);
		}
	  
		@Override
		protected RestClient doInBackground(BookInfo... params) {
			RestClient restClient = null;
			BookInfo scan = (BookInfo)params[0]	;
			
			try {
				String url = getString(R.string.base_uri) + "/book/rental";
				restClient = new RestClient(url);
				restClient.AddParam("isbn",scan.getIsbn());
				restClient.AddParam("day", Integer.toString(rentalDay));
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
//				String status = jsonResponse.getString("status");
				String status =restClient.getJson("status");
			
				
				if(status.equals("SUCCESS")){
					Toast.makeText(BookRentalResultActivity.this, restClient.getJson("result"), Toast.LENGTH_SHORT).show();

				}else{
				
					Toast.makeText(BookRentalResultActivity.this,  restClient.getJson("result"), Toast.LENGTH_SHORT).show();
				}
				
				
			}else{
				Log.i(TAG, "result exception:"+statusCode+result);				
				Toast.makeText(BookRentalResultActivity.this,  restClient.getJson("result"), Toast.LENGTH_SHORT).show();
				
				//ExceptionReport e = new ExceptionReport();
				//e.SendErrorMail(getApplicationContext(), "ddddddd");
			}
			setSupportProgressBarIndeterminateVisibility(true);
			showResult();
		}
	}
	
}
