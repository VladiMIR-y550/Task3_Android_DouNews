package com.mironenko.dounews.mk_version;

import com.mironenko.dounews.model.remote.ArticleResult;
import com.mironenko.dounews.model.remote.ArticlesNewsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("./articles")
    Call<ArticlesNewsList> getArticles();

    @GET("./articles")
    Call<List<ArticleResult>> getArticleResult();
}
