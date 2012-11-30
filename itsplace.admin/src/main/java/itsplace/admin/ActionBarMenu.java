package itsplace.admin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuItem;


import com.actionbarsherlock.view.Menu;

import com.actionbarsherlock.view.SubMenu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ActionBarMenu {
	
	protected static final String TAG = ActionBarMenu.class.getSimpleName();
	
	private ActionMode actionMode;
	private Context context;
	private Menu menu;
	public void createMenu(Menu menu){
		this.menu = menu;
		SubMenu sub1 = menu.addSubMenu(0,20,1,"도서등록");
		sub1.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		sub1.getItem().setIcon(R.drawable.gear);
		
		
        SubMenu sub2 = menu.addSubMenu(1, 40, 2,"사용자메뉴");
        sub2.add(1, 10, 0, "로그아웃");
        sub2.add(1, 30, 2, "도서대출");
        sub2.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        sub2.getItem().setIcon(R.drawable.flag);
        
        SubMenu sub3 = menu.addSubMenu(200,300,2,"refresh");
        sub3.getItem().setIcon(R.drawable.ic_refresh);
       // menu.add("Search")
       // //.setIcon(R.drawable.ic_search)
       // sub3.getItem().setActionView(R.layout.search_edittext);
        sub3.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
	}
	public boolean selectMenuEvent(ActionBarSherlock abs, Context context, MenuItem item){
		 
		 ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
	   	 List<RunningTaskInfo> Info = am.getRunningTasks(1);
	   	 ComponentName topActivity = Info.get(0).topActivity;
	   	 String topactivityname = topActivity.getPackageName()+topActivity.getClassName()+topActivity.getShortClassName();
	   	  
		 this.context = context;
		 
		 
		 if(item.getItemId()==100){
	   	  		//actionMode = abs.startActionMode(new AnActionModeOfEpicProportions());
	   	 }
		 
		 if(item.getItemId()==300){
			 
			 Intent intent = new Intent("com.mincoms.refresh");
			 intent.setData(Uri.parse("sample:"));
			 context.sendBroadcast(intent);
		 }
		 
		 if (item.getItemId() == android.R.id.home || item.getItemId() == 0) {
	        return false;
	     }else{
	    	 
	    	  Log.i(TAG,"topactivityname--------->"+topactivityname);
	    	  Log.i(TAG,"--------->"+topActivity.getShortClassName());	    		  
    		  
    		  if(item.getItemId()==10){
    		//	  new LogOutAsyncTask().execute();
    		  }else if(item.getItemId()==20){//도서등록
			  
	    	//	  Intent intent = new Intent(context, BookRegisterActivity.class);
		    //	  context.startActivity(intent);  
    		  }else if(item.getItemId()==30){//대출
    			  
	    		//  Intent intent = new Intent(context, BookRentalActivity.class);
		    	//  context.startActivity(intent);
    	    	   
    		  }else if(item.getItemId()==40){

	    		  //Intent intent = new Intent(context, TestActivity.class);
		    	  //context.startActivity(intent);
    			 
    		  }
	    	  
	    	 
	    	  return true;
	      }
	}
	public void remove(int id){
		if(menu!=null){ Log.i(TAG,"사용자 권한 리무브");
			//this.menu.findItem(200).getSubMenu().removeItem(id); // 
			this.menu.removeItem(20);
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
				Toast.makeText(context, "Got click: " + item, Toast.LENGTH_SHORT).show();
	            mode.finish();
	            return true;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub
				
			}
	 }
	 
	
}
