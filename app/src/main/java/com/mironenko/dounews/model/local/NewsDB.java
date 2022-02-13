package com.mironenko.dounews.model.local;

import com.mironenko.dounews.model.api.Article;
import com.mironenko.dounews.model.api.ArticlesNewsList;

import java.util.ArrayList;
import java.util.List;


public class NewsDB  implements INewsDB {
    private static NewsDB instance;

    private final List<Article> newsBase;

    public NewsDB() {
        newsBase = new ArrayList<>();
    }

    public static synchronized NewsDB getInstance() {
        if (instance == null) {
            instance = new NewsDB();
        }
        return instance;
    }

    @Override
    public List<Article> getAllNews() {
        synchronized (newsBase) {
            return newsBase;
        }
    }

    @Override
    public Article getArticles(int id) {
        synchronized (newsBase) {
            return newsBase.get(id);
        }
    }

    @Override
    public void saveArticlesFromResponseBody(ArticlesNewsList articlesNewsList) {
        synchronized (newsBase) {
                newsBase.addAll(articlesNewsList.getResults());
        }
    }
}
