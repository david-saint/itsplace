package itsplace.net;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class QRcodeActivity extends Activity {
	protected static final String TAG = QRcodeActivity.class.getSimpleName();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent = new Intent("net.itsplace.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent,1);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode == 0)
        {
              if(resultCode == RESULT_OK)
              {
                     String contents = intent.getStringExtra("SCAN_RESULT");
                     Log.i(TAG,contents);
                     Intent _intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                                          (contents));
                     startActivity(_intent);
              }
              else if(resultCode == RESULT_CANCELED)
              {
                     Toast.makeText(QRcodeActivity.this, "Cancel", 
                                    Toast.LENGTH_SHORT).show();
              }
        }
    }
}
