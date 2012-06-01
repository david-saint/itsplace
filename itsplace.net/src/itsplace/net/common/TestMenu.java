package itsplace.net.common;



import itsplace.net.PlaceActivity;
import itsplace.net.MainActivity;
import itsplace.net.R;
import itsplace.net.util.L;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TestMenu   extends RelativeLayout {
	public static final String TAG = TestMenu.class.getSimpleName();

	//protected ImageView logo;
	private TextView label;
	private ImageButton loginButton;

	public TestMenu(Context context) {
	    super(context);
	}

	public TestMenu(Context context, AttributeSet attrs) {
	    super(context, attrs);
	}

	public TestMenu(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	}

	public void initHeader() {
	        inflateHeader();
	}

	private void inflateHeader() {
	    LayoutInflater inflater = (LayoutInflater) getContext()
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    inflater.inflate(R.layout.itsplace_header, this);
	    //logo = (ImageView) findViewById(R.id.logo);
	    label = (TextView) findViewById(R.id.header_title);
	    loginButton = (ImageButton) findViewById(R.id.btn_Search);
	     final Intent intent = new Intent(getContext(), PlaceActivity.class);
	    loginButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				label.setText("dddd");
				getContext().startActivity(intent);
			}
	    });
	}
	
}