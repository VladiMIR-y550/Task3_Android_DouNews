package com.mironenko.dounews.model.local;

import android.annotation.SuppressLint;

import android.util.Log;

import com.mironenko.dounews.DouNewsApp;
import com.mironenko.dounews.model.pojo.ArticleObj;
import com.mironenko.dounews.model.pojo.ArticlesNewsList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

import io.realm.Sort;

import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class LocalDataBase implements ILocalDataBase {

    @Inject
    Realm realm;

    public LocalDataBase() {
        DouNewsApp.get().getAppComponent().injectLocalDataBase(this);
    }

    @Override
    public int getRealmBaseSize() {
        return realm.where(Article.class).findAll().size();
    }

    @Override
    public void saveInDB(ArticlesNewsList articlesNewsList) {
        for (ArticleObj articleObj : articlesNewsList.getResults()) {
            try {
                if (!realm.isInTransaction()) {
                    realm.beginTransaction();
                    Article article = articleMapping(articleObj);

                    realm.copyToRealm(article);
                    realm.commitTransaction();
                    Log.d("LocalDataBase", "saveInDB = " + article.getId());
                }
            } catch (RealmPrimaryKeyConstraintException e) {
                realm.cancelTransaction();
            }
        }
    }

    @Override
    public Observable<List<Article>> getAllArticles(Class<Article> clazz) {
        return Observable.just(clazz)
                .map(type -> realm.where(type).findAll().sort("date", Sort.DESCENDING));
    }

    private Article articleMapping(ArticleObj articleObj) {
        Article article = realm.createObject(Article.class, articleObj.getId());
        article.setAuthorName(articleObj.getAuthorName());
        article.setTitle(articleObj.getTitle());
        article.setUrl(articleObj.getUrl());
        article.setImgBig2x(articleObj.getImgBig2x());
        article.setTags(articleObj.getTags());
        article.setCategory(articleObj.getCategory());
        article.setPageviews(articleObj.getPageviews());
        article.setDate(stringMappingToDate(articleObj.getPublished()));
        article.setPublished(refactorDatePublished(article.getDate()));
        return article;
    }

    private Date stringMappingToDate(String published) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(published);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private String refactorDatePublished(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat readableFormat = new SimpleDateFormat("d MMMM yyyy H:mm");
        return readableFormat.format(date);
    }
}
