package com.test.news.domain;

import android.support.annotation.NonNull;

import com.test.news.data.entities.ArticleEntity;
import com.test.news.data.entities.SourceEntity;

import java.util.List;

import io.reactivex.Single;

public interface NewsRepository {

    Single<List<SourceEntity>> getSources();

    Single<List<ArticleEntity>> getArticles(@NonNull String query);

}
