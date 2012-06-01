package itsplace.net.stamp;

import itsplace.net.PlaceActivity;
import itsplace.net.R;
import itsplace.net.common.AbstractAsyncActivity;
import itsplace.net.common.AbstractAsyncListActivity;
import itsplace.net.user.LoginAsyncActivity;
import itsplace.net.util.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import net.itsplace.domain.PlaceListAdapter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class StampSaveActivity extends AbstractAsyncActivity{
	protected static final String TAG = StampSaveActivity.class.getSimpleName();
	 
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.stamp_save_activity);
	        getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
	        
	        EditText editFcode = (EditText) findViewById(R.id.fCode);
	        
	        String fCode = getIntent().getStringExtra("fcode");
	        
	        editFcode.setText(fCode);
	        
	        Button btnStampSave = (Button) findViewById(R.id.btnStampSave);
	        btnStampSave.setOnClickListener(new OnClickListener() {

	        			public void onClick(View v) {
	        				// Toast.makeText(getApplicationContext(), "작업이 끝났습니다.", Toast.LENGTH_LONG).show();
	        				//startActivity(intent);
	        				new saveStampAsync().execute();
	        			}
	        });

	 }
    private class saveStampAsync extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
        	showLoadingProgressDialog();
        }

        @Override
        protected String doInBackground(Void... params) {
			try {
				HttpHeaders requestHeaders = new HttpHeaders();
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
				//requestHeaders.setAccept(acceptableMediaTypes);
				
				HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
				
				
                // Create a new RestTemplate instance
                RestTemplate restTemplate = new RestTemplate();
                final String url = getString(R.string.base_uri)+ "/stamp/saveStampJson";
        		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
        		
        		mvm.add("email", "faye120dg06@gmail.com");
        		mvm.add("sid", "2");
        		mvm.add("remark", "faye11111111111111");
        		mvm.add("mobile", "01065141014");
        		//String result = restTemplate.postForObject(url ,mvm, String.class, requestEntity);//
        		String result = restTemplate.postForObject(url ,mvm, String.class);
        		return result;
            } catch (Exception e) {
                L.e(TAG, e.getMessage(), e);
               
            }

		
			return null;
		}
		
		// when data set changes, you need to call proper method here.
		// do not call notifyDataSetChanged() in 'doInBackground()'.
		protected void onPostExecute(String result){
			//Items.add(result.get(0).getName()+"kkkkkkkkkkkkkkkkk");
			//mAdapter.notifyDataSetChanged();
			dismissProgressDialog();
			if(result.equals("true")){
				Toast.makeText(StampSaveActivity.this, "적립 했어요"+result, Toast.LENGTH_SHORT).show();
				finish();
			}else{
				Toast.makeText(StampSaveActivity.this, "적립 실패 했어요 알 수 없는 오류 발생"+result, Toast.LENGTH_SHORT).show();
			}
			
			
			
			
            // �۾��� �Ϸ� �� �� ����
            // dialog.dismiss();
            // super.onPostExecute(result);
		}
    }
}
