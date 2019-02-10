package com.test.news.presentation.articles.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.news.R;
import com.test.news.models.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private List<Article> mArticles;

    public ArticlesAdapter(List<Article> articles) {
        this.mArticles = articles;
    }

    public void setArticles(List<Article> articles) {
        this.mArticles = articles;
        notifyDataSetChanged();
    }

    private Article getItem(int position) {
        return mArticles.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_articles_adapter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle) TextView mTvTitle;
        @BindView(R.id.ivImage) ImageView mIvImage;
        @BindView(R.id.tvDescription) TextView mTvDescription;
        @BindView(R.id.tvAuthor) TextView mTvAuthor;
        @BindView(R.id.tvDate) TextView mTvDate;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Article article) {
            Picasso.get()
                    .load(article.getImageUrl())
                    .into(mIvImage);

            mTvTitle.setText(article.getTitle());
            mTvDescription.setText(article.getDescription());
            mTvAuthor.setText(article.getAuthor());
            mTvDate.setText(article.getPublishedDate());
        }
    }
}
