package itsplace.net.common;
import java.util.List;

import itsplace.net.FranchiserActivity;
import itsplace.net.R;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.view.MenuItem;


import com.actionbarsherlock.view.Menu;

import com.actionbarsherlock.view.SubMenu;

public class ActionBarMenu {
	protected static final String TAG = FranchiserActivity.class.getSimpleName();
	public void createMenu(Menu menu){
		 SubMenu sub2 =menu.addSubMenu("tet");
		    sub2.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	        sub2.getItem().setIcon(R.drawable.ic_compose);
	        
	        SubMenu sub = menu.addSubMenu("Theme");
	        sub.add(0, 11111111, 0, "Default");
	        sub.add(0, 2222, 1, "Light");
	        sub.add(0, R.style.Theme_Sherlock_Light_DarkActionBar, 0, "Light (Dark Action Bar---------------)");
	        sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	        sub.getItem().setIcon(R.drawable.ic_title_share_default);
	}
	public boolean selectMenuEvent(Context context, MenuItem item){
		  if (item.getItemId() == android.R.id.home || item.getItemId() == 0) {
	            return false;
	      }else{
	    	  ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
	    	  List<RunningTaskInfo> Info = am.getRunningTasks(1);
	    	  ComponentName topActivity = Info.get(0).topActivity;
	    	  String topactivityname = topActivity.getPackageName()+topActivity.getClassName()+topActivity.getShortClassName();
	    	  Log.i(TAG,"topactivityname--------->"+topactivityname);
	    	  Toast.makeText(context, " item.getItemId()"+ item.getItemId()+"----Theme changed to \"" + item.getTitle() + "\"", Toast.LENGTH_SHORT).show();
	    	  
	    	  
	    	  Log.i(TAG,"--------->"+topActivity.getShortClassName());	    		  
    		  Log.i(TAG," --------->"+FranchiserActivity.class.getSimpleName());
	    	  if(topActivity.getShortClassName().equals("."+FranchiserActivity.class.getSimpleName())){
	    		  Log.i(TAG,"같으니까 실행하지 마라 --------->"+topActivity.getShortClassName());	    		  
	    		  Log.i(TAG,"같으니까 실행하지 마라 --------->"+FranchiserActivity.class.getSimpleName());	    		  
	    	  }else{
	    		  Intent intent = new Intent(context, FranchiserActivity.class);
		    	  context.startActivity(intent);  
	    	  }
	    	 
	    	  return true;
	      }
	}
}
