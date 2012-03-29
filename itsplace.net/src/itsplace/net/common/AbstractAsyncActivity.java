
package itsplace.net.common;

import itsplace.net.MainApplication;
import itsplace.net.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;


public abstract class AbstractAsyncActivity extends Activity implements AsyncActivity {

    protected static final String TAG = AbstractAsyncActivity.class.getSimpleName();

   // private ProgressDialog progressDialog;
    private Dialog progressDialog;
    private ProgressBar pb;;
    private boolean destroyed = false;

    // ***************************************
    // Activity methods
    // ***************************************
    @Override
    public MainApplication getApplicationContext() {
        return (MainApplication) super.getApplicationContext();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyed = true;
    }

    // ***************************************
    // Public methods
    // ***************************************
    public void showLoadingProgressDialog() {
        this.showProgressDialog("Loading. Please wait...");
    }
  
    public void showProgressDialog(CharSequence message) {
        if (progressDialog == null) {
        	progressDialog = new Dialog(this, R.style.TransparentDialog);//TransparentDialog.xml
        	
				ProgressBar pb = new ProgressBar(this);// 원형 프로그레스바
        	/*pb = new ProgressBar(this,null, android.R.attr.progressBarStyleHorizontal);
        	pb.setMax(100);
        	LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,4);
        	*/
        	
				LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	
				progressDialog.addContentView(pb, params);
				progressDialog.setCancelable(false);
				
				
        }

       // progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && !destroyed) {
            progressDialog.dismiss();
        }
    }

	public void setProgressBar(Integer i) {
		this.pb.setProgress(i);
	}

}
