package com.test.news.di;

import com.test.news.MainApplication;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        PresenterModule.class
})
public interface AppComponent {
    void inject(MainApplication application);
}
