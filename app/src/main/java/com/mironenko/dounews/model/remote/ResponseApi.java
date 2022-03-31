package com.mironenko.dounews.model.remote;

import com.mironenko.dounews.DouNewsApp;
import com.mironenko.dounews.model.pojo.ArticlesNewsList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ResponseApi implements IResponseApi {

    @Inject
    IDouApi iDouApi;

    public ResponseApi() {
        DouNewsApp.get().getAppComponent().injectResponseApi(this);
    }

    @Override
    public Single<ArticlesNewsList> downloadPageArticle(int pageSize, int page) {
        return iDouApi.getNextArticlesIntObservable(pageSize, page)
                .subscribeOn(Schedulers.io())
                .doOnError(e -> {
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
