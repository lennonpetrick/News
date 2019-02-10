package com.test.news.presentation.articles;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.news.R;
import com.test.news.models.Article;
import com.test.news.presentation.articles.adapter.ArticlesAdapter;
import com.test.news.presentation.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesActivity extends BaseActivity<ArticlesContract.Presenter>
        implements ArticlesContract.View {

    public static final String SOURCE_EXTRA = "source";

    @BindView(R.id.rvArticles) RecyclerView mRvArticles;

    private ArticlesAdapter mArticlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ButterKnife.bind(this);
        setUpRecyclerView();
        mPresenter.setView(this);
        checkExtras(getIntent().getExtras());
    }

    private void setUpRecyclerView() {
        final DividerItemDecoration divider = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);

        final Drawable drawable = getDrawable(R.drawable.layout_recycler_divider_transparent);
        if (drawable != null) {
            divider.setDrawable(drawable);
        }

        mRvArticles.addItemDecoration(divider);
        mRvArticles.setLayoutManager(new LinearLayoutManager(this));
        mRvArticles.setHasFixedSize(false);
    }

    private void checkExtras(Bundle bundle) {
        if (bundle == null) {
            return;
        }

        if (bundle.containsKey(SOURCE_EXTRA)) {
            mPresenter.setSource(bundle.getParcelable(SOURCE_EXTRA));
        }
    }

    @Override
    public void setArticles(List<Article> articles) {
        if (mArticlesAdapter == null) {
            mArticlesAdapter = new ArticlesAdapter(articles);
            mRvArticles.setAdapter(mArticlesAdapter);
        } else {
            mArticlesAdapter.setArticles(articles);
        }
    }
}
