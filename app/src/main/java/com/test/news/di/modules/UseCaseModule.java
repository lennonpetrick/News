package com.test.news.di.modules;

import com.test.news.di.qualifiers.IOScheduler;
import com.test.news.di.qualifiers.UIScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@Module
public class UseCaseModule {

    @Provides
    @Singleton
    @IOScheduler
    Scheduler ioScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @UIScheduler
    Scheduler uiScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

}
