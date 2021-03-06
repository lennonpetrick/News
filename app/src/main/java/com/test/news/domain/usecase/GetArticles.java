package com.test.news.domain.usecase;

import android.support.annotation.NonNull;

import com.test.news.di.qualifiers.IOScheduler;
import com.test.news.di.qualifiers.UIScheduler;
import com.test.news.domain.NewsRepository;
import com.test.news.domain.models.Article;
import com.test.news.domain.models.Source;
import com.test.news.domain.models.mapper.ModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * This class is an implementation of {@link UseCase} which retrieves the articles
 * for a specific query.
 *
 */
public class GetArticles extends UseCase<List<Article>, GetArticles.Params> {

    private NewsRepository mRepository;

    @Inject
    public GetArticles(@NonNull @IOScheduler Scheduler workerThread,
                       @NonNull @UIScheduler Scheduler uiThread,
                       @NonNull CompositeDisposable disposables,
                       @NonNull NewsRepository repository) {
        super(workerThread, uiThread, disposables);
        mRepository = repository;
    }

    @Override
    Single<List<Article>> buildUseCase(Params params) {
        return mRepository.getArticles(params.query, params.sourceId, params.page)
                .toObservable()
                .flatMapIterable(articleEntities -> articleEntities)
                .map(ModelMapper::transformArticle)
                .toList();
    }

    public static final class Params {

        private final String query;
        private final String sourceId;
        private final int page;

        private Params(String query, String sourceId, int page) {
            this.query = query;
            this.sourceId = sourceId;
            this.page = page;
        }

        public static Params forSource(@NonNull Source source,
                                       int page) {
            return new Params(source.getCategory(), source.getId(), page);
        }
    }
}
