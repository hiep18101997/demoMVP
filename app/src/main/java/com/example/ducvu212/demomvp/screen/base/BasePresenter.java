package com.example.ducvu212.demomvp.screen.base;

/**
 * Created by CuD HniM on 18/08/07.
 */
public interface BasePresenter<T> {

    void setView(T view);

    void onStart();

    void onStop();
}