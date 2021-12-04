package com.mironenko.dounews.mk_version;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mironenko.dounews.model.remote.ArticleResult;

import java.util.List;

public class ArticlesAdapter extends ArrayAdapter<ArticleResult> {

    private List<ArticleResult> newsList;
    private Context context;
    private LayoutInflater inflater;

    public ArticlesAdapter(@NonNull Context context, @NonNull List<ArticleResult> objects) {
        super(context, 0, objects);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        newsList = objects;
    }

    @Nullable
    @Override
    public ArticleResult getItem(int position) {
        return super.getItem(position);
    }

    private static class ViewHolder {

    }
}
