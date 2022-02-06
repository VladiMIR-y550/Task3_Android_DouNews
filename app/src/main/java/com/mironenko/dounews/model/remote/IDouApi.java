package com.mironenko.dounews.model.remote;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IDouApi {

    @GET("articles")
    Single<ArticlesNewsList> getNextArticlesIntObservable(@Query("limit") Integer loadSize,
                                                          @Query("offset") Integer page);
}
