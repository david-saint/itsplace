package com.mincoms.main;



import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HelloAndroidActivity extends SherlockActivity{
	protected static final String TAG = HelloAndroidActivity.class.getSimpleName();
	private ActionBarMenu actionBarMenu;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		actionBarMenu.createMenu(menu);

		
	       
	    return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      
    	return actionBarMenu.selectMenuEvent(getSherlock(),this, item);
    }
   
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG,"onCreate ");
		
        super.onCreate(savedInstanceState);
        actionBarMenu = new ActionBarMenu();
	}
	
  
}
