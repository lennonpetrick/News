package com.test.news.data.datasource;

import com.google.gson.Gson;
import com.test.news.MockedObjects;
import com.test.news.data.entities.ArticleEntity;
import com.test.news.data.entities.SourceEntity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RemoteNewsDataSourceTest {

    private RemoteNewsDataSource mDataSource;

    @Before
    public void setUp() {
        NewsService service = Mockito.mock(NewsService.class);
        Wrapper wrapper = new Gson()
                .fromJson(MockedObjects.getJsonResponse(), Wrapper.class);

        when(service.getSources(anyString())).thenReturn(Single.just(wrapper));
        when(service.getArticles(anyString(), anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(Single.just(wrapper));

        mDataSource = new RemoteNewsDataSource(service, "api_key");
    }

    @Test
    public void getSources() {
        mDataSource.getSources()
                .test()
                .assertValue(sourceEntities -> {
            SourceEntity entity = sourceEntities.get(0);
            return entity.getId().equals("abc-news")
                    && entity.getName().equals("ABC News")
                    && entity.getUrl().equals("https://abcnews.go.com");
        });
    }

    @Test
    public void getArticles() {
        mDataSource.getArticles("query", "id", 1)
                .test()
                .assertValue(articleEntities -> {
                    ArticleEntity entity = articleEntities.get(0);
                    return entity.getTitle().equals("Digital Garage teams up with " +
                            "Blockstream to develop blockchain financial services in Japan")
                            && entity.getAuthor().equals("Jon Russell")
                            && entity.getSource().getId().equals("techcrunch");
                });

        mDataSource.getArticles("query", "id", 2)
                .test()
                .assertValue(List::isEmpty);
    }
}