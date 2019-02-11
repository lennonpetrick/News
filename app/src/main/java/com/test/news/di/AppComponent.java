package com.test.news.di;

import com.test.news.MainApplication;
import com.test.news.di.modules.ActivityModule;
import com.test.news.di.modules.UseCaseModule;
import com.test.news.di.modules.NetworkModule;
import com.test.news.di.modules.PresenterModule;
import com.test.news.di.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        PresenterModule.class,
        UseCaseModule.class,
        RepositoryModule.class,
        NetworkModule.class
})
public interface AppComponent {
    void inject(MainApplication application);
}
