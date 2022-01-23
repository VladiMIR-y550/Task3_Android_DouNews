package com.mironenko.dounews.model;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IDouApi {

    @GET("articles")
    Single<ArticlesNewsList> getNextArticlesIntObservable(@Query("limit") Integer loadSize,
                                                             @Query("offset") Integer page);
}
