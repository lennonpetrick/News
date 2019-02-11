package com.test.news.di.modules;

import android.support.annotation.NonNull;

import com.test.news.presentation.articles.ArticlesContract;
import com.test.news.presentation.articles.ArticlesPresenter;
import com.test.news.presentation.sources.SourcesContract;
import com.test.news.presentation.sources.SourcesPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PresenterModule {

    @Binds
    abstract SourcesContract.Presenter providesSourcePresenter(@NonNull SourcesPresenter presenter);

    @Binds
    abstract ArticlesContract.Presenter providesArticlePresenter(@NonNull ArticlesPresenter presenter);

}
