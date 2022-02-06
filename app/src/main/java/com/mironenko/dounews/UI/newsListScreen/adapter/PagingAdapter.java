package com.mironenko.dounews.UI.newsListScreen.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mironenko.dounews.UI.newsListScreen.INewsListContract;
import com.mironenko.dounews.databinding.LayoutArticleCardBinding;

public class PagingAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private final int PREFETCH_DISTANCE = 5;
    private INewsListContract.IPresenter presenter;

    public void setPresenter(INewsListContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutArticleCardBinding bindingCard = LayoutArticleCardBinding.inflate(inflater, parent, false);
        return new NewsViewHolder(bindingCard, parent.getContext(), presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        presenter.onBindViewHolder(holder, position);
        pagingUpdate(position);

    }

    @Override
    public int getItemCount() {
        if (presenter.getPageNum() == 0) {
            return presenter.getPAGE_SIZE();
        } else {
            return presenter.getPageNum();
        }
    }

    private void pagingUpdate(int position) {
        if (presenter.getPageNum() != PREFETCH_DISTANCE && position == (presenter.getPageNum() - PREFETCH_DISTANCE)) {
            presenter.downloadNewsList(presenter.getPageNum());
        }
    }
}
