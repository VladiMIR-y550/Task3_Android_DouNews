package com.mironenko.dounews.model.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IdouApi {

    @GET("./articles")
    Call<ArticlesNewsList> getArticles();
}
