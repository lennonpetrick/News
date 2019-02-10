package com.test.news.presentation.articles;

import com.test.news.models.Source;
import com.test.news.presentation.base.BasePresenter;

import javax.inject.Inject;

public class ArticlesPresenter extends BasePresenter<ArticlesContract.View>
        implements ArticlesContract.Presenter {

    private Source mSource;

    @Inject
    public ArticlesPresenter() {}

    @Override
    public void load() {

    }

    @Override
    public void setSource(Source source) {
        mSource = source;
    }
}
