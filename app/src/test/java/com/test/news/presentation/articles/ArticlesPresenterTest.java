package com.test.news.presentation.articles;

import com.google.gson.Gson;
import com.test.news.MockedObjects;
import com.test.news.data.datasource.Wrapper;
import com.test.news.domain.NewsRepository;
import com.test.news.domain.models.Source;
import com.test.news.domain.usecase.GetArticles;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArticlesPresenterTest {

    private ArticlesContract.Presenter mPresenter;

    @Mock
    private ArticlesContract.View mView;

    @Mock
    private NewsRepository mRepository;

    @Before
    public void setUp() {
        Scheduler scheduler = Schedulers.trampoline();
        GetArticles useCase = new GetArticles(scheduler, scheduler,
                new CompositeDisposable(), mRepository);

        mPresenter = new ArticlesPresenter(useCase);
        mPresenter.setView(mView);

        Source source = new Source();
        source.setId("id");
        source.setCategory("query");
        mPresenter.setSource(source);
    }

    @Test
    public void load() {
        Wrapper wrapper = new Gson().fromJson(MockedObjects.getJsonResponse(), Wrapper.class);
        when(mRepository.getArticles(anyString(), anyString(), anyInt()))
                .thenReturn(Single.just(wrapper.getArticles()));

        mPresenter.load();

        verify(mView).showLoading();
        verify(mView).hideLoading();
        verify(mView).addArticles(anyList());
        verify(mView, never()).showError(anyString());
    }

    @Test
    public void load_with_error() {
        when(mRepository.getArticles(anyString(), anyString(), anyInt()))
                .thenReturn(Single.error(new Throwable("ERROR")));

        mPresenter.load();
        verify(mView, never()).addArticles(anyList());
        verify(mView).showError("ERROR");

        mPresenter.loadMore(7, 10);
        verify(mView, never()).addArticles(anyList());
        verify(mView, times(2)).showError("ERROR");
    }

    @Test
    public void loadMore() {
        Wrapper wrapper = new Gson().fromJson(MockedObjects.getJsonResponse(), Wrapper.class);
        when(mRepository.getArticles(anyString(), anyString(), anyInt()))
                .thenReturn(Single.just(wrapper.getArticles()));

        mPresenter.loadMore(7, 10);

        verify(mView, never()).showLoading();
        verify(mView).hideLoading();
        verify(mView).addArticles(anyList());
        verify(mView, never()).showError(anyString());
    }

    @Test
    public void loadMore_notNeeded() {
        mPresenter.loadMore(5, 10);
        verify(mView, never()).addArticles(anyList());
        verify(mView, never()).showError(anyString());
    }
}