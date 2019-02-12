package com.test.news.data;

import android.support.annotation.NonNull;

import com.test.news.data.datasource.RemoteNewsDataSource;
import com.test.news.data.entities.ArticleEntity;
import com.test.news.data.entities.SourceEntity;
import com.test.news.domain.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * This class is a implementation of {@link NewsRepository} and it is responsible for
 * retrieving data from local and cloud. In this case, it is only fetching in the cloud.
 *
 * Needs to be executed in a worker thread.
 *
 * */
public class NewsRepositoryImpl implements NewsRepository {

    private RemoteNewsDataSource mDataSource;

    @Inject
    public NewsRepositoryImpl(@NonNull RemoteNewsDataSource dataSource) {
        this.mDataSource = dataSource;
    }

    /**
     * Returns a list of {@link SourceEntity} from the repository.
     *
     * @return A single.
     *  */
    @Override
    public Single<List<SourceEntity>> getSources() {
        return mDataSource.getSources();
    }

    /**
     * Returns a list of {@link ArticleEntity} from the api for a specific
     * query with pagination.
     *
     * @param query A query to search for the articles.
     * @param page The current page through the results.
     * @return A single.
     *  */
    @Override
    public Single<List<ArticleEntity>> getArticles(@NonNull String query,
                                                   int page) {
        return mDataSource.getArticles(query, page);
    }
}
