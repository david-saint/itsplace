package itsplace.admin;

import org.apache.cordova.DroidGap;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.ActionBarSherlock.OnCreateOptionsMenuListener;
import com.actionbarsherlock.ActionBarSherlock.OnOptionsItemSelectedListener;
import com.actionbarsherlock.app.SherlockActivity;


import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import com.actionbarsherlock.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import itsplace.library.util.*;
public class MainActivity  extends DroidGap implements OnCreateOptionsMenuListener, OnOptionsItemSelectedListener {
    ActionBarSherlock mSherlock = ActionBarSherlock.wrap(this);
	protected static final String TAG = MainActivity.class.getSimpleName();
	//private ActionBarMenu actionBarMenu;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {        
		MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.search_view, menu);
        
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView)item.getActionView();
        
	    if(searchView == null){
	    	Log.i(TAG,"널이다 서치뷰");
	    }else{
	    	Log.i(TAG,"널이 아니다 서치뷰");
	    	//searchView.setSubmitButtonEnabled(true);
	    	searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
	            @Override public boolean onQueryTextSubmit(String s) {
	            	Log.i(TAG,"검색:"+s);
	                return true;
	              }
	
	              @Override public boolean onQueryTextChange(String s) {
	            	  Log.i(TAG,"검색 타이핑:"+s);
	                return false;
	              }
	            });
	    }
     
        menu.add("Refresh")
            .setIcon( R.drawable.ic_refresh)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        return mSherlock.dispatchCreateOptionsMenu(menu);
    }
    @Override
    
	public boolean onOptionsItemSelected(MenuItem item) {
		return true;
	}
   
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
    	//Toast.makeText(this, "Got click: " + item.getItemId(), Toast.LENGTH_SHORT).show();
    	switch (item.getItemId()) {
        case 20:
        	Toast.makeText(this, "Got click: " + item.toString(), Toast.LENGTH_SHORT).show();
        	//serachText = (EditText) item.getActionView(); //api 11 이상
        	
        	//serachText.addTextChangedListener(filterTextWatcher);
        //	serachText.requestFocus();
         //   InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
         //   imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    	}
    	 startActivity(new Intent(this,(NfcActivity.class)));	
        return mSherlock.dispatchOptionsItemSelected(item);
    }
  


    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Theme_example);
    	super.setBooleanProperty("showTitle",true); // 액션바 애드전에 호출해야함
    	
        super.onCreate(savedInstanceState);
      //  EditText editText = (EditText)findViewById(R.id.search);
      //  editText.setText("dddd");
     //   AndroidCall androidCall = new AndroidCall(this, super.appView); 
        //setContentView(R.layout.main);
        
    
     super.loadUrl("file:///android_asset/www/index.html");
     //   super.loadUrl("http://10.0.2.2:8080");
      //  super.loadUrl("http://naver.com");
        super.appView.setVerticalScrollBarEnabled(true);
    	super.appView.setHorizontalScrollBarEnabled(false);
    	
       // super.appView.addJavascriptInterface(androidCall, "AndroidCall");
        super.appView.addJavascriptInterface(this, "AndroidCall");
        
     //   mSherlock.setUiOptions(ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW); // 안드로이드 버전이 낮으면 액션바가 화면화단에 붙는다 
        mSherlock.getActionBar().setHomeButtonEnabled(true);
        mSherlock.getActionBar().setDisplayShowTitleEnabled(false);
      
    }
    public void show(String fid){
		//Toast.makeText(mGap, message, Toast.LENGTH_LONG).show();
    	try{
    		  Intent intent = new Intent(this, NfcActivity.class);
    	      intent.putExtra("nfckey", Encrypt.encrypt("itsplace",fid));
    	      startActivityForResult(intent,1);
    	      
    	}catch(Exception e){
    		
    	}
	
    
    	Toast.makeText(this, "Got click", Toast.LENGTH_SHORT).show();
	    //  startActivity(new Intent(MainActivity.this,(NfcActivity.class)));	
	}
	

}

