package com.mironenko.dounews.UI.newsListScreen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mironenko.dounews.InternetConnection;
import com.mironenko.dounews.UI.newsListScreen.adapter.ArticleComparator;
import com.mironenko.dounews.UI.newsListScreen.adapter.NewsLoadStateAdapter;
import com.mironenko.dounews.UI.newsListScreen.adapter.NewsPagingAdapter;
import com.mironenko.dounews.databinding.FragmentNewsListBinding;
import com.mironenko.dounews.model.remote.Article;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;

public class NewsListFragment extends Fragment implements INewsListContract.IView, NewsPagingAdapter.OnItemClickedListener, NewsLoadStateAdapter.OnRetryClickListener {

    public interface OnNewsSelectedListener {
        void newsSelected(String urlNews);
    }

    private OnNewsSelectedListener onNewsSelectedListener;
    private FragmentNewsListBinding binding;
    private final INewsListContract.IPresenter listPresenter = new NewsListPresenter();

    private NewsPagingAdapter pagingAdapter;

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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listPresenter.attachView(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(layoutManager);

        pagingAdapter = new NewsPagingAdapter(new ArticleComparator());
        binding.recyclerView.setAdapter(pagingAdapter.withLoadStateHeaderAndFooter(new NewsLoadStateAdapter(this),
                new NewsLoadStateAdapter(this)));

        if (InternetConnection.checkConnection(requireContext())) {
            listPresenter.downloadNewsList();
        }
        pagingAdapter.setListener(this);

        return binding.getRoot();
    }

    @Override
    public void onArticleSelected(String urlArticle) {
        listPresenter.articleSelected(urlArticle);
    }

    @Override
    public void retryClicked() {
        pagingAdapter.retry();
        Toast.makeText(getContext(), "Retry Button clicked", Toast.LENGTH_SHORT).show();
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

    @Override
    public void showDetailedNews(String urlNewsDetailed) {
        onNewsSelectedListener.newsSelected(urlNewsDetailed);
    }

    @Override
    public void subscribeNews(Flowable<PagingData<Article>> articlePagingDataFlowable) {
        Log.d("Paging", "SubscribeNews() = " + articlePagingDataFlowable);

        articlePagingDataFlowable
                .subscribe(new Consumer<PagingData<Article>>() {
                    @Override
                    public void accept(PagingData<Article> articlePagingData) {
                        pagingAdapter.submitData(NewsListFragment.this.getLifecycle(), articlePagingData);

                        Log.d("Paging", "SubscribeNews() -> accept = " + articlePagingDataFlowable.map(Article -> articlePagingData));
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        binding = null;
        listPresenter.detachView();
        pagingAdapter = null;
        super.onDestroy();
    }
}
