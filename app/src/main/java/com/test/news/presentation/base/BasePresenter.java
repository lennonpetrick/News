package com.test.news.presentation.base;

public abstract class BasePresenter<T extends BaseContract.View>
        implements BaseContract.Presenter<T> {

    protected T mView;

    @Override
    public void setView(T view) {
        mView = view;
    }

    @Override
    public void destroy() {
        mView = null;
    }
}
