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
import com.mironenko.dounews.model.api.Article;

import java.util.ArrayList;
import java.util.List;

public class PagingAdapter extends RecyclerView.Adapter<PagingAdapter.NewsViewHolder> {

    private final int PRELOAD = 5;
<<<<<<< HEAD
    private List<Article> articleList = new ArrayList<>();
    private PagingUpdateListener listener;

    public interface PagingUpdateListener {
        void onUpdate();
        void onItemClickListener(String urlArticle);
=======
    private PagingUpdateListener listener;
    private List<Article> articleList = new ArrayList<>();

    public interface PagingUpdateListener {
        void onUpdate();

        void onItemClickListener(String urlArticle);
    }

    public PagingAdapter(PagingUpdateListener listener) {
        this.listener = listener;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList.clear();
        this.articleList = articleList;
>>>>>>> developer-rxJava
    }

    public PagingAdapter(PagingUpdateListener listener) {
        this.listener = listener;
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
<<<<<<< HEAD
       holder.bindItem(articleList.get(position));

       Log.d("RxPagingAdapter", "onBindViewHolder = " + position);
=======
        holder.bindItem(articleList.get(position));
        Log.d("PagingAdapter", "onBindViewHolder = " + position);
>>>>>>> developer-rxJava
        if (position == articleList.size() - PRELOAD) {
            listener.onUpdate();
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
<<<<<<< HEAD
    }

    public void setData(List<Article> articles) {
        Log.d("RxPagingAdapter", "setData articles = " + articles.size());
        articleList.addAll(articles);
        Log.d("RxPagingAdapter", "articlesList = " + articleList.size());

=======
>>>>>>> developer-rxJava
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
                Glide.with(context)
                        .load(item.getImgBig2x())
                        .into(itemBinding.ivTitle);
                urlArticle = item.getUrl();
            }
        }

        @Override
        public void onClick(View v) {
<<<<<<< HEAD
            if(listener != null && urlArticle != null) {
=======
            if (listener != null && urlArticle != null) {
>>>>>>> developer-rxJava
                listener.onItemClickListener(urlArticle);
            }
        }
    }
}
