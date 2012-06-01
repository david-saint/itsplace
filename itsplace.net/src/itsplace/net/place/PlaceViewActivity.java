package itsplace.net.place;

import net.itsplace.domain.Place;

import com.fedorvlasov.lazylist.ImageLoader;

import itsplace.net.R;
import itsplace.net.common.AbstractAsyncActivity;
import itsplace.net.map.DaumMapActivity;
import itsplace.net.util.L;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class PlaceViewActivity extends  AbstractAsyncActivity {
    
	protected static final String TAG = PlaceViewActivity.class.getSimpleName();
	   
	private WebView webView;
	private ImageLoader imageLoader; 
	Location  gpsLocation=null;
	Location networkLocation=null;
	LocationManager locationManager;
	String currentLocation="";
	String currentAddress="";
	
	
	private final int ACTIVITY_REQUEST_CODE_QRCODE	= 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	 super.onCreate(savedInstanceState);    	
    	 setContentView(R.layout.franchiser_view_activity);  
    	
    	 imageLoader=new ImageLoader(this);
    	 ImageView image=(ImageView)findViewById(R.id.fImage);       
    	   String t = getIntent().getStringExtra("image");
        // imageLoader.DisplayImage(getIntent().getStringExtra("image"), image);
    	   Bitmap bm = BitmapFactory.decodeResource(getResources(), 
                   R.drawable.stamp);
           image.setImageBitmap(getRoundedCornerBitmap(bm, 10));
           
           Place place = (Place) getIntent().getSerializableExtra("place");
           Log.i(TAG,place.getFname());
           Log.i(TAG,place.getFname());
           Log.i(TAG,place.getFname());
           Log.i(TAG,place.getFname());
    	 webView = (WebView)findViewById(R.id.webView);
        
    	 final String MAP_URL = getString(R.string.map_uri);
         webView.loadUrl(MAP_URL);
         
        
         webView.setHorizontalScrollBarEnabled(false);
         webView.setVerticalScrollBarEnabled(false);
         webView.setVerticalScrollbarOverlay(true);
         webView.setHorizontalScrollbarOverlay(true);
        
    //     L.i(TAG,  getIntent().getStringExtra("latitude")+getIntent().getStringExtra("image"));

         //터치햇을때 스크롤 살리기
         webView.setWebViewClient(new WebViewClient(){ 
        	 @Override
        	 public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		 if (url.startsWith("sms:")) {
        		     Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
        		     startActivity(i);
        		     return true;
        		    }
        		    if (url.startsWith("tel:")) {
        		     Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(url));
        		     startActivity(i);
        		     return true;
        		    }
        		    if (url.startsWith("mailto:")) {
        		     Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
        		     startActivity(i);
        		     return true;
        		   }
	        	 view.loadUrl(url);//
	        	 return true;
        	 }
        	
         });
         
         WebSettings set = webView.getSettings();
         set.setJavaScriptEnabled(true);
         set.setBuiltInZoomControls(false);
         
         //자바스크립트에서 안드로이드 콜은 클래스를 등록하고 등록한 클래스 알리아스로 메소드를 호출한다 windows.CallAndroid + 안드로이드함수명 
     //    webView.addJavascriptInterface(new CallAndroid(), "CallAndroid");
         
        
     
        
    }
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);


        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
  
    	
}