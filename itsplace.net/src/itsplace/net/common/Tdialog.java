package itsplace.net.common;

import itsplace.net.R;
import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;



public class Tdialog {
	  protected static final String TAG = Tdialog.class.getSimpleName();
	  private static Dialog dialog = null;
	  public static void show(Context context){
		  if (dialog == null) {
			    dialog = new Dialog(context, R.style.TransparentDialog);//TransparentDialog.xml
				ProgressBar pb = new ProgressBar(context);
				LayoutParams params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				dialog.addContentView(pb, params);
				dialog.setCancelable(false);
			}

		  dialog.show();
	  }
	  public static void hide(Context context){
		  if (dialog != null) {
			  dialog.dismiss();
			  dialog = null;
			}
	  }
}
