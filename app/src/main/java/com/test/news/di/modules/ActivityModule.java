package com.test.news.di.modules;

import com.test.news.presentation.articles.ArticlesActivity;
import com.test.news.presentation.sources.SourcesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = PresenterModule.class)
    abstract SourcesActivity sourcesActivity();

    @ContributesAndroidInjector(modules = PresenterModule.class)
    abstract ArticlesActivity articlesActivity();

}
