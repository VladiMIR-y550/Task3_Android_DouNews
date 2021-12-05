package com.mironenko.dounews.presenter;

import androidx.annotation.NonNull;

import com.mironenko.dounews.DouNewsApp;
import com.mironenko.dounews.model.remote.ArticleResult;
import com.mironenko.dounews.model.remote.ArticlesNewsList;
import com.mironenko.dounews.view.IListFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListPresenter {

    private final IListFragment iListFragment;
    private List<ArticleResult> articlesList;
    private final boolean internetConnection;

    public NewsListPresenter(IListFragment iListFragment, boolean internetConnection) {
        this.iListFragment = iListFragment;
        this.internetConnection = internetConnection;
        articlesList = new ArrayList<>();

    }

    public List<ArticleResult> getArticlesList() {
        return articlesList;
    }

    public void chooseNews(int position) {
        if (articlesList != null) {
            iListFragment.uploadArticle(articlesList.get(position).getUrl());
        }
    }

    public void fetchNewsList() {

        iListFragment.showLoading();

        if (internetConnection) {

            Call<ArticlesNewsList> call = DouNewsApp.idouApi.getArticles();
            call.enqueue(new Callback<ArticlesNewsList>() {
                @Override
                public void onResponse(@NonNull Call<ArticlesNewsList> call, @NonNull Response<ArticlesNewsList> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        articlesList = response.body().getResults();

                        iListFragment.showNewsList();
                        iListFragment.hideLoading();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ArticlesNewsList> call, @NonNull Throwable t) {

                    iListFragment.showError();
                }
            });
        } else {

            iListFragment.showError();
        }
    }
}
