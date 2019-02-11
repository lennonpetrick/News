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

    private Source mSource;
    private GetArticles mGetArticles;

    @Inject
    public ArticlesPresenter(@NonNull GetArticles getArticles) {
        mGetArticles = getArticles;
    }

    @Override
    public void load() {
        if (mSource == null) {
            return;
        }

        mView.showLoading();
        mGetArticles.execute(new DisposableSingleObserver<List<Article>>() {
            @Override
            public void onSuccess(List<Article> articles) {
                mView.hideLoading();
                mView.setArticles(articles);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e.getMessage());
            }
        }, GetArticles.Params.forSource(mSource));
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
}
