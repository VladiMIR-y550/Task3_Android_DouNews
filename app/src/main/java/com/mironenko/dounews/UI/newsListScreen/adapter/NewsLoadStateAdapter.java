package com.mironenko.dounews.UI.newsListScreen.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mironenko.dounews.databinding.LayoutLoadStateItemBinding;

public class NewsLoadStateAdapter extends LoadStateAdapter<NewsLoadStateAdapter.ItemViewHolder> implements View.OnClickListener{

    public interface OnRetryClickListener {
        void retryClicked();
    }

    private final OnRetryClickListener onRetryClickListener;
    private LayoutLoadStateItemBinding layoutLoadStateBinding;

    public NewsLoadStateAdapter(OnRetryClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, @NonNull LoadState loadState) {
        layoutLoadStateBinding.loadStateRetry.setOnClickListener(this);

        if (loadState instanceof LoadState.Error) {
            LoadState.Error loadStateError = (LoadState.Error) loadState;
            layoutLoadStateBinding.loadStateErrorMessage.setText(loadStateError.getError().getLocalizedMessage());
        }
        Log.d("Paging", "LoadState - " + loadState);

        layoutLoadStateBinding.loadStateProgress.setVisibility(loadState instanceof LoadState.Loading ? View.VISIBLE : View.GONE);
        layoutLoadStateBinding.loadStateRetry.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
        layoutLoadStateBinding.loadStateErrorMessage.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull LoadState loadState) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        layoutLoadStateBinding = LayoutLoadStateItemBinding.inflate(inflater);

        return new ItemViewHolder(layoutLoadStateBinding.getRoot());
    }

    @Override
    public void onClick(View v) {
        onRetryClickListener.retryClicked();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
