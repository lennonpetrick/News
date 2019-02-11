package com.test.news.data.datasource;

import android.support.annotation.NonNull;

import com.test.news.data.entities.ArticleEntity;
import com.test.news.data.entities.SourceEntity;
import com.test.news.di.qualifiers.ApiKey;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * This data source is used for fetching the data from the api. It always needs to be
 * executed in a worker thread.
 *
 * */
public class RemoteNewsDataSource {

    private NewsService mService;
    private String mApiKey;

    @Inject
    public RemoteNewsDataSource(@NonNull NewsService service,
                                @NonNull @ApiKey String apiKey) {
        this.mService = service;
        this.mApiKey = apiKey;
    }

    /**
     * Returns a list of {@link SourceEntity} from the api.
     *
     * @return A single.
     *  */
    public Single<List<SourceEntity>> getSources() {
        return mService.getSources(mApiKey)
                .map(Wrapper::getSources);
    }

    /**
     * Returns a list of {@link ArticleEntity} from the api for a specific query.
     *
     * @param query A query to search for the articles.
     * @return A single.
     *  */
    public Single<List<ArticleEntity>> getArticles(@NonNull String query) {
        return mService.getArticles(query, mApiKey)
                .map(Wrapper::getArticles);
    }

}
