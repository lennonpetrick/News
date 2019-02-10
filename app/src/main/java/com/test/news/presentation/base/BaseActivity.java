package com.test.news.presentation.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.test.news.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends BaseContract.Presenter>
        extends AppCompatActivity implements BaseContract.View {

    private ProgressDialog mDialog;

    @Inject
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.load();
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        mPresenter.destroy();
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        hideLoading();

        mDialog = new ProgressDialog(this);
        mDialog.setMessage(getString(R.string.progress_please_wait));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @Override
    public void showError(String message) {
        hideLoading();

        if (TextUtils.isEmpty(message)) {
            message = getString(R.string.error_default);
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
