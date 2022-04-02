package com.mironenko.dounews.model.local;

import com.mironenko.dounews.model.pojo.ArticlesNewsList;

import java.util.List;

import io.reactivex.Observable;

public interface ILocalDataBase {
    Observable<List<Article>> getAllArticles(Class<Article> clazz);
    void saveInDB(ArticlesNewsList articlesNewsList);
    int getRealmBaseSize();
}
