package com.example.ducvu212.demomvp.screen.userlist;

import com.example.ducvu212.demomvp.data.model.User;
import com.example.ducvu212.demomvp.data.repository.UserRepository;
import com.example.ducvu212.demomvp.data.source.UserDataSource;
import com.example.ducvu212.demomvp.data.source.remote.UserRemoteDataSource;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/07.
 */
public class UserPresenter implements UserContract.Presenter {

    private static final int LIMIT_QUERY = 100;
    private UserContract.View mView;
    private UserRepository mRepository;

    @Override
    public void setView(UserContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
        mRepository = UserRepository.getmInstance(UserRemoteDataSource.getInstance());
    }

    @Override
    public void onStop() {

    }

    @Override
    public void searchUser(String text) {
        mRepository.getUsers(text, LIMIT_QUERY, new UserDataSource.onNetworkChange() {
            @Override
            public boolean onSuccess(List<User> users) {
                mView.loadUserSuccess(users);
                return false;
            }

            @Override
            public boolean onError() {
                mView.loadUserError();
                return false;
            }

            @Override
            public boolean onNetWorkError() {
                mView.onNetworkError();
                return false;
            }
        });
    }
}
