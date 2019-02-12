package com.test.news.presentation.articles;

import android.support.annotation.NonNull;

import com.test.news.domain.models.Article;
import com.test.news.domain.models.Source;
import com.test.news.domain.usecase.GetArticles;
import com.test.news.presentation.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class ArticlesPresenter extends BasePresenter<ArticlesContract.View>
        implements ArticlesContract.Presenter {

    private static final int THRESHOLD = 3;

    private Source mSource;
    private GetArticles mGetArticles;
    private int mPage;
    private boolean mLoading;

    @Inject
    public ArticlesPresenter(@NonNull GetArticles getArticles) {
        mGetArticles = getArticles;
        mPage = 1;
    }

    @Override
    public void load() {
        if (mSource == null) {
            return;
        }

        mView.showLoading();
        executeGetArticles();
    }

    @Override
    public void loadMore(int lastVisibleItem, int totalItemCount) {
        if (!mLoading && (lastVisibleItem + THRESHOLD) >= totalItemCount) {
            mPage++;
            executeGetArticles();
        }
    }

    @Override
    public void setSource(@NonNull Source source) {
        mSource = source;
    }

    @Override
    public void destroy() {
        mGetArticles.dispose();
        mGetArticles = null;
        mSource = null;
        super.destroy();
    }

    private void executeGetArticles() {
        mLoading = true;
        mGetArticles.execute(new DisposableSingleObserver<List<Article>>() {
            @Override
            public void onSuccess(List<Article> articles) {
                mView.hideLoading();
                mView.addArticles(articles);
                mLoading = false;
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e.getMessage());
                mLoading = false;
            }
        }, GetArticles.Params.forSource(mSource, mPage));
    }
}
