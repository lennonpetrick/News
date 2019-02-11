package com.test.news.di.modules;

import android.support.annotation.NonNull;

import com.test.news.BuildConfig;
import com.test.news.data.datasource.NewsService;
import com.test.news.di.qualifiers.ApiKey;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @ApiKey
    String providesApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Reusable
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(@NonNull HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Reusable
    RxJava2CallAdapterFactory providesRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Reusable
    GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(@NonNull OkHttpClient client,
                              @NonNull RxJava2CallAdapterFactory rxAdapter,
                              @NonNull GsonConverterFactory jsonConverter) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(jsonConverter)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    NewsService providesNewsService(@NonNull Retrofit retrofit) {
        return retrofit.create(NewsService.class);
    }
}
