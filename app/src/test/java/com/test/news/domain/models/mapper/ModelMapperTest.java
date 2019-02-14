package com.test.news.domain.models.mapper;

import com.google.gson.Gson;
import com.test.news.MockedObjects;
import com.test.news.data.datasource.Wrapper;
import com.test.news.data.entities.ArticleEntity;
import com.test.news.data.entities.SourceEntity;
import com.test.news.domain.models.Article;
import com.test.news.domain.models.Source;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class ModelMapperTest {

    private Wrapper mWrapper;

    @Before
    public void setUp() {
        mWrapper = new Gson().fromJson(MockedObjects.getJsonResponse(), Wrapper.class);
    }

    @Test
    public void transformSource() {
        SourceEntity entity = mWrapper.getSources().get(0);
        Source model = ModelMapper.transformSource(entity);

        assertThat(model.getId(), equalTo(entity.getId()));
        assertThat(model.getCategory(), equalTo(entity.getCategory()));
        assertThat(model.getDescription(), equalTo(entity.getDescription()));
        assertThat(model.getTitle(), equalTo(entity.getName()));
        assertThat(model.getUrl(), equalTo(entity.getUrl()));
    }

    @Test
    public void transformArticle() throws ParseException {
        ArticleEntity entity = mWrapper.getArticles().get(0);
        Article model = ModelMapper.transformArticle(entity);

        assertThat(model.getTitle(), equalTo(entity.getTitle()));
        assertThat(model.getImageUrl(), equalTo(entity.getUrlToImage()));
        assertThat(model.getAuthor(), equalTo(entity.getAuthor()));
        assertThat(model.getDescription(), equalTo(entity.getDescription()));

        assertThat(model.getPublishedDate(), not(equalTo(entity.getPublishedAt())));
        assertThat(model.getPublishedDate(), is("22/01/2019"));
    }
}