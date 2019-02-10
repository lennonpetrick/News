package com.test.news;

import android.app.Activity;
import android.app.Application;

import com.test.news.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MainApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        buildDependencyInjection();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }

    private void buildDependencyInjection() {
        DaggerAppComponent.create().inject(this);
    }
}
