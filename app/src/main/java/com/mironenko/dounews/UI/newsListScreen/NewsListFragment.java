package com.mironenko.dounews.UI.newsListScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mironenko.dounews.R;
import com.mironenko.dounews.UI.newsDetailedScreen.NewsDetailedFragment;
import com.mironenko.dounews.UI.newsListScreen.adapter.PagingAdapter;
import com.mironenko.dounews.databinding.FragmentNewsListBinding;
import com.mironenko.dounews.model.api.Article;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class NewsListFragment extends Fragment implements INewsListContract.IView,
        SwipeRefreshLayout.OnRefreshListener,
        PagingAdapter.PagingUpdateListener {

    private static final String KEY_SCROLL_STATE = "Key scroll view";
    private final String KEY_URL_NEWS = "Url news";
    private FragmentNewsListBinding binding;
    private final INewsListContract.IPresenter listPresenter = NewsListPresenter.getInstance();
    private PagingAdapter pagingAdapter;
    private LinearLayoutManager layoutManager;

    public static NewsListFragment newInstance() {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("RxLifeCycle", "onCreate " + savedInstanceState);
        super.onCreate(savedInstanceState);
        pagingAdapter = new PagingAdapter(this);

        if (savedInstanceState == null) {
            if (listPresenter.getNews() == 0) {
                listPresenter.downloadArticles();
            }
        }
        listPresenter.getAllBase(Article.class)
                .subscribe(new Observer<List<Article>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Article> articleList) {
                        pagingAdapter.setArticleList(articleList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        pagingAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("RxLifeCycle", "onCreateView");
        binding = FragmentNewsListBinding.inflate(inflater, container, false);

        listPresenter.attachView(this);
        layoutManager = new LinearLayoutManager(requireContext());

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(pagingAdapter);


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
    public void updateAdapter() {
        pagingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        listPresenter.refreshNews();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        listPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void onUpdate() {
        listPresenter.downloadArticles();
    }

    @Override
    public void onItemClickListener(String urlArticle) {
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
}
