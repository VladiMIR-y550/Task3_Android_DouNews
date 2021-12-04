package com.mironenko.dounews.mk_version;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.mironenko.dounews.databinding.FragmentArticlesListBinding;
import com.mironenko.dounews.model.remote.ArticleResult;

import java.util.ArrayList;

public class ArticlesListFragment extends Fragment {

    private FragmentArticlesListBinding binding;
    private ArticlesAdapter adapter;
    private ArrayList<ArticleResult> articlesList;
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        binding = FragmentArticlesListBinding.inflate(inflater);

        articlesList = new ArrayList<>();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = binding.getRoot();
        return view;
    }
}
