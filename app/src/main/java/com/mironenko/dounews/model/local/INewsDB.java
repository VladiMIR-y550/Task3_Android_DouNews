package com.mironenko.dounews.model.local;

import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.model.remote.ArticlesNewsList;

import java.util.List;

public interface INewsDB {

    void saveArticlesFromResponseBody(ArticlesNewsList articlesNewsList);
    List<Article> getAllNews();
    Article getArticles(int id);
}
