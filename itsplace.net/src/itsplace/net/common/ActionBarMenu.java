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

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuItem;


import com.actionbarsherlock.view.Menu;

import com.actionbarsherlock.view.SubMenu;

public class ActionBarMenu {
	protected static final String TAG = FranchiserActivity.class.getSimpleName();
	private ActionMode actionMode;
	private Context context;
	public void createMenu(Menu menu){
		 SubMenu sub2 = menu.addSubMenu(0,100,0,"설정");
		    sub2.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	        sub2.getItem().setIcon(R.drawable.gear);
	        
	        SubMenu sub = menu.addSubMenu("Theme");
	        sub.add(0, 10, 0, "Search Place");
	        sub.add(0, 20, 1, "Light");
	        sub.add(0, 30, 0, "Light (Dark Action Bar---------------)");
	        sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	        sub.getItem().setIcon(R.drawable.flag);
	        
	        SubMenu sub3 = menu.addSubMenu("Search");
	        
	        sub3.getItem().setIcon(R.drawable.ic_search);
	       // menu.add("Search")
           // //.setIcon(R.drawable.ic_search)
            sub3.getItem().setActionView(R.layout.search_edittext);
            sub3.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
	}
	public boolean selectMenuEvent(Context context, MenuItem item){
		 Toast.makeText(context, " item.getItemId()"+ item.getItemId()+"----Theme changed to \"" + item.getTitle() + "\"", Toast.LENGTH_SHORT).show();
		 this.context = context;
		 if(item.getItemId()==100){
			 SherlockActivity sa = (SherlockActivity) context;
	   	  		actionMode = sa.startActionMode(new AnActionModeOfEpicProportions());
	   	  	  }
		  if (item.getItemId() == android.R.id.home || item.getItemId() == 0) {
	            return false;
	      }else{
	    	  ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
	    	  List<RunningTaskInfo> Info = am.getRunningTasks(1);
	    	  ComponentName topActivity = Info.get(0).topActivity;
	    	  String topactivityname = topActivity.getPackageName()+topActivity.getClassName()+topActivity.getShortClassName();
	    	  Log.i(TAG,"topactivityname--------->"+topactivityname);
	    	 
	    	  
	    	  Log.i(TAG,"--------->"+topActivity.getShortClassName());	    		  
    		  Log.i(TAG," --------->"+FranchiserActivity.class.getSimpleName());
    		  
    		  if(item.getItemId()==10){
    			  if(topActivity.getShortClassName().equals("."+FranchiserActivity.class.getSimpleName())){
    	    		  Log.i(TAG,"같으니까 실행하지 마라 --------->"+topActivity.getShortClassName());	    		  
    	    		  Log.i(TAG,"같으니까 실행하지 마라 --------->"+FranchiserActivity.class.getSimpleName());	    		  
    	    	  }else{
    	    		  Intent intent = new Intent(context, FranchiserActivity.class);
    		    	  context.startActivity(intent);  
    	    	  }  
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
