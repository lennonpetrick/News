package com.test.news.domain.usecase;

import com.google.gson.Gson;
import com.test.news.MockedObjects;
import com.test.news.data.datasource.Wrapper;
import com.test.news.data.entities.ArticleEntity;
import com.test.news.data.entities.SourceEntity;
import com.test.news.domain.NewsRepository;
import com.test.news.domain.models.Article;
import com.test.news.domain.models.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UseCasesTest {
    
    private Scheduler mScheduler;
    private Wrapper mWrapper;
    
    @Mock
    private NewsRepository mRepository;

    @Before
    public void setUp() {
        mWrapper = new Gson().fromJson(MockedObjects.getJsonResponse(), Wrapper.class);
        
        when(mRepository.getSources()).thenReturn(Single.just(mWrapper.getSources()));
        when(mRepository.getArticles(anyString(), anyString(), anyInt()))
                .thenReturn(Single.just(mWrapper.getArticles()));

        mScheduler = Schedulers.trampoline();
    }
    
    @Test
    public void getSources() {
        GetSources useCase = new GetSources(mScheduler, mScheduler, 
                new CompositeDisposable(), mRepository);
        
        useCase.execute(new DisposableSingleObserver<List<Source>>() {
            @Override
            public void onSuccess(List<Source> sources) {
                SourceEntity entity = mWrapper.getSources().get(0);
                Source model = sources.get(0);
                
                assertThat(model.getId(), equalTo(entity.getId()));
                assertThat(model.getCategory(), equalTo(entity.getCategory()));
                assertThat(model.getDescription(), equalTo(entity.getDescription()));
                assertThat(model.getTitle(), equalTo(entity.getName()));
                assertThat(model.getUrl(), equalTo(entity.getUrl()));
            }

            @Override
            public void onError(Throwable e) { }
        }, null);
    }

    @Test
    public void getArticles() {
        GetArticles useCase = new GetArticles(mScheduler, mScheduler,
                new CompositeDisposable(), mRepository);

        Source source = new Source();
        source.setCategory("query");
        source.setId("id");

        useCase.execute(new DisposableSingleObserver<List<Article>>() {
            @Override
            public void onSuccess(List<Article> articles) {
                ArticleEntity entity = mWrapper.getArticles().get(0);
                Article model = articles.get(0);

                assertThat(model.getTitle(), equalTo(entity.getTitle()));
                assertThat(model.getImageUrl(), equalTo(entity.getUrlToImage()));
                assertThat(model.getAuthor(), equalTo(entity.getAuthor()));
                assertThat(model.getDescription(), equalTo(entity.getDescription()));

                assertThat(model.getPublishedDate(), not(equalTo(entity.getPublishedAt())));
                assertThat(model.getPublishedDate(), is("22/01/2019"));
            }

            @Override
            public void onError(Throwable e) { }
        }, GetArticles.Params.forSource(source, 1));
    }
    
}