package itsplace.net.stamp;

import itsplace.net.R;
import itsplace.net.StampActivity;
import itsplace.net.common.DateDeserializer;
import itsplace.net.connection.RestClient;
import itsplace.net.connection.RestClient.RequestMethod;

import itsplace.library.restful.AsyncClient;
import itsplace.library.util.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Stamp;
import net.itsplace.domain.User;
import net.itsplace.domain.StampFragmentAdpter.ItemFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class MyStampFragmentAdapter extends FragmentPagerAdapter {
    protected static final String TAG = MyStampFragmentAdapter.class.getSimpleName();
    private List<PlaceStamp> stampList;
    private User user;
	private Context context;
    public MyStampFragmentAdapter(FragmentManager fm,User user,Context context) {
        super(fm);
        L.i(TAG, "MyStampFragmentAdapter생성");
        this.stampList = new ArrayList();
        this.user = user;
        this.context = context;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        //return MyStampFragmentAdapter.CONTENT[position % CONTENT.length];
    	return stampList.get(position).getPlace().getFname();
    }
    @Override
    public Fragment getItem(int position) {
      //  return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
    	L.i(TAG, "Fragment getItem position:"+position);
    	
    	//if(stampList == null){
		//	L.i(TAG, "stampList null=============");
    	  new getStamped().execute(this.stampList.get(position)); 
			return MyStampFragment.newInstance(this.stampList.get(position).getFid(),this.stampList.get(position).getStampid() ,user.getEmail());
    	//}
//			return  new MyStampFragment();
//		}else{
//			return  new MyStampFragment(this.stampList.get(position).getFid(),this.stampList.get(position).getStampid() ,user, context);
//		}
    }

    @Override
    public int getCount() {
        //return mCount;
    	 return stampList!= null ? stampList.size(): 1;
    }

   
    
    public void addStampList(List<PlaceStamp> stampList){
		Log.i(TAG,"적립된 가맹점 리스트 프래그먼트 삽입 ");
		this.stampList.addAll(stampList);
	}
    
    private class getStamped extends AsyncTask<PlaceStamp, Void, List<Stamp>> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<Stamp> doInBackground(PlaceStamp... params) {
        	final String url = context.getString(R.string.base_uri) + "/stamp/stamppedList";
			Log.i(TAG, url);
			
			RestClient restClient = new RestClient(url);
			List<Stamp> stampList = new ArrayList();
			try {
				PlaceStamp placeStamp = params[0];
					Log.i(TAG, "헐 fid"+Integer.toString(placeStamp.getFid()));
					Log.i(TAG, "헐 stampid"+Integer.toString(placeStamp.getStampid()));
				
				restClient.AddParam("fid", Integer.toString(placeStamp.getFid()));
				restClient.AddParam("email", "faye12005@gmail.com");
				restClient.AddParam("stampid", Integer.toString(placeStamp.getStampid()));
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
		
				setStampList(result);
			
		}
    }
    public void setStampList(List<Stamp> stampList){
    
    }
}