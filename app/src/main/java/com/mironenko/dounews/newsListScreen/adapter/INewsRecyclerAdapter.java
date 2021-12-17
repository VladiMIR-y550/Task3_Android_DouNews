package com.mironenko.dounews.newsListScreen.adapter;

import com.mironenko.dounews.model.remote.Article;

import java.util.List;

public interface INewsRecyclerAdapter {
    void setData(List<Article> newsBase);
}
