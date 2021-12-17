package com.mironenko.dounews.newsListScreen;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mironenko.dounews.DouNewsApp;
import com.mironenko.dounews.model.local.NewsDataBase;
import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.model.remote.ArticlesNewsList;
import com.mironenko.dounews.mvpBase.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListPresenter extends BasePresenter<Article, INewsListContract.IView> implements INewsListContract.IPresenter {
    NewsDataBase newsBase;

    public NewsListPresenter() {
        newsBase = NewsDataBase.getInstance();
    }

    @Override
    public void attachView(INewsListContract.IView view) {
        bindView(view);
    }

    @Override
    public void detachView() {
        unBindView();
    }

    @Override
    public void downloadNewsList() {
        getView().showLoading();

        Call<ArticlesNewsList> call = DouNewsApp.idouApi.getArticles();
        call.enqueue(new Callback<ArticlesNewsList>() {
            @Override
            public void onResponse(@NonNull Call<ArticlesNewsList> call, @NonNull Response<ArticlesNewsList> response) {
                if (response.isSuccessful() && response.body() != null) {

                    newsBase.saveArticlesFromList(response.body().getResults());

                    getView().showNewsList(newsBase.getAllNews());
                    getView().hideLoading();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticlesNewsList> call, @NonNull Throwable t) {
                getView().showError();
            }
        });
    }

    @Override
    public void articleSelected(int position) {
        String urlNews = newsBase.getAllNews().get(position).getUrl();
        getView().showDetailedNews(urlNews);
        Log.d("Mvp NewsListPresenter", " articleSelected() - " + urlNews);
    }
}
