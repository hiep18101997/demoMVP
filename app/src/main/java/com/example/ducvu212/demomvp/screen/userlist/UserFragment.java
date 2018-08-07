package com.example.ducvu212.demomvp.screen.userlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ducvu212.demomvp.R;
import com.example.ducvu212.demomvp.data.model.User;
import com.example.ducvu212.demomvp.utils.DisplayUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements UserContract.View,
        SearchView.OnQueryTextListener {

    private UserPresenter mPresenter;
    private RecyclerView mRecyclerViewUser;
    private SearchView mSearchView;
    private List<User> mUsers;
    private UserAdapter mUserAdapter;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        mPresenter = new UserPresenter();
        mPresenter.setView(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initCoponents();
    }

    private void initCoponents() {
        mUsers = new ArrayList<>();
        initSearchView();
    }

    private void initViews() {
        mSearchView = getActivity().findViewById(R.id.search_view);
        mRecyclerViewUser = getActivity().findViewById(R.id.recycle_user);
    }

    private void initSearchView() {
        SearchView.SearchAutoComplete searchAutoComplete =
                mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        mSearchView.setIconified(false);
        mSearchView.setIconifiedByDefault(false);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.hint_text_color));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.text_search_color));
        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (!s.isEmpty()) {
            mPresenter.searchUser(s);
        }
        return false;
    }

    @Override
    public void loadUserSuccess(List<User> users) {
        DisplayUtils.makeText(getContext(), getResources().getString(R.string.onSuccess));
        setupRecycleview(users);
    }

    private void setupRecycleview(List<User> users) {
        if (mUsers != null && mUserAdapter != null) {
            mUsers.clear();
            mUsers.addAll(users);
            mUserAdapter.notifyDataSetChanged();
        } else {
            mUsers = users;
            mUserAdapter = new UserAdapter(mUsers);
            mRecyclerViewUser.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerViewUser.setAdapter(mUserAdapter);
        }
    }

    @Override
    public void onNetworkError() {
        DisplayUtils.makeText(getContext(), getResources().getString(R.string.onNetworkError));
    }

    @Override
    public void loadUserError() {
        DisplayUtils.makeText(getContext(), getResources().getString(R.string.onEror));
    }
}
