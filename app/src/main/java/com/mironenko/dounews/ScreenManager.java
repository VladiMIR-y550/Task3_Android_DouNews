package com.mironenko.dounews;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.mironenko.dounews.view.IScreenManager;

public class ScreenManager extends FragmentActivity implements IScreenManager {

    private final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public void changeScreen(Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }
}
