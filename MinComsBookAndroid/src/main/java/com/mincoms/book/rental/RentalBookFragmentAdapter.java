package com.mincoms.book.rental;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mincoms.domain.BookRental;
import com.mincoms.domain.UserInfo;
import com.mincoms.main.R;
import com.mincoms.restful.RestClient;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class RentalBookFragmentAdapter extends FragmentStatePagerAdapter  {
    protected static final String TAG = RentalBookFragmentAdapter.class.getSimpleName();
    private List<BookRental> bookRentalList;
	private Context context;
   
    public RentalBookFragmentAdapter(FragmentManager fm) {
    	super(fm);
    	Log.i(TAG,"어댑터 생성자");
    	Log.i(TAG,"어댑터 생성자");
    	Log.i(TAG,"어댑터 생성자");
    	 this.bookRentalList = new ArrayList();
    
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        //return MyStampFragmentAdapter.CONTENT[position % CONTENT.length];
    	Log.i(TAG,"gettitle position:"+ position);
    	Log.i(TAG,"gettitle:"+ bookRentalList.get(position).getBookInfo().getTitle());
    	return bookRentalList.get(position).getBookInfo().getTitle().substring(0, 10) + "...";
    
    }
    
    @Override
    public Fragment getItem(int position) {
    		Log.i(TAG,"getitem");
    	
    //	return RentalBookFragment.newInstance(this.bookRentalList.get(position));
    return new RentalBookFragment(this.bookRentalList.get(position));
    	
    }

    @Override
    public int getCount() {
        //return mCount;
    	Log.i(TAG,"getCount:"+bookRentalList.size());
    	return bookRentalList!= null ? bookRentalList.size(): 0;
    	
    }

   
    
    public void addBookRentalList(List<BookRental> bookRentalList){
    	if(bookRentalList.size()>=0){
    		removeBookRentalList();
    	}
		this.bookRentalList.addAll(bookRentalList);
	}
    public void removeBookRentalList(){
  		this.bookRentalList.clear();
  		this.bookRentalList = new ArrayList();
  	}
    
    
}