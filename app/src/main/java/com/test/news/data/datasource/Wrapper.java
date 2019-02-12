package com.test.news.data.datasource;

import com.test.news.data.entities.ArticleEntity;
import com.test.news.data.entities.SourceEntity;

import java.util.List;

/**
 * This is a wrapper that comes from the {@link NewsService}. It's used for parsing the data
 * returned from the service.
 *
 * */
public class Wrapper {

    private String status;
    private int totalResults;
    private List<SourceEntity> sources;
    private List<ArticleEntity> articles;

    public List<SourceEntity> getSources() {
        return sources;
    }

    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
