package com.mironenko.dounews.model;

import com.mironenko.dounews.model.local.Article;
import com.mironenko.dounews.model.remote.SavedListener;

import java.util.List;

import io.reactivex.Observable;

public interface IDataStore {
    int getSizeDataBase();
    Observable<List<Article>> getData();
    void refreshNews();
    void refreshNews(int page);

    void setSavedListener(SavedListener savedListener);
}
