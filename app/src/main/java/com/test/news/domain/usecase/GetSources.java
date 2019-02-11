package com.test.news.domain.usecase;

import android.support.annotation.NonNull;

import com.test.news.di.qualifiers.IOScheduler;
import com.test.news.di.qualifiers.UIScheduler;
import com.test.news.domain.NewsRepository;
import com.test.news.domain.models.Source;
import com.test.news.domain.models.mapper.ModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * This class is an implementation of {@link UseCase} which retrieves all
 * the sources.
 *
 */
public class GetSources extends UseCase<List<Source>, Void> {

    private NewsRepository mRepository;

    @Inject
    public GetSources(@NonNull @IOScheduler Scheduler workerThread,
                      @NonNull @UIScheduler Scheduler uiThread,
                      @NonNull CompositeDisposable disposables,
                      @NonNull NewsRepository repository) {
        super(workerThread, uiThread, disposables);
        mRepository = repository;
    }

    @Override
    Single<List<Source>> buildUseCase(Void params) {
        return mRepository.getSources()
                .toObservable()
                .flatMapIterable(sourceEntities -> sourceEntities)
                .map(ModelMapper::transformSource)
                .toList();
    }
}
