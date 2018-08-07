package com.example.ducvu212.demomvp.screen.main;

import android.annotation.SuppressLint;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by CuD HniM on 18/08/07.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    @SuppressLint("RestrictedApi")
    @Override
    public void setView(MainContract.View view) {
        mView = checkNotNull(view);
    }

    @Override
    public void onStart() {
        mView.initFragment();
    }

    @Override
    public void onStop() {

    }
}
