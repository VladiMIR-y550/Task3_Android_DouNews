package com.mironenko.dounews.model.local;

import android.util.Log;

import com.mironenko.dounews.DouNewsApp;
import com.mironenko.dounews.model.pojo.ArticleObj;
import com.mironenko.dounews.model.pojo.ArticlesNewsList;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
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
        Realm realm = Realm.getDefaultInstance();
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
                .map(type -> realm.where(type).findAll());
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
        return article;
    }
}
