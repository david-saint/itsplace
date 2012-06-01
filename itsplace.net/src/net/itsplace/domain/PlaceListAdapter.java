/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.itsplace.domain;

import itsplace.net.PlaceActivity;
import itsplace.net.R;
import itsplace.net.map.DaumMapActivity;
import itsplace.net.place.PlaceViewActivity;
import itsplace.net.util.L;

import java.util.ArrayList;
import java.util.List;

import com.fedorvlasov.lazylist.ImageLoader;


import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Roy Clarkson
 * @author Pierre-Yves Ricau
 */
public class PlaceListAdapter extends BaseAdapter implements OnClickListener {
	protected static final String TAG = PlaceListAdapter.class.getSimpleName();
    private List<Place> placeList;
    private final LayoutInflater layoutInflater;
    private Context context;
    private ImageLoader imageLoader; 
    
    public PlaceListAdapter(Context context){
    	this.placeList = new ArrayList<Place>();
    	this.layoutInflater = LayoutInflater.from(context);
    	this.context = context;
    	this.imageLoader=new ImageLoader(context);
    }
    
    public void addStatesListAdapter(List<Place> placeList) {
        this.placeList.addAll(placeList);
        notifyDataSetChanged();//refresh 해준다 
    }

    public int getCount() {
        return placeList != null ? placeList.size() : 0;
    }

    public Place getItem(int position) {
        return placeList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list, parent, false);
        }
        
        Place f = getItem(position);
        if (f != null) {
        	TextView t = (TextView) convertView.findViewById(R.id.state_name);
            t.setText(f.getFname()+"kk");
            TextView t2 = (TextView) convertView.findViewById(R.id.state_name2);
            t2.setText("t2t2");
            
            ImageView imageView=(ImageView)convertView.findViewById(R.id.image);        
            
            imageLoader.DisplayImage(f.getImageHost()+f.getFileName(), imageView);
            
           // imageLoader.DisplayImage("http://a3.twimg.com/profile_images/670625317/aam-logo-v3-twitter.png", image);
        }
        convertView.setTag(position);
        convertView.setOnClickListener(this);
        return convertView;
    }
  

	@Override
	public void onClick(View view) {
		 int position = (Integer) view.getTag();
		 Place f = getItem(position);
		  if(f == null){
			  L.i(TAG, "가맹점 널" );
		  }else{
			  Intent intent = new Intent(context,PlaceViewActivity.class);
			  intent.putExtra("latitude", f.getLatitude());
			  intent.putExtra("longitude", f.getLongitude());
			  intent.putExtra("image", "http://itsplace.net/resources/images/"+f.getFileName());
			  intent.putExtra("place",f);
			  L.i(TAG, ""+position+f.toString() );
			  
			  context.startActivity(intent);
		  }
			// Toast.makeText( getApplication(),  ""+f.getName() ,Toast.LENGTH_LONG).show();
		
	}

	

}
