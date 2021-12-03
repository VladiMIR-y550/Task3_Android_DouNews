package com.mironenko.dounews.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mironenko.dounews.R;
import com.mironenko.dounews.databinding.FragmentNewsListBinding;
import com.mironenko.dounews.model.remote.ArticlesListItems;
import com.mironenko.dounews.presenter.NewsListPresenter;

public class NewsListFragment extends Fragment implements IListFragment, View.OnClickListener {

    private FragmentNewsListBinding binding;
    private NewsListPresenter listPresenter;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            ArticlesListItems articleItems = (ArticlesListItems) message.obj;
            binding.item1.tvTitle.setText(articleItems.getTitle());
            binding.item1.tvShortText.setText(articleItems.getAuthor_name());

            return false;
        }
    });

    public static Fragment newInstance() {
        Bundle args = new Bundle();

        /**
         * TODO put in Bundle
         */

        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        binding = FragmentNewsListBinding.inflate(inflater);

        listPresenter = new NewsListPresenter(handler, this);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = binding.getRoot();
        binding.item1.getRoot().setOnClickListener(this);
        binding.item2.getRoot().setOnClickListener(this);
        binding.item3.getRoot().setOnClickListener(this);

        listPresenter.fetchNewsList();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_1:
            case R.id.item_3:
            case R.id.item_2:
                listPresenter.chooseNews();
                break;
        }
    }

    @Override
    public void showDetail() {
        DetailedNewsFragment detailedNewsFragment = DetailedNewsFragment.newInstance();
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, detailedNewsFragment)
                .commit();
    }

    @Override
    public void showLoading() {
/**
 * TODO показать индикатор загрпузки после выбора новости
 */
    }

    @Override
    public void hideLoading() {
/**
 * TODO скрыть индикатор загрпузки после обнаружения новости перед показом деталей
 */
    }
}
