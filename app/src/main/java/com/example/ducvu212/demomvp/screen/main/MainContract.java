package com.example.ducvu212.demomvp.screen.main;

import com.example.ducvu212.demomvp.screen.base.BasePresenter;

/**
 * Created by CuD HniM on 18/08/07.
 */
public interface MainContract {

    /**
     * Interface View
     */

    interface View {
        void initFragment();
    }

    /**
     * Interface Presenter
     */

    interface Presenter extends BasePresenter<View> {

    }
}
