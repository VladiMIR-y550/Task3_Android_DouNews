package com.mironenko.dounews.model;

import com.mironenko.dounews.DouNewsApp;
import com.mironenko.dounews.model.local.Article;
import com.mironenko.dounews.model.local.ILocalDataBase;
import com.mironenko.dounews.model.pojo.ArticlesNewsList;
import com.mironenko.dounews.model.remote.IResponseApi;
import com.mironenko.dounews.model.remote.SavedListener;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class DataStore implements IDataStore {

    @Inject
    ILocalDataBase localBase;
    @Inject
    IResponseApi responseApi;
    private SavedListener savedListener;
    private final int PAGE_SIZE = 20;

    public DataStore() {
        DouNewsApp.get().getDataComponent().injectDataStore(this);
    }

    public void setSavedListener(SavedListener savedListener) {
        this.savedListener = savedListener;
    }

    @Override
    public Observable<List<Article>> getData() {
        return localBase.getAllArticles(Article.class);
    }

    @Override
    public int getSizeDataBase() {
        return localBase.getRealmBaseSize();
    }

    @Override
    public void refreshNews() {
        responseApi.downloadPageArticle(PAGE_SIZE, localBase.getRealmBaseSize())
                .subscribe(new SingleObserver<ArticlesNewsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(ArticlesNewsList articlesNewsList) {
                        localBase.saveInDB(articlesNewsList);
                        savedListener.articlesSaved();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    @Override
    public void refreshNews(int page) {
        responseApi.downloadPageArticle(PAGE_SIZE, page)
                .subscribe(new SingleObserver<ArticlesNewsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(ArticlesNewsList articlesNewsList) {
                        localBase.saveInDB(articlesNewsList);
                        savedListener.articlesSaved();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}
