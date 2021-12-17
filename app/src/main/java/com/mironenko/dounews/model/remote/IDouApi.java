package com.mironenko.dounews.model.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IDouApi {

    @GET("./articles")
    Call<ArticlesNewsList> getArticles();
}
