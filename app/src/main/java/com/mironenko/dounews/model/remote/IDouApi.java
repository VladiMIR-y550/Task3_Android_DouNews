package com.mironenko.dounews.model.remote;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDouApi {

    @GET("articles")
    Single<ArticlesNewsList> getNextArticlesStringObservable(@Query("limit") String loadSize,
                                                            @Query("offset") String page);

}
