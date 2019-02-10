package com.test.news.presentation.sources;

import com.test.news.presentation.base.BasePresenter;

import javax.inject.Inject;

public class SourcesPresenter extends BasePresenter<SourcesContract.View>
        implements SourcesContract.Presenter {

    @Inject
    public SourcesPresenter() {}

    @Override
    public void load() {

    }
}
