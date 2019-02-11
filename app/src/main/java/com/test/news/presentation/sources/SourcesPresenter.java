package com.test.news.presentation.sources;

import android.support.annotation.NonNull;

import com.test.news.domain.models.Source;
import com.test.news.domain.usecase.GetSources;
import com.test.news.presentation.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class SourcesPresenter extends BasePresenter<SourcesContract.View>
        implements SourcesContract.Presenter {

    private GetSources mGetSources;

    @Inject
    public SourcesPresenter(@NonNull GetSources getSources) {
        mGetSources = getSources;
    }

    @Override
    public void load() {
        mView.showLoading();
        mGetSources.execute(new DisposableSingleObserver<List<Source>>() {
            @Override
            public void onSuccess(List<Source> sources) {
                mView.hideLoading();
                mView.setSources(sources);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e.getMessage());
            }
        }, null);
    }

    @Override
    public void destroy() {
        mGetSources.dispose();
        mGetSources = null;
        super.destroy();
    }
}
