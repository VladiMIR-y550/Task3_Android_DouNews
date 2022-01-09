package com.mironenko.dounews.UI.newsListScreen.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mironenko.dounews.databinding.LayoutArticleCardBinding;
import com.mironenko.dounews.model.remote.Article;
import com.squareup.picasso.Picasso;

public class NewsPagingAdapter extends PagingDataAdapter<Article, NewsPagingAdapter.NewsViewHolder> {

    public interface OnItemClickedListener {
        void onArticleSelected(String urlArticle);
    }

    private LayoutArticleCardBinding bindingCard;
    private OnItemClickedListener listener;

    public NewsPagingAdapter(@NonNull DiffUtil.ItemCallback<Article> diffCallback) {
        super(diffCallback);
        Log.d("Paging", "NextPagingAdapter constructor args" + diffCallback);
    }

    public void setListener(OnItemClickedListener l) {
        this.listener = l;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        bindingCard = LayoutArticleCardBinding.inflate(inflater);
        return new NewsViewHolder(bindingCard.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article item = getItem(position);
        Log.d("Paging", "PagingAdapter position = " + position);
        holder.bind(item);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView articleTitle;
        private final TextView articleDatePublished;
        private final TextView articleAuthorName;
        private final TextView articlePostViews;
        private final TextView articleTag;
        private final ImageView articleImageTitle;
        private String urlArticle;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            articleTitle = bindingCard.tvTitle;
            articleDatePublished = bindingCard.tvDatePablisher;
            articleAuthorName = bindingCard.tvAuthorName;
            articlePostViews = bindingCard.tvPageViews;
            articleTag = bindingCard.tvTags;
            articleImageTitle = bindingCard.ivTitle;

            itemView.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Article item) {
            articleTitle.setText(item.getTitle().trim());
            articleDatePublished.setText(item.getPublished());
            articleAuthorName.setText(item.getAuthorName());
            articlePostViews.setText("Просмотров: " + item.getPageviews());
            articleTag.setText("Теги: " + item.getTags());
            Picasso.get().load(item.getImgBig2x()).into(articleImageTitle);
            urlArticle = item.getUrl();
        }

        @Override
        public void onClick(View v) {
            if(listener != null && urlArticle != null) {
                listener.onArticleSelected(urlArticle);
            }
        }
    }
}
