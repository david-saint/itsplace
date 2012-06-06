package itsplace.net.common;
import java.util.List;
import java.util.SimpleTimeZone;

import itsplace.net.PlaceActivity;
import itsplace.net.QRcodeActivity;
import itsplace.net.R;
import itsplace.net.MyStampActivity;
import itsplace.net.StampActivity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuItem;


import com.actionbarsherlock.view.Menu;

import com.actionbarsherlock.view.SubMenu;

public class ActionBarMenu {
	protected static final String TAG = PlaceActivity.class.getSimpleName();
	private ActionMode actionMode;
	private Context context;
	public void createMenu(Menu menu){
		 SubMenu sub2 = menu.addSubMenu(0,100,0,"설정");
		    sub2.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	        sub2.getItem().setIcon(R.drawable.gear);
	        
	        SubMenu sub = menu.addSubMenu("Theme");
	        sub.add(0, 10, 0, "Search Place");
	        sub.add(0, 20, 1, "qr");
	        sub.add(0, 30, 2, "나의스탬프");
	        sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	        sub.getItem().setIcon(R.drawable.flag);
	        
	        SubMenu sub3 = menu.addSubMenu("Search");
	        
	        sub3.getItem().setIcon(R.drawable.ic_search);
	       // menu.add("Search")
           // //.setIcon(R.drawable.ic_search)
            sub3.getItem().setActionView(R.layout.search_edittext);
            sub3.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
	}
	public boolean selectMenuEvent(ActionBarSherlock abs,Context context, MenuItem item){
		// Toast.makeText(, " item.getItemId()"+ item.getItemId()+"----Theme changed to \"" + item.getTitle() + "\"", Toast.LENGTH_SHORT).show();
		 
		 ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
	   	  List<RunningTaskInfo> Info = am.getRunningTasks(1);
	   	  ComponentName topActivity = Info.get(0).topActivity;
	   	  String topactivityname = topActivity.getPackageName()+topActivity.getClassName()+topActivity.getShortClassName();
	   	  
		 this.context = context;
		 Log.i(TAG,"ddddddddddddd--------1");
		 if(item.getItemId()==100){
			 Log.i(TAG,"ddddddddddddd--------2");
			// SherlockActivity sa = (SherlockActivity) context;// 셜록을 상속받아야한다 무조건?
			 Log.i(TAG,"ddddddddddddd--------3");
	   	  		actionMode = abs.startActionMode(new AnActionModeOfEpicProportions());
	   	  	  }
		  if (item.getItemId() == android.R.id.home || item.getItemId() == 0) {
	            return false;
	      }else{
	    	 
	    	  Log.i(TAG,"topactivityname--------->"+topactivityname);
	    	 
	    	  
	    	  Log.i(TAG,"--------->"+topActivity.getShortClassName());	    		  
    		  Log.i(TAG," --------->"+PlaceActivity.class.getSimpleName());
    		  
    		  if(item.getItemId()==10){
    			  if(topActivity.getShortClassName().equals("."+PlaceActivity.class.getSimpleName())){
    	    		     		  
    	    	  }else{
    	    		  Intent intent = new Intent(context, PlaceActivity.class);
    		    	  context.startActivity(intent);  
    	    	  }  
    		  }else if(item.getItemId()==20){
    			  
    			  if(topActivity.getShortClassName().equals("."+StampActivity.class.getSimpleName())){
    	    		  		  
    	    	  }else{
    	    		  Intent intent = new Intent(context, QRcodeActivity.class);
    		    	  context.startActivity(intent);  
    	    	  }  
    		  }else if(item.getItemId()==30){
    			  Log.i(TAG,"테스--------30");
    			  Intent intent = new Intent(context, MyStampActivity.class);
		    	  context.startActivity(intent);
    		  }
	    	  
	    	 
	    	  return true;
	      }
	}
	
	 private final class AnActionModeOfEpicProportions implements ActionMode.Callback {
	        @Override
	        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	          
	            menu.add("Save")
	                .setIcon( R.drawable.ic_compose)
	                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

	            menu.add("Search")
	                .setIcon( R.drawable.ic_search)
	                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


	            return true;
	        }

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				//Toast.makeText(context, "Got click: " + item, Toast.LENGTH_SHORT).show();
	            mode.finish();
	            return true;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub
				
			}
	 }
}
