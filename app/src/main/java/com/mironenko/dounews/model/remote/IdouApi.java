package com.mironenko.dounews.model.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IdouApi {

//    @GET("./articles")
//    ArticlesListResponse getArticles();

    @GET("./articles")
    Call<ArticlesListResponse> getArticles();
}
