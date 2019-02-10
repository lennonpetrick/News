package com.test.news.presentation.base;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {

    //private ProgressDialog mDialog;

    @Override
    public void showLoading() {
        hideLoading();
        /*mDialog = new ProgressDialog.Builder(this)
                .setMessage(getString(R.string.progress_please_wait))
                .setCancelable(false)
                .show();*/
    }

    @Override
    public void hideLoading() {
        /*if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }*/
    }

    @Override
    public void showError(String message) {
        hideLoading();

        if (TextUtils.isEmpty(message)) {
            //message = getString(R.string.error_default);
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
