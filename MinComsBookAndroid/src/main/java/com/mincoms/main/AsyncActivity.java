
package com.mincoms.main;



public interface AsyncActivity {

    public MainApplication getApplicationContext();

    public void showLoadingProgressDialog();

    public void showProgressDialog(CharSequence message);

    public void dismissProgressDialog();

}
