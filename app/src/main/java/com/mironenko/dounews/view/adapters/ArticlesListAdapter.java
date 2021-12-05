package com.mironenko.dounews.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mironenko.dounews.databinding.LayoutRowViewBinding;
import com.mironenko.dounews.model.remote.ArticleResult;
import com.mironenko.dounews.presenter.NewsListPresenter;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ArticlesViewHolder> {

    private final NewsListPresenter listPresenter;
    private LayoutRowViewBinding binding;

    public ArticlesListAdapter(NewsListPresenter listPresenter) {
        this.listPresenter = listPresenter;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = LayoutRowViewBinding.inflate(inflater);
        View view = binding.getRoot();

        return new ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {

        ArticleResult article = listPresenter.getArticlesList().get(position);
        holder.bind(article);

    }

    @Override
    public int getItemCount() {
        return listPresenter.getArticlesList().size();
    }

    class ArticlesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView1;
        TextView textView2;

        public ArticlesViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = binding.textViewTitle;
            textView2 = binding.textViewAnnouncement;

            itemView.setOnClickListener(this);
        }

        void bind(ArticleResult articleResult) {

            textView1.setText(articleResult.getTitle());
            textView2.setText(articleResult.getPublished());

        }

        @Override
        public void onClick(View v) {
            listPresenter.chooseNews(getAdapterPosition());
        }
    }
}
