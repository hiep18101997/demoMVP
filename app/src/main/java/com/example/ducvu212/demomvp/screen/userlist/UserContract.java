package com.example.ducvu212.demomvp.screen.userlist;

import com.example.ducvu212.demomvp.data.model.User;
import com.example.ducvu212.demomvp.screen.base.BasePresenter;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/07.
 */
public interface UserContract {

    /**
     * Interface View
     */

    interface View {
        void loadUserSuccess(List<User> users);

        void onNetworkError();

        void loadUserError();
    }

    /**
     * Interface Presenter
     */

    interface Presenter extends BasePresenter<View> {
        void searchUser(String text);
    }
}
