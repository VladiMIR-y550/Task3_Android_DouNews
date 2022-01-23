package com.mironenko.dounews.repositories;

import static com.mironenko.dounews.DouNewsApp.idouApi;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.mironenko.dounews.model.Article;
import com.mironenko.dounews.model.ArticlesNewsList;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsPageSource extends RxPagingSource<Integer, Article> {

    private int pageSize;
    private int page;

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Article> pagingState) {

        Log.d("Paging", "get Refresh Key Start = " + pagingState.getAnchorPosition());

        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, Article> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            Log.d("Paging", "get Refresh Key prevKey + pageSize = " + (prevKey + pageSize));
            return prevKey + pageSize;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            Log.d("Paging", "get Refresh Key prevKey - pageSize = " + (nextKey - pageSize));
            return nextKey - pageSize;
        }
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Article>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            pageSize = loadParams.getLoadSize();
            page = loadParams.getKey() != null ? loadParams.getKey() : pageSize;

            return idouApi.getNextArticlesIntObservable(pageSize, page)
                    .subscribeOn(Schedulers.io())
                    .map(ArticlesNewsList::getResults)
                    .map(articleList -> toLoadResult(articleList, page))
                    .onErrorReturn(LoadResult.Error::new);
        } catch (Exception e) {
            return Single.just(new LoadResult.Error(e));
        }
    }

    private LoadResult<Integer, Article> toLoadResult(List<Article> articles, int page) {

        Integer nextKey;
        if (articles.size() < pageSize) {
            nextKey = null;
        } else {
            nextKey = page + pageSize;
        }

        Integer prevKey;
        if (page == pageSize) {
            prevKey = null;
        } else {
            prevKey = page - pageSize;
        }
        Log.d("Paging", "toLoadResult nextKey = " + nextKey + ", prevKey = " + prevKey);
        return new LoadResult.Page(articles, prevKey, nextKey);
    }
}
