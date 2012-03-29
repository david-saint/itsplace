
package itsplace.net.common;

import itsplace.net.MainApplication;


public interface AsyncActivity {

    public MainApplication getApplicationContext();

    public void showLoadingProgressDialog();

    public void showProgressDialog(CharSequence message);

    public void dismissProgressDialog();

}
