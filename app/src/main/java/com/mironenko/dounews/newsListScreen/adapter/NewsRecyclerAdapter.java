package com.mironenko.dounews.newsListScreen.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mironenko.dounews.R;
import com.mironenko.dounews.databinding.LayoutRecyclerItemBinding;
import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.mvpBase.IMvpBaseView;
import com.mironenko.dounews.mvpBase.MvcBaseRecyclerAdapter;
import com.mironenko.dounews.mvpBase.MvpViewHolder;
import com.mironenko.dounews.newsListScreen.NewsListPresenter;

import java.util.List;

public class NewsRecyclerAdapter extends MvcBaseRecyclerAdapter<Article, NewsRecyclerAdapter.NewsViewHolder> implements INewsRecyclerAdapter{

    public interface OnItemClickedListener {
        void onArticleSelected(int position);
    }

    private LayoutRecyclerItemBinding binding;
    private OnItemClickedListener listener;

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = LayoutRecyclerItemBinding.inflate(inflater);
        return new NewsViewHolder(binding.getRoot());
    }

    public void setListener(OnItemClickedListener l) {
        this.listener = l;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(getArticlesList().get(position));
    }

    @Override
    public int getItemCount() {
        return getArticlesList().size();
    }

    @Override
    public void setData(List<Article> newsBase) {
        setArticlesList(newsBase);
    }

    class NewsViewHolder extends MvpViewHolder<NewsListPresenter> implements IMvpBaseView, View.OnClickListener {

        private final TextView articleTitle;
        private final TextView articleDatePublished;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            this.articleTitle = (TextView) itemView.findViewById(R.id.tv_Title);
            this.articleDatePublished = (TextView) itemView.findViewById(R.id.tv_Published);

            itemView.setOnClickListener(this);
        }


        public void bind(Article article) {
            articleTitle.setText(article.getTitle());
            articleDatePublished.setText(article.getPublished());
        }

        @Override
        public void showLoading() {
            binding.progressItem.progressLayout.setVisibility(View.VISIBLE);
        }

        @Override
        public void hideLoading() {
            binding.progressItem.progressLayout.setVisibility(View.GONE);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void showError() {
            binding.tvPublished.setText("Something went wrong...");
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onArticleSelected(getAdapterPosition());
            }
        }
    }

}
