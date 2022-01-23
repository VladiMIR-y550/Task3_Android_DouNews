package com.mironenko.dounews.UI.newsListScreen.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.mironenko.dounews.model.Article;

public class ArticleComparator extends DiffUtil.ItemCallback<Article> {
    @Override
    public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.equals(newItem);
    }
}
