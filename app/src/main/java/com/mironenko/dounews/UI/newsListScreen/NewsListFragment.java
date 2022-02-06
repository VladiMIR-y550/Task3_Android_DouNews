package com.mironenko.dounews.UI.newsListScreen;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mironenko.dounews.InternetConnection;
import com.mironenko.dounews.R;
import com.mironenko.dounews.UI.newsDetailedScreen.NewsDetailedFragment;
import com.mironenko.dounews.UI.newsListScreen.adapter.PagingAdapter;
import com.mironenko.dounews.databinding.FragmentNewsListBinding;
import com.mironenko.dounews.model.remote.ArticlesNewsList;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;


public class NewsListFragment extends Fragment implements INewsListContract.IView,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String KEY_SCROLL_STATE = "Key scroll view";
    private final String KEY_URL_NEWS = "Url news";
    private FragmentNewsListBinding binding;
    private final INewsListContract.IPresenter listPresenter = new NewsListPresenter();
    private PagingAdapter pagingAdapter;

    public static NewsListFragment newInstance() {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsListBinding.inflate(inflater, container, false);

        listPresenter.attachView(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(layoutManager);

        pagingAdapter = new PagingAdapter();
        pagingAdapter.setPresenter(listPresenter);
        binding.recyclerView.setAdapter(pagingAdapter);
        if (savedInstanceState == null) {
            if (InternetConnection.checkConnection(requireContext())) {
                listPresenter.downloadNewsList(listPresenter.getPageNum());
            }
        } else {
            Parcelable state = savedInstanceState.getParcelable(KEY_SCROLL_STATE);
            layoutManager.onRestoreInstanceState(state);
        }

        binding.swipeRefresh.setOnRefreshListener(this);
        return binding.getRoot();
    }

    @Override
    public void showLoading(boolean showProgress) {
        binding.swipeRefresh.setRefreshing(showProgress);

        if (showProgress) {
            binding.recyclerView.setVisibility(View.GONE);
        } else {
            binding.recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void subscribeNews(Single<ArticlesNewsList> dataSource) {
        dataSource.subscribe(new SingleObserver<ArticlesNewsList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ArticlesNewsList articlesNewsList) {
                pagingAdapter.notifyDataSetChanged();
                showLoading(false);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void onArticleSelected(String urlArticle) {
        NewsDetailedFragment detailedFragment = NewsDetailedFragment.newInstance();
        Bundle args = new Bundle();
        args.putString(KEY_URL_NEWS, urlArticle);
        detailedFragment.setArguments(args);

        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, detailedFragment)
                .commit();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_SCROLL_STATE, binding.recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onRefresh() {
        listPresenter.downloadNewsList(listPresenter.getPageNum());
    }

    @Override
    public void onDestroyView() {
        binding = null;
        listPresenter.detachView();
        pagingAdapter.setPresenter(null);
        pagingAdapter = null;
        super.onDestroyView();
    }
}
