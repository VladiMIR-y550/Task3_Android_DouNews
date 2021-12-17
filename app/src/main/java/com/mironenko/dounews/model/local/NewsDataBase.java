package com.mironenko.dounews.model.local;

import com.mironenko.dounews.model.remote.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsDataBase {

    private static NewsDataBase instance;

    private final Map<Integer, Article> newsBase;

    public NewsDataBase() {
        newsBase = new HashMap<>();
    }

    public static synchronized NewsDataBase getInstance() {
        if (instance == null) {
            instance = new NewsDataBase();
        }
        return instance;
    }

    public List<Article> getAllNews() {
        synchronized (newsBase) {
            return new ArrayList<>(newsBase.values());
        }
    }

    public Map<Integer, Article> getAllNewsMap() {
        synchronized (newsBase) {
            return newsBase;
        }
    }

    public Article getArticles(int id) {
        synchronized (newsBase) {
            return newsBase.get(id);
        }
    }

    public void saveArticles(Article article) {
        synchronized (newsBase) {
            int id = article.getId();
            newsBase.put(id, article);
        }
    }

    public void saveArticlesFromList(List<Article> articles) {
        synchronized (newsBase) {
            for (Article article : articles) {
                newsBase.put(article.getId(), article);
            }
        }
    }
}
