package com.mironenko.dounews.UI.newsListScreen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.mironenko.dounews.R;
import com.mironenko.dounews.databinding.LayoutArticleCardBinding;
import com.mironenko.dounews.model.remote.Article;

public class NewsPagingAdapter extends PagingDataAdapter<Article, NewsPagingAdapter.NewsViewHolder> {

    public interface OnItemClickedListener {
        void onArticleSelected(String urlArticle);
    }

    private OnItemClickedListener listener;
    private Context context;

    public NewsPagingAdapter(@NonNull DiffUtil.ItemCallback<Article> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }

    public void setListener(OnItemClickedListener l) {
        this.listener = l;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutArticleCardBinding bindingCard = LayoutArticleCardBinding.inflate(inflater, parent, false);
        return new NewsViewHolder(bindingCard);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article item = getItem(position);
        Log.d("Paging", "PagingAdapter position = " + position);
        holder.bind(item);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final LayoutArticleCardBinding itemBinding;
        private String urlArticle;

        public NewsViewHolder(@NonNull LayoutArticleCardBinding cardBinding) {
            super(cardBinding.getRoot());
            this.itemBinding = cardBinding;

            itemView.setOnClickListener(this);
        }

        @SuppressLint({"SetTextI18n", "CheckResult"})
        public void bind(Article item) {
            itemBinding.tvTitle.setText(item.getTitle().trim());
            itemBinding.tvName.setText(item.getAuthorName());
            itemBinding.tvCount.setText(String.valueOf(item.getPageviews()));
            itemBinding.tvTags.setText(item.getTags());
            Glide.with(context)
                    .load(item.getImgBig2x())
                    .optionalFitCenter()
                    .into(itemBinding.ivTitle);
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
