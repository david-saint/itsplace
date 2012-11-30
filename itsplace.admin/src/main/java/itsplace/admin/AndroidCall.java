package itsplace.admin;

import org.apache.cordova.DroidGap;

import android.webkit.WebView;
import android.widget.Toast;

public class AndroidCall {
	 private WebView mAppView;
	    private DroidGap mGap;

	    public AndroidCall(DroidGap gap, WebView view)
	    {
	        mAppView = view;
	        mGap = gap;
	    }
		public String testMethod(){
    		return "dddddddddd";
   	    	//Toast.makeText(mGap, "init`````````````````1", Toast.LENGTH_LONG).show();
   	    }
		public void show(String message){
			Toast.makeText(mGap, message, Toast.LENGTH_LONG).show();
		}
}
