package com.mironenko.dounews;

import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScreenChangedAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public ScreenChangedAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();

    }

    public void addListFragments (Fragment fragment) {
        this.fragmentList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
