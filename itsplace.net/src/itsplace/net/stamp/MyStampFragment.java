package itsplace.net.stamp;

import itsplace.net.MainApplication;
import itsplace.net.PlaceActivity;
import itsplace.net.R;
import itsplace.net.common.DateDeserializer;
import itsplace.net.connection.RestClient;
import itsplace.net.connection.RestClient.RequestMethod;
import itsplace.net.util.L;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.Stamp;
import net.itsplace.domain.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class MyStampFragment extends ListFragment{
	private View v;
	private Integer fid;
	private Integer stampid;
	private TextView txtEvent;
	private TextView textView;
	private TextView txtStampTitle;
	private TextView txtFcode;
	private LinearLayout linearLayout;
	private LinearLayout stampLayout;
	private Date date;
	 private  String email;
	private static final String KEY_FID = "fid" ;
	private static final String KEY_STAPMID = "stampid";
	private static final String KEY_EMAIL = "email";
	public MyStampFragment() {	Log.i(TAG,"프래그먼트 일반생성자 콜 ");}
	protected static final String TAG = MyStampFragment.class.getSimpleName();
	
	 public static MyStampFragment newInstance(Integer fid, Integer stampid, String email) {
		 MyStampFragment fragment = new MyStampFragment();
		 fragment.fid = fid;
		 fragment.stampid = stampid;
		 fragment.email = email;
	     return fragment;
	}
//	public MyStampFragment(Integer fid,Integer stampid,User user,Context context) {
//		Log.i(TAG,"프래그먼트 생성자 콜 ");
//		this.fid = fid;
//		this.stampid = stampid;
//		this.user = user;
//		this.context = context;
//	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			
	            this.fid = savedInstanceState.getInt(KEY_FID);
	            this.stampid = savedInstanceState.getInt(KEY_STAPMID);
	            this.email = savedInstanceState.getString(KEY_EMAIL);
	            Log.i(TAG,"프래그먼트 oncreate fid:" + fid );
	            Log.i(TAG,"프래그먼트 oncreate stampid:" + stampid );
	            Log.i(TAG,"프래그먼트 oncreate email:" + email );
	    }
	} 
	 @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt(KEY_FID, fid );
	        outState.putInt(KEY_STAPMID, stampid);
	        outState.putString(KEY_EMAIL, email);
	        Log.i(TAG,"프래그먼트 onSaveInstanceState fid:" + fid );
            Log.i(TAG,"프래그먼트 onSaveInstanceState stampid:" + stampid );
            Log.i(TAG,"프래그먼트 onSaveInstanceState email:" + email );
	    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Drawable prev =  getResources().getDrawable(R.drawable.rss);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.rss);
        ImageView image;
        image = new ImageView(getActivity().getApplicationContext());
        image.setImageResource(R.drawable.stamp);
        //image.setImageBitmap(bmp);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
       // image.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        image.setPadding(5, 5, 5, 5);
      //  LinearLayout.LayoutParams arrowLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
      //  container.addView(image,arrowLayoutParams);
       
        v = inflater.inflate(R.layout.stamp_fragment, container, false);
        linearLayout = (LinearLayout) v.findViewById(R.id.stamplinear);
        stampLayout  = (LinearLayout) v.findViewById(R.id.stampLayout);
        txtEvent = (TextView) v.findViewById(R.id.event);
        txtFcode = (TextView) v.findViewById(R.id.fcode);
        txtStampTitle = (TextView) v.findViewById(R.id.stampTitle);
       if(fid!=null){
    	   Log.i(TAG,"fid널 아닙니다다---------exec ");
    	   new getStamped().execute(fid); 
       }else{
    	   Log.i(TAG,"fid널 입니다-------exec ");
       }
