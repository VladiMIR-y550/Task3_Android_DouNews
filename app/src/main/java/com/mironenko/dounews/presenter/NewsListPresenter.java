package com.mironenko.dounews.presenter;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

import com.mironenko.dounews.model.remote.ArticlesListItems;
import com.mironenko.dounews.model.remote.ArticlesListResponse;
import com.mironenko.dounews.model.remote.IdouApi;
import com.mironenko.dounews.view.IListFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class NewsListPresenter {

    private final IListFragment iListFragment;
    private List<ArticlesListItems> articles;
    private Handler handler;

    public NewsListPresenter(Handler handler, IListFragment iListFragment) {
        this.iListFragment = iListFragment;
        articles = new ArrayList<>();
        this.handler = handler;
    }

    public void chooseNews() {
        iListFragment.showDetail();
    }

    public void fetchNewsList() {
        Message msg = Message.obtain();
        msg.obj = articles.get(0);
        handler.sendMessage(msg);
    }
}
