package com.test.news.data.entities;

public class ArticleEntity {

    private SourceEntity source;

    private String author,
                   title,
                   description,
                   urlToImage,
                   publishedAt;

    public SourceEntity getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
