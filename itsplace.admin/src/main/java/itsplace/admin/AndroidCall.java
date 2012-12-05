package itsplace.admin;

import org.apache.cordova.DroidGap;



import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

public class AndroidCall  extends Activity {
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
		  Intent intent = new Intent(AndroidCall.this, NfcActivity.class);
	      intent.putExtra("message", message);
	    //  startActivityForResult(intent,1);
	   //   startActivity(new Intent(MainActivity.this,(NfcActivity.class)));	
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		if(requestCode == 1){
			
		}
	}
}
