package com.test.news.presentation.sources.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.news.R;
import com.test.news.models.Source;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {

    private List<Source> mSources;
    private OnItemClickListener mListener;

    public SourcesAdapter(@NonNull List<Source> sources) {
        this.mSources = sources;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void setSources(List<Source> sources) {
        this.mSources = sources;
        notifyDataSetChanged();
    }

    private Source getItem(int position) {
        return mSources.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_sources_adapter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mSources.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle) TextView mTvTitle;
        @BindView(R.id.tvDescription) TextView mTvDescription;
        @BindView(R.id.tvUrl) TextView mTvUrl;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onItemClick(getItem(getAdapterPosition()));
                }
            });
        }

        private void bind(Source source) {
            mTvTitle.setText(source.getTitle());
            mTvDescription.setText(source.getDescription());
            mTvUrl.setText(source.getUrl());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Source source);
    }

}
