package com.test.news.presentation.articles;

import com.test.news.domain.models.Article;
import com.test.news.domain.models.Source;
import com.test.news.presentation.base.BaseContract;

import java.util.List;

public interface ArticlesContract {

    interface View extends BaseContract.View {
        void setArticles(List<Article> articles);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void setSource(Source source);
    }

}
