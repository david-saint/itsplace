package org.springframework.android.basicauth;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;

public class AbsTest extends SherlockActivity {
	protected static final String TAG = MainActivity.class.getSimpleName();
	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_layout);
		  
	}
}
