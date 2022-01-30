package com.mironenko.dounews.model.local;

import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.model.remote.ArticlesNewsList;

import java.util.ArrayList;
import java.util.List;

public class NewsDB {
    private int pageSize;
    private int countPages;
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

    public List<Article> getAllNews() {
        synchronized (newsBase) {
            return newsBase;
        }
    }

    public List<Article> getAllNewsList() {
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
            newsBase.add(article);
        }
    }

    private void saveArticlesFromList(List<Article> articles) {
        synchronized (newsBase) {
            newsBase.addAll(articles);
        }
    }

    public List<Article> saveArticles(ArticlesNewsList articlesNewsList) {
        List<Article> responseBody = new ArrayList<>(articlesNewsList.getResults());
        savePageSize(responseBody.size());
        saveArticlesFromList(responseBody);

        return getAllNews();
    }

    private void getCountPages(String countFromResponse) {
        countPages = Integer.parseInt(countFromResponse);
    }

    private void savePageSize(int amountArticles) {
        if (pageSize == 0) {
            pageSize = amountArticles;
        }
    }



//    private final Map<Integer, Article> newsBase;
//
//    public NewsDB() {
//        newsBase = new HashMap<>();
//    }
//
//    public static synchronized NewsDB getInstance() {
//        if (instance == null) {
//            instance = new NewsDB();
//        }
//        return instance;
//    }
//
//    public List<Article> getAllNews() {
//        synchronized (newsBase) {
//            return new ArrayList<>(newsBase.values());
//        }
//    }
//
//    public Map<Integer, Article> getAllNewsMap() {
//        synchronized (newsBase) {
//            return newsBase;
//        }
//    }
//
//    public Article getArticles(int id) {
//        synchronized (newsBase) {
//            return newsBase.get(id);
//        }
//    }
//
//    public void saveArticles(Article article) {
//        synchronized (newsBase) {
//            int id = article.getId();
//            newsBase.put(id, article);
//        }
//    }
//
//    public void saveArticlesFromList(List<Article> articles) {
//        synchronized (newsBase) {
//            for (Article article : articles) {
//                newsBase.put(article.getId(), article);
//            }
//        }
//    }
}
