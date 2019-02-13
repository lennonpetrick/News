package com.test.news.data.datasource;

import android.support.annotation.NonNull;

import com.test.news.data.entities.ArticleEntity;
import com.test.news.data.entities.SourceEntity;
import com.test.news.di.qualifiers.ApiKey;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * This data source is used for fetching the data from the api. It always needs to be
 * executed in a worker thread.
 *
 * */
public class RemoteNewsDataSource {

    private static final int PAGE_SIZE = 10;

    private final NewsService mService;
    private final String mApiKey;
    private int mTotalResults,
                mItemsCount;

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
     * Returns a list of {@link ArticleEntity} from the api for a specific
     * query with pagination.
     *
     * @param query A query to search for the articles.
     * @param sourceId The source id for this query.
     * @param page The current page through the results.
     * @return A single.
     *  */
    public Single<List<ArticleEntity>> getArticles(@NonNull String query,
                                                   @NonNull String sourceId,
                                                   int page) {

        if (page == 1) {
            mItemsCount = 0;
        }

        if (mTotalResults > 0 && mItemsCount >= mTotalResults) {
            return Single.just(new ArrayList<>());
        }

        return mService.getArticles(query, sourceId, mApiKey, PAGE_SIZE, page)
                .doAfterSuccess(wrapper -> mTotalResults = wrapper.getTotalResults())
                .map(Wrapper::getArticles)
                .doAfterSuccess(articleEntities -> mItemsCount += articleEntities.size());
    }

}
