package com.mironenko.dounews.UI.newsListScreen.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mironenko.dounews.UI.newsListScreen.INewsListContract;
import com.mironenko.dounews.databinding.LayoutArticleCardBinding;
import com.mironenko.dounews.model.remote.Article;

public class NewsViewHolder extends RecyclerView.ViewHolder implements INewsListContract.ItemView {

    private final LayoutArticleCardBinding itemBinding;
    private final Context context;
    private String urlArticle;

    public NewsViewHolder(LayoutArticleCardBinding cardBinding, Context context, INewsListContract.IPresenter presenter) {
        super(cardBinding.getRoot());
        this.itemBinding = cardBinding;
        this.context = context;

        itemView.setOnClickListener(v -> presenter.onItemClicked(urlArticle));
    }

    @Override
    public void bindItem(Article item) {
        if (item != null) {
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
    }
}
