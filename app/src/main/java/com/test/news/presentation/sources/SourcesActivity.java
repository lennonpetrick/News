package com.test.news.presentation.sources;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.news.R;
import com.test.news.domain.models.Source;
import com.test.news.presentation.articles.ArticlesActivity;
import com.test.news.presentation.base.BaseActivity;
import com.test.news.presentation.sources.adapter.SourcesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourcesActivity extends BaseActivity<SourcesContract.Presenter>
        implements SourcesContract.View, SourcesAdapter.OnItemClickListener {

    @BindView(R.id.rvSources) RecyclerView mRvSources;

    private SourcesAdapter mSourceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        ButterKnife.bind(this);
        setUpRecyclerView();
        mPresenter.setView(this);
    }

    private void setUpRecyclerView() {
        final DividerItemDecoration divider = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);

        final Drawable drawable = getDrawable(R.drawable.layout_recycler_divider_transparent);
        if (drawable != null) {
            divider.setDrawable(drawable);
        }

        mRvSources.addItemDecoration(divider);
        mRvSources.setLayoutManager(new LinearLayoutManager(this));
        mRvSources.setHasFixedSize(false);
    }

    @Override
    public void setSources(List<Source> sources) {
        if (mSourceAdapter == null) {
            mSourceAdapter = new SourcesAdapter(sources);
            mSourceAdapter.setOnItemClickListener(this);
            mRvSources.setAdapter(mSourceAdapter);
        } else {
            mSourceAdapter.setSources(sources);
        }
    }

    @Override
    public void onItemClick(Source source) {
        startArticlesActivity(source);
    }

    private void startArticlesActivity(Source source) {
        final Intent intent = new Intent(this, ArticlesActivity.class);
        intent.putExtra(ArticlesActivity.SOURCE_EXTRA, source);
        startActivity(intent);
    }
}
