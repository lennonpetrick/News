package com.test.news.presentation.articles;

import com.test.news.domain.models.Article;
import com.test.news.domain.models.Source;
import com.test.news.presentation.base.BaseContract;

import java.util.List;

public interface ArticlesContract {

    interface View extends BaseContract.View {
        void addArticles(List<Article> articles);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadMore(int lastVisibleItem, int totalItemCount);
        void setSource(Source source);
    }

}
