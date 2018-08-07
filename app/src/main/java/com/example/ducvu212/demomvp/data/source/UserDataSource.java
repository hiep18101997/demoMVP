package com.example.ducvu212.demomvp.data.source;

import com.example.ducvu212.demomvp.data.model.User;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/06.
 */
public abstract class UserDataSource {

    /**
     * Remote data source
     */

    public interface onNetworkChange {
        boolean onSuccess(List<User> users);

        boolean onError();

        boolean onNetWorkError();
    }

    public abstract void getDataFromGithub(String name, int limit, onNetworkChange onNetworkChange);
}
