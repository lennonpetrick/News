package com.test.news.presentation.sources;

import com.google.gson.Gson;
import com.test.news.MockedObjects;
import com.test.news.data.datasource.Wrapper;
import com.test.news.domain.NewsRepository;
import com.test.news.domain.usecase.GetSources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SourcesPresenterTest {

    private SourcesContract.Presenter mPresenter;

    @Mock
    private SourcesContract.View mView;

    @Mock
    private NewsRepository mRepository;

    @Before
    public void setUp() {
        Scheduler scheduler = Schedulers.trampoline();
        GetSources useCase = new GetSources(scheduler, scheduler,
                new CompositeDisposable(), mRepository);

        mPresenter = new SourcesPresenter(useCase);
        mPresenter.setView(mView);
    }

    @Test
    public void load() {
        Wrapper wrapper = new Gson().fromJson(MockedObjects.getJsonResponse(), Wrapper.class);
        when(mRepository.getSources()).thenReturn(Single.just(wrapper.getSources()));

        mPresenter.load();

        verify(mView).showLoading();
        verify(mView).hideLoading();
        verify(mView).setSources(anyList());
        verify(mView, never()).showError(anyString());
    }

    @Test
    public void load_with_error() {
        when(mRepository.getSources()).thenReturn(Single.error(new Throwable("ERROR")));

        mPresenter.load();

        verify(mView).showLoading();
        verify(mView, never()).setSources(anyList());
        verify(mView).showError("ERROR");
    }
}