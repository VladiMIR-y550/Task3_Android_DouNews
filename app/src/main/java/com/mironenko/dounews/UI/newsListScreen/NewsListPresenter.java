package com.mironenko.dounews.UI.newsListScreen;

import android.util.Log;

import com.mironenko.dounews.DouNewsApp;
import com.mironenko.dounews.UI.mvpBase.BasePresenter;
import com.mironenko.dounews.UI.newsListScreen.adapter.NewsViewHolder;
import com.mironenko.dounews.model.local.INewsDB;
import com.mironenko.dounews.model.local.NewsDB;
import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.model.remote.ArticlesNewsList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsListPresenter extends BasePresenter<Article, INewsListContract.IView> implements INewsListContract.IPresenter {
    private final int PAGE_SIZE = 20;
    private int countPages = 0;
    private int pageNum = 0;
    private INewsDB baseNews = NewsDB.getInstance();

    public int getPageNum() {
        return pageNum;
    }

    public int getPAGE_SIZE() {
        return PAGE_SIZE;
    }

    @Override
    public void downloadNewsList(int page) {
        view.subscribeNews(dataSource(page));
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        if(!baseNews.getAllNews().isEmpty()) {
            Log.d("RxPresenter", "OnBindViewHolder position = " + position);
            holder.bindItem(baseNews.getArticles(position));
        }
    }

    @Override
    public void onItemClicked(String urlArticle) {
        view.onArticleSelected(urlArticle);
    }

    private Single<ArticlesNewsList> dataSource(int page) {
        return DouNewsApp.idouApi.getNextArticlesIntObservable(PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .map(this::getSaveListIntoDatabase)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private ArticlesNewsList getSaveListIntoDatabase(ArticlesNewsList articlesNewsList) {
        pageNum += PAGE_SIZE;

        baseNews.saveArticlesFromResponseBody(articlesNewsList);
        return articlesNewsList;
    }
}
