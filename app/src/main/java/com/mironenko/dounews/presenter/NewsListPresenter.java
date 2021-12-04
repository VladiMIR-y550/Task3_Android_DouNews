package com.mironenko.dounews.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.mironenko.dounews.mk_version.ApiService;
import com.mironenko.dounews.mk_version.RetroClient;
import com.mironenko.dounews.model.remote.ArticleResult;
import com.mironenko.dounews.model.remote.ArticlesNewsList;
import com.mironenko.dounews.model.remote.IdouApi;
import com.mironenko.dounews.view.IListFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListPresenter {

    private final IListFragment iListFragment;
    private IdouApi idouApi;
    private List<ArticleResult> articlesList;
    private Handler handler;
    private Context applicationContext;

    public NewsListPresenter(Handler handler, IListFragment iListFragment, Context applicationContext) {
        this.iListFragment = iListFragment;
        this.handler = handler;
        articlesList = new ArrayList<>();
        this.applicationContext = applicationContext;
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

        if (InternetConnection.checkConnection(applicationContext)) {
            Log.d("Presenter INTERNET", "internet Connection OK");
        } else {
            Log.d("Presenter INTERNET", "internet Connection ERROR");
        }
        ApiService api = RetroClient.getApiService();
        Call<ArticlesNewsList> call = api.getArticles();
        Log.d("Presenter GSON response", "Call<ArticleNewsList> call = " + call);

        call.enqueue(new Callback<ArticlesNewsList>() {
            @Override
            public void onResponse(Call<ArticlesNewsList> call, Response<ArticlesNewsList> response) {
                /**
                 * TODO скрыть прогресбар
                 */
                Log.d("Presenter GSON response", "response Successful - " + response.isSuccessful());
                if(response.isSuccessful()) {
                    articlesList = response.body().getResults();

                    handler.sendEmptyMessage(0);

                }
            }

            @Override
            public void onFailure(Call<ArticlesNewsList> call, Throwable t) {
                Log.d("Presenter GSON response", "response Failure");
            }
        });
        Log.d("Presenter JSON", "result = " + articlesList);

//        Message msg = Message.obtain();
//        msg.obj = articles.get(0);
//        handler.sendMessage(msg);
    }

    public void fetchDetailed(ArticleResult article) {

        if (InternetConnection.checkConnection(applicationContext)) {
            Log.d("Presenter INTERNET", "internet Connection OK");
        } else {
            Log.d("Presenter INTERNET", "internet Connection ERROR");
        }
        ApiService api = RetroClient.getApiService();
        Call<ArticlesNewsList> call = api.getArticles();
        Log.d("Presenter GSON response", "Call<ArticleNewsList> call = " + call);

        call.enqueue(new Callback<ArticlesNewsList>() {
            @Override
            public void onResponse(Call<ArticlesNewsList> call, Response<ArticlesNewsList> response) {
                /**
                 * TODO скрыть прогресбар
                 */
                Log.d("Presenter GSON response", "response Successful - " + response.isSuccessful());
                if(response.isSuccessful()) {
                    articlesList = response.body().getResults();
                    handler.sendEmptyMessage(0);

                }
            }

            @Override
            public void onFailure(Call<ArticlesNewsList> call, Throwable t) {
                Log.d("Presenter GSON response", "response Failure");
            }
        });
        Log.d("Presenter JSON", "result = " + articlesList);

//        Message msg = Message.obtain();
//        msg.obj = articles.get(0);
//        handler.sendMessage(msg);
    }
}
