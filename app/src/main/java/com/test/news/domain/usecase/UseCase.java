package com.test.news.domain.usecase;

import android.support.annotation.NonNull;

import com.test.news.di.qualifiers.IOScheduler;
import com.test.news.di.qualifiers.UIScheduler;

import dagger.internal.Preconditions;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * This abstract class is an UseCase which represents an execution unit
 * for different cases.
 *
 */
public abstract class UseCase<T, P> {

    private final CompositeDisposable mDisposables;
    private final Scheduler mWorkerThread,
                            mUiThread;

    public UseCase(@NonNull @IOScheduler Scheduler workerThread,
                   @NonNull @UIScheduler Scheduler uiThread,
                   @NonNull CompositeDisposable disposables) {
        this.mWorkerThread = workerThread;
        this.mUiThread = uiThread;
        this.mDisposables = disposables;
    }

    /**
     * Returns an {@link Single} which will be used when executing the current {@link UseCase}.
     *
     * @param params The parameters to execute the {@link UseCase}.
     */
    abstract Single<T> buildUseCase(P params);

    /**
     * Disposes all observers.
     */
    public void dispose() {
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    /**
     * Adds a disposable into {@link CompositeDisposable}.
     *
     * @param disposable A {@link Disposable} from an observer.
     * */
    private void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }

    /**
     * Runs the current {@link UseCase}.
     *
     * @param observer An observer which will be called after it gets executed.
     * @param params The parameters to execute the current {@link UseCase}.
     */
    public void execute(@NonNull DisposableSingleObserver<T> observer, P params) {
        Preconditions.checkNotNull(observer);
        addDisposable(buildUseCase(params)
                .subscribeOn(mWorkerThread)
                .observeOn(mUiThread)
                .subscribeWith(observer));
    }

}
