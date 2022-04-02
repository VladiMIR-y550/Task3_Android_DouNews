package com.mironenko.dounews.model.remote;

import com.mironenko.dounews.model.pojo.ArticlesNewsList;

import io.reactivex.Single;

public interface IResponseApi {
    Single<ArticlesNewsList> downloadPageArticle(int pageSize, int page);
}
