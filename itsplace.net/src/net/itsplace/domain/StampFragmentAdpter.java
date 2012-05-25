package net.itsplace.domain;

import itsplace.net.MainApplication;
import itsplace.net.R;
import itsplace.net.ViewPagerIndicator;
import itsplace.net.franchiser.FranchiserViewActivity;
import itsplace.net.stamp.StampSaveActivity;
import itsplace.net.util.L;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import android.content.Context;
import android.content.Intent;
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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class StampFragmentAdpter extends FragmentPagerAdapter implements ViewPagerIndicator.PageInfoProvider{
	private List<Stamp> stampList;
	User user;
	private Context context;
	public StampFragmentAdpter(FragmentManager fm,User user,Context context) {
		super(fm);
		this.user = user;
		this.context = context;
	}
	public void addStampList(List<Stamp> stampList){
		this.stampList = new ArrayList<Stamp>();
		this.stampList.addAll(stampList);
	}
	
	@Override
	public Fragment getItem(int pos) {
		//스탬프 가져오기
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, pos - getCount() / 2);
		
		
		
		//this.stampList.get(pos).getFid()
		//myStampByFidJson
		L.i("dddd", pos+"");
	
		if(stampList == null){
			//return ItemFragment.newInstance(1);
			return  new ItemFragment();
			// new getStamped().execute(Integer.toString(fid)); 
		}else{
			return null;
			//return new ItemFragment(this.stampList.get(pos).getFid());
		}
		
		//return new ItemFragment(this.stampList.get(pos).getFid());
	}
	
	@Override
	public int getCount() {
		return stampList!= null ? stampList.size(): 1;
	}
	
	
	@Override
	public String getTitle(int pos){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, pos - getCount() / 2);
		//return readableDateFormat.format(cal.getTime());
		
		return "";//this.stampList.get(pos).getFname()+pos;
	}


	
		 
    public  class ItemFragment extends ListFragment{
    	 View v;
				Integer fid;
				TextView textView;
				TextView txtEvent;
				TextView txtFcode;
				LinearLayout linearLayout;
				LinearLayout stampLayout;
		    	Date date;
		    	public ItemFragment() {}
		    	public ItemFragment(Integer fid) {
		    		
		    		this.fid = fid;
		         
		            
		        }
	
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			
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
	        new getStamped().execute(fid); 
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
	        //setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, list));
	    }
	
	    @Override
	    public void onListItemClick(ListView l, View v, int position, long id) {
	        Log.i("FragmentList", "Item clicked: " + id);
	    }
	  
		public void setStampt(List<Stamp> stampList){
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
					  //  bitmap = drawText(BitmapFactory.decodeResource(getResources(),R.drawable.stamp), stampList.get(i).getInpdate() );
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
			  
			
			
		
		}
		private Bitmap drawText(Bitmap bitmap,Date inpDate){
			Bitmap bitmapText = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight()+20, 
				    Bitmap.Config.ARGB_4444);
			Canvas canvas = new Canvas(bitmapText);
			
			
			canvas.drawColor(Color.TRANSPARENT);
			//canvas.drawColor();
			Paint p = new Paint();		
			p.setColor(Color.BLUE);
	        p.setStyle(Paint.Style.FILL);
			p.setTextSize(15);
			p.setTypeface(Typeface.DEFAULT_BOLD);
			p.setFakeBoldText(true);
			p.setTextAlign(Align.CENTER);
			
			p.setAntiAlias(true);
			
			 String fmt = "yy/MM/dd";
		       
			 String date = (new SimpleDateFormat(fmt)).format(inpDate);
		        
			canvas.drawText(date, bitmap.getWidth()/2 ,bitmap.getHeight()+15, p);
			
			//p.setShader(shader);
			canvas.drawBitmap(bitmap,0, 0, p);
			return bitmapText;
		}
		private class getStamped extends AsyncTask<Integer, Void, List<Stamp>> {

	        @Override
	        protected void onPreExecute() {
	            // before the network request begins, show a progress indicator
	        	//showLoadingProgressDialog();
	        	//mTestLayout.setVisibility(View.VISIBLE);
	        	
	        }

	        @Override
	        protected List<Stamp> doInBackground(Integer... params) {
				try {
	                // The URL for making the GET request
					String fid = params[0]+	"";
					
					
	                final String url = context.getString(R.string.base_uri)+"/stamp/myStampByFidJson?email=" + user.getEmail() + "&fid=" + fid; 
					//final String url ="http://192.168.123.186:8080/MyPlace/stamp/myStampByFidJson?email=faye12005@gmail.com&fid="+fid;	                //getString(R.string.base_uri)
	                Log.i("myStampByFidJson","URL:"+url);
	                
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
	                ResponseEntity<Stamp[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Stamp[].class);

	                // convert the array to a list and return it
	               
	                return Arrays.asList(responseEntity.getBody());
	            } catch (Exception e) {
	                L.e("myStampByFidJson", e.getMessage(), e);
	               
	            }

			
				return null;
			}
			
			// when data set changes, you need to call proper method here.
			// do not call notifyDataSetChanged() in 'doInBackground()'.
			protected void onPostExecute(List<Stamp> result){
				//Items.add(result.get(0).getName()+"kkkkkkkkkkkkkkkkk");
				//mAdapter.notifyDataSetChanged();
				//dismissProgressDialog();
				//mTestLayout.setVisibility(View.INVISIBLE);
				if(result != null){
					//refreshStates(result);
					setStampt(result);
				}else{
					 Toast.makeText(context, "스탬프가 없습니다", Toast.LENGTH_LONG).show();
				}
				
	            // �۾��� �Ϸ� �� �� ����
	            // dialog.dismiss();
	            // super.onPostExecute(result);
			}
	    }
		
	
	}   
	
	
	
			
	
	 
    
}
