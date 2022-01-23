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
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mironenko.dounews.InternetConnection;
import com.mironenko.dounews.R;
import com.mironenko.dounews.UI.newsDetailedScreen.NewsDetailedFragment;
import com.mironenko.dounews.UI.newsListScreen.adapter.ArticleComparator;
import com.mironenko.dounews.UI.newsListScreen.adapter.NewsLoadStateAdapter;
import com.mironenko.dounews.UI.newsListScreen.adapter.NewsPagingAdapter;
import com.mironenko.dounews.databinding.FragmentNewsListBinding;
import com.mironenko.dounews.model.Article;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;

public class NewsListFragment extends Fragment implements INewsListContract.IView,
        NewsPagingAdapter.OnItemClickedListener,
        NewsLoadStateAdapter.OnRetryClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private final String KEY_SCROLL_STATE = "RecyclerView State";
    private final String KEY_URL_NEWS = "Url news";
    private FragmentNewsListBinding binding;
    private final INewsListContract.IPresenter listPresenter = new NewsListPresenter();

    private NewsPagingAdapter pagingAdapter;

    public static NewsListFragment newInstance() {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsListBinding.inflate(inflater);

        listPresenter.attachView(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        if (savedInstanceState != null) {
            Parcelable state = savedInstanceState.getParcelable(KEY_SCROLL_STATE);
            layoutManager.onRestoreInstanceState(state);
        }
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(layoutManager);

        pagingAdapter = new NewsPagingAdapter(new ArticleComparator());
        pagingAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        binding.recyclerView.setAdapter(pagingAdapter.withLoadStateHeaderAndFooter(new NewsLoadStateAdapter(this),
                new NewsLoadStateAdapter(this)));

        if (InternetConnection.checkConnection(requireContext())) {
            listPresenter.downloadNewsList();
        }
        pagingAdapter.setListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);

        return binding.getRoot();
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
    public void retryClicked() {
        pagingAdapter.retry();
    }

    @Override
    public void showLoading(boolean showProgress) {
        binding.swipeRefresh.setRefreshing(showProgress);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void subscribeNews(Flowable<PagingData<Article>> articlePagingDataFlowable) {

        articlePagingDataFlowable
                .subscribe(new Consumer<PagingData<Article>>() {
                    @Override
                    public void accept(PagingData<Article> articlePagingData) {
                        pagingAdapter.submitData(NewsListFragment.this.getLifecycle(), articlePagingData);
                    }
                });
    }

    @Override
    public void onRefresh() {
        listPresenter.downloadNewsList();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_SCROLL_STATE, binding.recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onDestroyView() {
        binding = null;
        listPresenter.detachView();
        pagingAdapter = null;
        super.onDestroyView();
    }
}
