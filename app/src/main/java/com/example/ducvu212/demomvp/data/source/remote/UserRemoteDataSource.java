package com.example.ducvu212.demomvp.data.source.remote;

import android.os.AsyncTask;
import com.example.ducvu212.demomvp.data.model.GitRespone;
import com.example.ducvu212.demomvp.data.model.User;
import com.example.ducvu212.demomvp.data.source.UserDataSource;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/06.
 */
public class UserRemoteDataSource extends UserDataSource {

    private static final String BASE_URL =
            "https://api.github.com/search/users?per_page=number&q=name";
    private static final String QUERY_TEXT = "name";
    private static final String NUMBER_PER_PAGE = "number";
    private static final String REQUEST_TYPE = "GET";
    private static final int READ_TIMEOUT = 20000;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final String LAST_CHARACTER = "\n";
    private static UserRemoteDataSource mInstance;

    public static synchronized UserRemoteDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new UserRemoteDataSource();
        }
        return mInstance;
    }

    @Override
    public void getDataFromGithub(String name, int limit, onNetworkChange onNetworkChange) {
        String url =
                BASE_URL.replace(QUERY_TEXT, name).replace(NUMBER_PER_PAGE, String.valueOf(limit));
        new GetUserFromGit(onNetworkChange).execute(url);
    }

    private class GetUserFromGit extends AsyncTask<String, Void, List<User>> {

        private onNetworkChange mNetworkChange;

        public GetUserFromGit(onNetworkChange networkChange) {
            mNetworkChange = networkChange;
        }

        @Override
        protected List<User> doInBackground(String... strings) {
            String url = strings[0];
            GitRespone data = null;
            try {
                data = new Gson().fromJson(getJsonFromUrl(url), GitRespone.class);
            } catch (IOException e) {
                e.printStackTrace();
                mNetworkChange.onError();
            }
            return data.getItems();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            mNetworkChange.onSuccess(users);
        }
    }

    private String getJsonFromUrl(String json) throws IOException {
        HttpURLConnection urlConnection = null;

        URL url = null;
        String result = null;
        url = new URL(json);
        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod(REQUEST_TYPE);
        urlConnection.setReadTimeout(READ_TIMEOUT);
        urlConnection.setConnectTimeout(CONNECT_TIMEOUT);

        urlConnection.setDoOutput(true);
        urlConnection.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append(LAST_CHARACTER);
        }
        br.close();

        result = sb.toString();
        urlConnection.disconnect();

        return result;
    }
}
