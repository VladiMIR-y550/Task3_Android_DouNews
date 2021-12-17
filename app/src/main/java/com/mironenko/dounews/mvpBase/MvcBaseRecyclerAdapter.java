package com.mironenko.dounews.mvpBase;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class MvcBaseRecyclerAdapter<M, VH extends MvpViewHolder> extends RecyclerView.Adapter<VH> {
    private List<M> articlesList;

    public MvcBaseRecyclerAdapter() {
        this.articlesList = new ArrayList<>();
    }

    public List<M> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<M> modelList) {
        this.articlesList = modelList;
    }

    @Override
    public void onViewRecycled(@NonNull VH holder) {
        super.onViewRecycled(holder);

        holder.unbindPresenter();
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull VH holder) {
        holder.unbindPresenter();

        return super.onFailedToRecycleView(holder);
    }
}
