package com.test.news.di.modules;

import android.support.annotation.NonNull;

import com.test.news.data.NewsRepositoryImpl;
import com.test.news.domain.NewsRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract NewsRepository providesNewRepository(@NonNull NewsRepositoryImpl repository);

}
