package com.mironenko.dounews.UI.newsListScreen;

import android.annotation.SuppressLint;
import android.util.Log;

import com.mironenko.dounews.DouNewsApp;
import com.mironenko.dounews.UI.mvpBase.BasePresenter;
import com.mironenko.dounews.model.api.Article;
import com.mironenko.dounews.model.api.ArticlesNewsList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class NewsListPresenter extends BasePresenter<Article, INewsListContract.IView> implements INewsListContract.IPresenter {
    private final int PAGE_SIZE = 20;
    private static NewsListPresenter presenter;

    public static synchronized NewsListPresenter getInstance() {
        if (presenter == null) {
            presenter = new NewsListPresenter();
        }
        return presenter;
    }

    @Override
    public int getNews() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Article.class).findAll().size();
    }

    @Override
    public void downloadArticles() {
        Realm realm = Realm.getDefaultInstance();
        Log.d("NewsListPresenter", "realm.size = " + realm.where(Article.class).findAll().size());
        downloadPagesArticle(realm.where(Article.class).findAll().size())
                .subscribe(new SingleObserver<ArticlesNewsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArticlesNewsList articlesNewsList) {
                        Log.d("NewsListPresenter", "Downloaded new articles = " + articlesNewsList.getResults().size());
                        saveInDB(articlesNewsList.getResults());
                        view.updateAdapter();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    @Override
    public Observable<List<Article>> getAllBase(Class<Article> clazz) {
        Realm realm = Realm.getDefaultInstance();
        return Observable.just(clazz)
                .map(type -> realm.where(type).findAll());
    }

    @SuppressLint("CheckResult")
    @Override
    public void refreshNews() {
        downloadPagesArticle(0)
                .subscribe(new Consumer<ArticlesNewsList>() {
                    @Override
                    public void accept(ArticlesNewsList articlesNewsList) throws Exception {
                        saveInDB(articlesNewsList.getResults());
                    }
                });
        view.showLoading(false);

    }

    private Single<ArticlesNewsList> downloadPagesArticle(int page) {
        return DouNewsApp.idouApi.getNextArticlesIntObservable(PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .doOnError(e -> {
                    view.showError();
                    Log.d("NewsListPresenter", "DataSource error " + e);
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void saveInDB(List<Article> articles) {
        Realm realm = Realm.getDefaultInstance();
        for (Article article : articles) {
            try {
                if (!realm.isInTransaction()) {
                    realm.beginTransaction();
                    realm.copyToRealm(article);
                    realm.commitTransaction();
                }
            } catch (RealmPrimaryKeyConstraintException e) {
                realm.cancelTransaction();
            }
        }
    }
}
