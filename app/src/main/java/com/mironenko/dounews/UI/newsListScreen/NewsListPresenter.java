package com.mironenko.dounews.UI.newsListScreen;

import com.mironenko.dounews.DouNewsApp;
import com.mironenko.dounews.UI.mvpBase.BasePresenter;
import com.mironenko.dounews.model.IDataStore;
import com.mironenko.dounews.model.local.Article;
import com.mironenko.dounews.model.remote.SavedListener;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NewsListPresenter extends BasePresenter<Article, INewsListContract.IView> implements INewsListContract.IPresenter,
        SavedListener {

    @Inject
    IDataStore dataStore;
    private final int START_PAGE = 0;
    private static NewsListPresenter presenter;

    public static synchronized NewsListPresenter getInstance() {
        if (presenter == null) {
            presenter = new NewsListPresenter();
        }
        return presenter;
    }

    public NewsListPresenter() {
        DouNewsApp.get().getDataComponent().injectListPresenter(this);
        dataStore.setSavedListener(this);
    }

    @Override
    public int getBaseSize() {
        return dataStore.getSizeDataBase();
    }

    @Override
    public Observable<List<Article>> getNews() {
        return dataStore.getData();
    }

    @Override
    public void refreshArticles() {
        dataStore.refreshNews(START_PAGE);
        view.updateAdapter();
        view.showLoading(false);
    }

    @Override
    public void downloadArticles() {
        dataStore.refreshNews();
    }

    @Override
    public void articlesSaved() {
        view.updateAdapter();
    }
}
