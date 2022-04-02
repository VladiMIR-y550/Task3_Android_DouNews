package com.mironenko.dounews.UI.newsListScreen.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mironenko.dounews.databinding.LayoutArticleCardBinding;
import com.mironenko.dounews.model.local.Article;

import java.util.ArrayList;
import java.util.List;

public class PagingAdapter extends RecyclerView.Adapter<PagingAdapter.NewsViewHolder> {

    public interface PagingUpdateListener {
        void onUpdate();
        void onItemClickListener(String urlArticle);
    }

    private final int PRELOAD = 5;
    private PagingUpdateListener listener;
    private List<Article> articleList = new ArrayList<>();

    public PagingAdapter() {
    }

    public void setListener(PagingUpdateListener listener) {
        this.listener = listener;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList.clear();
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutArticleCardBinding bindingCard = LayoutArticleCardBinding.inflate(inflater, parent, false);
        return new NewsViewHolder(bindingCard, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bindItem(articleList.get(position));
        Log.d("PagingAdapter", "onBindViewHolder = " + position);
        if (position == articleList.size() - PRELOAD) {
            listener.onUpdate();
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final LayoutArticleCardBinding itemBinding;
        private final Context context;
        private String urlArticle;

        public NewsViewHolder(LayoutArticleCardBinding cardBinding, Context context) {
            super(cardBinding.getRoot());
            this.itemBinding = cardBinding;
            this.context = context;

            itemBinding.getRoot().setOnClickListener(this);
        }

        public void bindItem(Article item) {
            if (item != null) {
                itemBinding.tvTitle.setText(item.getTitle().trim());
                itemBinding.tvName.setText(item.getAuthorName());
                itemBinding.tvCount.setText(String.valueOf(item.getPageviews()));
                itemBinding.tvTags.setText(item.getTags());
                itemBinding.tvDate.setText(item.getPublished().toString());
                Glide.with(context)
                        .load(item.getImgBig2x())
                        .into(itemBinding.ivTitle);
                urlArticle = item.getUrl();
            }
        }

        @Override
        public void onClick(View v) {
            if (listener != null && urlArticle != null) {
                listener.onItemClickListener(urlArticle);
            }
        }
    }
}
