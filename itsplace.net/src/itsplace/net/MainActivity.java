package itsplace.net;

import itsplace.net.common.TestMenu;
import itsplace.net.map.DaumMapActivity;
import itsplace.net.user.LoginActivity;
import itsplace.net.user.SignUpActivity;
import itsplace.net.util.L;

import com.mobyfactory.uiwidgets.DemoActivity1;
import com.mobyfactory.uiwidgets.DemoActivity2;
import com.mobyfactory.uiwidgets.RadioStateDrawable;
import com.mobyfactory.uiwidgets.ScrollableTabActivity;
import com.mobyfactory.uiwidgets.ScrollableTabActivity.SliderBarActivityDelegate;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends ScrollableTabActivity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.i("MainActivity", "MainActivity start");
       
		TestMenu header = (TestMenu) findViewById(R.id.header);
	    header.initHeader();
        /*
         * set this activity as the tab bar delegate
         * so that onTabChanged is called when users tap on the bar
         */
        setDelegate(new SliderBarActivityDelegateImpl());
        
        /*
         * optional
         * set default off and on color shades of tab bar button
         * if not specified, opaque areas are shaded gray in off state, and yellow in on state 
         */
     //   setDefaultShade(RadioStateDrawable.SHADE_BLUE, RadioStateDrawable.SHADE_GREEN);
        
       // for (int i=0; i<14; i++)
       // {
        	 
        	Intent intent = new Intent(this, DemoActivity2.class);
        	this.addTab("PBook", R.drawable.icon, intent);//나의스탬프
        	 
        	intent = new Intent(this, SignUpActivity.class);
        	this.addTab("Places", R.drawable.ic_search, intent);//내주변 가맹점
        	
        	intent = new Intent(this, StampActivity.class);
        	this.addTab("Stamp", R.drawable.rss, intent);//Qrcode 찍으면 메뉴 -> 스탬프 받는 방법
        	
        	intent = new Intent(this, FranchiserActivity.class);
        	this.addTab("Event", R.drawable.calendar, intent);//알림 이벤트 등
        	
        	intent = new Intent(this, DaumMapActivity.class);
        	this.addTab("MapActivity", R.drawable.star, intent);
        	
        	/*
        	 * This adds a title, and an image to the tab bar button
        	 * Image should be a PNG file with transparent background.
        	 * By default, opaque areas are shaded gray in off state, and yellow in on state 
        	 */
        
        	
    //    }
        
        /*
         * commit is required to redraw the bar after add tabs are added
         * if you know of a better way, drop me your suggestion please.
         */
        commit();
    }
 
    private class SliderBarActivityDelegateImpl extends SliderBarActivityDelegate
    {
    	/*
    	 * Optional callback method
    	 * called when users tap on the tab bar button
    	 */
    	protected void onTabChanged(int tabIndex) 
    	{
    		Log.d("onTabChanged",""+tabIndex);
    	}
    }
}
