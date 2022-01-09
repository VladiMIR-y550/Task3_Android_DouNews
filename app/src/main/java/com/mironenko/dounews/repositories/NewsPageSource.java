package com.mironenko.dounews.repositories;

import static com.mironenko.dounews.DouNewsApp.idouApi;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.model.remote.ArticlesNewsList;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsPageSource extends RxPagingSource<String, Article> {

    @Nullable
    @Override
    public String getRefreshKey(@NonNull PagingState<String, Article> pagingState) {
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<String, Article> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        String prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey;
        }

        String nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey;
        }

        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<String, Article>> loadSingle(@NonNull LoadParams<String> loadParams) {
        try {
            Log.d("Paging", "Ключь получаемый от loadParams.getKey = " + getQueryPage(loadParams.getKey()));

            String page = loadParams.getKey() != null ? getQueryPage(loadParams.getKey()) : null;
            String loadSize = String.valueOf(loadParams.getLoadSize());

            Single<LoadResult<String, Article>> loadResultSingle = null;
                loadResultSingle = idouApi.getNextArticlesStringObservable(loadSize, page)
                        .subscribeOn(Schedulers.io())
                        .map(this::toLoadResult)
//                        .cache()
                        .onErrorReturn(LoadResult.Error::new);
                Log.d("Paging", "Второй запрос page != null = " + page);

            return loadResultSingle;

        } catch (Exception e) {
            return Single.just(new LoadResult.Error(e));
        }
    }

    private LoadResult<String, Article> toLoadResult(ArticlesNewsList articlesNewsList) {
        String prevKey = articlesNewsList.getPrevious();
        String nextKey = articlesNewsList.getNext();

        Log.d("Paging", "toLoadResult nextKey = " + nextKey + ", prevKey = " + prevKey);
        return new LoadResult.Page<>(articlesNewsList.getResults(), prevKey, nextKey, LoadResult.Page.COUNT_UNDEFINED, LoadResult.Page.COUNT_UNDEFINED);
    }

    private String getQueryPage(String page) {
        if (page != null) {
            Uri uri = Uri.parse(page);
            return uri.getQueryParameter("offset");
        }
        return null;
    }
}