//	        l.addView(image,arrowLayoutParams);
      //  l.addView(image);
      //  stampLayout.addView(image, 1, new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
      //  textView = (TextView) v.findViewById(R.id.text);
        
       // textView.setText("qqqqq");
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"onActivityCreated");
        Log.i(TAG,"onActivityCreated");
        //setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, list));
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("FragmentList", "Item clicked: " + id);
    }
	public void setStampList(List<Stamp> stampList){
		LinearLayout stampLineLayout = null;
		List<Stamp> useStampList = new ArrayList<Stamp>();
		 //textView.setText(stampList.get(0).getFname());
		//L.i("ddddddddddddd", stampList.get(0).getFname());
		//textView.setText(stampList.get(0).getFname()+stampList.get(0).getRemark());
		int stampCount=0;
		int useStampCount=0;//사용한 스탬프 갯수
		int emptyStampCount=0;
		//int eventDay = Integer.parseInt(stampList.get(0).getEventday());
		
		//stampCount = Integer.parseInt(stampList.get(0).getStampcount());
		txtEvent.setText(stampList.get(0).getRemark());
		txtStampTitle.setText(stampList.get(0).getPlaceStamp().getStampTitle());
		//txtFcode.setText(stampList.get(0).getFcode());
		
		 Button btnStampSaveActivity = (Button) v.findViewById(R.id.btnStampSaveActivity);
	        final Intent intent = new Intent(getActivity(), StampSaveActivity.class);	        
			intent.putExtra("fcode", txtFcode.getText());
			
	        btnStampSaveActivity.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					startActivity(intent);
				}
	        });
	        
		int stampLine = stampCount/5;
		
				
		//for(int i=0; i<stampLine; i++){
		//	stampLineLayout = new LinearLayout(getActivity().getApplicationContext());			
		//	stampLayout.addView(stampLineLayout);
		//}
		
		ImageView image;
		Bitmap bitmap = null;
		for(int i=0; i<stampList.size(); i++){
			
			image = new ImageView(getActivity().getApplicationContext());
			image.setImageResource(R.drawable.stamp_empty);
			
			if(i%5==0){										
				stampLineLayout = new LinearLayout(getActivity().getApplicationContext());
				stampLineLayout.setBackgroundColor(Color.WHITE);
				stampLineLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				stampLayout.addView(stampLineLayout);
			
				Stamp stamp = stampList.get(i);
				if(stamp.getStatus().equals("P")){//적립					  
				    bitmap = drawText(BitmapFactory.decodeResource(getResources(),R.drawable.stamp), stampList.get(i).getSaveDate() );
				}
				if(stamp.getStatus().equals("U")){
					++useStampCount;
					useStampList.add(stamp);
				}
			     //   stampLayout.addView(image);
			       // stampLayout.addView(image, 1, new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			//	}
			}//else{
			//	bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.stamp_empty);
			//}
			
//				if((i+1)%eventDay==0){
//					++emptyStampCount;
//					bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.heart_and_rose);
//					
//				}
			  	image.setImageBitmap(bitmap);
		        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
		       
		        image.setPadding(5, 5, 5, 5);
		        image.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		        stampLineLayout.addView(image);
		}
			/*
			if(stampList.get(i).getStatus().equals("P")){
			    //image.setImageResource(R.drawable.stamp);
			    bitmap = drawText(BitmapFactory.decodeResource(getResources(),R.drawable.stamp), stampList.get(i).getInpdate().toString() );
			    image.setImageBitmap(bitmap);
		        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
		       
		        image.setPadding(5, 5, 5, 5);
		        image.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		     //   stampLayout.addView(image);
		       // stampLayout.addView(image, 1, new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			}else{
			    
			}
		      */  
		  
		
		Log.i(TAG,"그리기 완료 ");
	
	}
	private Bitmap drawText(Bitmap bitmap,Date saveDate){
		Bitmap bitmapText = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight()+20, 
			    Bitmap.Config.ARGB_4444);
		Canvas canvas = new Canvas(bitmapText);
		
		
		canvas.drawColor(Color.TRANSPARENT);
		//canvas.drawColor();
		Paint p = new Paint();		
		p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);
		p.setTextSize(12);
		p.setTypeface(Typeface.DEFAULT_BOLD);
		p.setFakeBoldText(true);
		p.setTextAlign(Align.CENTER);
		
		p.setAntiAlias(true);
		
		 String fmt = "yy/MM/dd";
	       
		 String date = (new SimpleDateFormat(fmt)).format(saveDate);
	        
		canvas.drawText(date, bitmap.getWidth()/2 ,bitmap.getHeight()+15, p);
		
		//p.setShader(shader);
		canvas.drawBitmap(bitmap,0, 0, p);
		return bitmapText;
	}
	private class getStamped extends AsyncTask<Integer, Void, List<Stamp>> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<Stamp> doInBackground(Integer... params) {
        	final String url = getString(R.string.base_uri) + "/stamp/stamppedList";
			Log.i(TAG, url);
			
			RestClient restClient = new RestClient(url);
			List<Stamp> stampList = new ArrayList();
			try {
				
					Log.i(TAG, "헐 fid"+Integer.toString(fid));
					Log.i(TAG, "헐 stampid"+Integer.toString(stampid));
				
				restClient.AddParam("fid", Integer.toString(fid));
				restClient.AddParam("email", "faye12005@gmail.com");
				restClient.AddParam("stampid", Integer.toString(stampid));
				restClient.Execute(RequestMethod.POST);
				if (restClient.getResponseCode() == 200) {
					try {
						Log.i(TAG, "적립된 스탬프 조회 200");
						JSONObject jsonResponse = restClient.getJsonObject();
						JSONArray placesJson = jsonResponse.getJSONArray("result");
						GsonBuilder gsonb = new GsonBuilder();
						DateDeserializer ds = new DateDeserializer();
						gsonb.registerTypeAdapter(Date.class, ds);
						Gson gson = gsonb.create();
						
						for (int i = 0; i < placesJson.length(); i++) {
							Stamp stamp = gson.fromJson(placesJson.getString(i), Stamp.class);
							stampList.add(stamp);
							//Log.i(TAG, stamp.getPlaceStamp().getStampTitle());
							//Log.i(TAG, stamp.getSaveDate().toGMTString());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					Log.i(TAG, "적립된 스탬프  조회 오류");
				}
            } catch (Exception e) {
            	Log.i(TAG, "적립된 스탬프 조회 익셉션 발생성 ");
            	Log.e(TAG, e.getMessage()+ e.getLocalizedMessage(), e);
				Log.i(TAG, restClient.getErrorMessage(), e);
               
            }
			return stampList;
        }
		protected void onPostExecute(List<Stamp> result){
			//Items.add(result.get(0).getName()+"kkkkkkkkkkkkkkkkk");
			//mAdapter.notifyDataSetChanged();
			//dismissProgressDialog();
			//mTestLayout.setVisibility(View.INVISIBLE);
				//refreshStates(result);
				setStampList(result);
				 //Toast.makeText(context, "스탬프가 없습니다", Toast.LENGTH_LONG).show();
			
            // �۾��� �Ϸ� �� �� ����
            // dialog.dismiss();
            // super.onPostExecute(result);
		}
    }
	
	
	}   
