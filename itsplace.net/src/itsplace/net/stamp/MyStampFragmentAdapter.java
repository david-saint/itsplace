package itsplace.net.stamp;

import itsplace.net.StampActivity;
import itsplace.net.util.L;

import java.util.ArrayList;
import java.util.List;


import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.User;
import net.itsplace.domain.StampFragmentAdpter.ItemFragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class MyStampFragmentAdapter extends FragmentPagerAdapter {
    protected static final String TAG = MyStampFragmentAdapter.class.getSimpleName();
    private List<PlaceStamp> stampList;
    private User user;
	private Context context;
    public MyStampFragmentAdapter(FragmentManager fm,User user,Context context) {
        super(fm);
        L.i(TAG, "MyStampFragmentAdapter생성");
        this.stampList = new ArrayList();
        this.user = user;
        this.context = context;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        //return MyStampFragmentAdapter.CONTENT[position % CONTENT.length];
    	return stampList.get(position).getPlace().getFname();
    }
    @Override
    public Fragment getItem(int position) {
      //  return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
    	L.i(TAG, "Fragment getItem position:"+position);
    	
    	//if(stampList == null){
		//	L.i(TAG, "stampList null=============");
		
			return MyStampFragment.newInstance(this.stampList.get(position).getFid(),this.stampList.get(position).getStampid() ,user.getEmail());
    	//}
//			return  new MyStampFragment();
//		}else{
//			return  new MyStampFragment(this.stampList.get(position).getFid(),this.stampList.get(position).getStampid() ,user, context);
//		}
    }

    @Override
    public int getCount() {
        //return mCount;
    	 return stampList!= null ? stampList.size(): 1;
    }

   
    
    public void addStampList(List<PlaceStamp> stampList){
		Log.i(TAG,"적립된 가맹점 리스트 프래그먼트 삽입 ");
		this.stampList.addAll(stampList);
	}
}