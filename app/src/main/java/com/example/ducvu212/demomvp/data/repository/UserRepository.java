package com.example.ducvu212.demomvp.data.repository;

import android.annotation.SuppressLint;
import com.example.ducvu212.demomvp.data.source.UserDataSource;
import com.example.ducvu212.demomvp.data.source.remote.UserRemoteDataSource;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by CuD HniM on 18/08/07.
 */
public class UserRepository {

    private static UserRepository mInstance;
    private UserDataSource mDataSource;

    @SuppressLint("RestrictedApi")
    public UserRepository(UserDataSource userDataSource) {
        mDataSource = checkNotNull(userDataSource);
    }

    public static synchronized UserRepository getmInstance(UserDataSource userDataSource){
        if (mInstance == null){
            mInstance = new UserRepository(userDataSource);
        }
        return mInstance;
    }

    public void getUsers(String name, int limit, UserRemoteDataSource.onNetworkChange networkChange){
        mDataSource.getDataFromGithub(name, limit, networkChange);
    }

}
