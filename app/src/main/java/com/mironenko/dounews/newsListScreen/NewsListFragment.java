package com.mironenko.dounews.newsListScreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mironenko.dounews.InternetConnection;
import com.mironenko.dounews.databinding.FragmentNewsListBinding;
import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.newsListScreen.adapter.NewsRecyclerAdapter;

import java.util.List;

public class NewsListFragment extends Fragment implements INewsListContract.IView, NewsRecyclerAdapter.OnItemClickedListener {

    public interface OnNewsSelectedListener {
        void newsSelected(String urlNews);
    }

    private OnNewsSelectedListener onNewsSelectedListener;
    private FragmentNewsListBinding binding;
    private final INewsListContract.IPresenter listPresenter = new NewsListPresenter();
    private NewsRecyclerAdapter adapter;

    public static NewsListFragment newInstance() {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onNewsSelectedListener = (OnNewsSelectedListener) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        binding = FragmentNewsListBinding.inflate(inflater);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listPresenter.attachView(this);

        adapter = new NewsRecyclerAdapter();
        binding.recyclerView.setAdapter(adapter);

        if (InternetConnection.checkConnection(requireContext())){
            listPresenter.downloadNewsList();
        }
        adapter.setListener(this);

        return binding.getRoot();
    }

    @Override
    public void onArticleSelected(int position) {
        listPresenter.articleSelected(position);
    }

    @Override
    public void showLoading() {
        binding.progressNewsList.progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressNewsList.progressLayout.setVisibility(View.GONE);
    }


    @Override
    public void showError() {
        Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showNewsList(List<Article> newsList) {
        adapter.setData(newsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showDetailedNews(String urlNewsDetailed) {
        onNewsSelectedListener.newsSelected(urlNewsDetailed);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        binding = null;
        listPresenter.detachView();
        adapter = null;
        super.onDestroy();
    }
}
