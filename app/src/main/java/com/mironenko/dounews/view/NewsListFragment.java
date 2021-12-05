package com.mironenko.dounews.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mironenko.dounews.R;
import com.mironenko.dounews.databinding.FragmentNewsListBinding;
import com.mironenko.dounews.model.InternetConnection;
import com.mironenko.dounews.presenter.NewsListPresenter;
import com.mironenko.dounews.view.adapters.ArticlesListAdapter;

public class NewsListFragment extends Fragment implements IListFragment {

    private final int RESPONSE_SUCCESSFUL = 0;

    private FragmentNewsListBinding binding;
    private NewsListPresenter listPresenter;

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {

            if (message.what == RESPONSE_SUCCESSFUL) {
                ArticlesListAdapter adapter = new ArticlesListAdapter(listPresenter);
                binding.recyclerView.setAdapter(adapter);
            }
            return false;
        }
    });


    public static NewsListFragment newInstance() {

        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        binding = FragmentNewsListBinding.inflate(inflater);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        if (getContext() != null) {
            boolean internetConnection = InternetConnection.checkConnection(getContext());
            listPresenter = new NewsListPresenter(this, internetConnection);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = binding.getRoot();

        listPresenter.fetchNewsList();

        return view;
    }

    @Override
    public void uploadArticle(String url) {

        DetailedNewsFragment detailedNewsFragment = DetailedNewsFragment.newInstance(url);
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, detailedNewsFragment)
                .commit();
    }

    @Override
    public void showNewsList() {
        handler.sendEmptyMessage(RESPONSE_SUCCESSFUL);
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

        Toast.makeText(getContext(), "Sorry something went wrong", Toast.LENGTH_SHORT).show();
    }
}
