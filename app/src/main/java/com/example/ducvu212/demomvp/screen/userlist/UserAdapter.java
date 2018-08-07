package com.example.ducvu212.demomvp.screen.userlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ducvu212.demomvp.R;
import com.example.ducvu212.demomvp.data.model.User;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/07.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> mUsers;

    public UserAdapter(List<User> users) {
        mUsers = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.bindData(mUsers.get(i));
    }

    @Override
    public int getItemCount() {
        return mUsers == null ? 0 : mUsers.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageViewAvar;
        private TextView mTextViewName;
        private TextView mTextViewId;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewAvar = itemView.findViewById(R.id.imageViewAvatar);
            mTextViewName = itemView.findViewById(R.id.textViewName);
            mTextViewId = itemView.findViewById(R.id.textViewId);
        }

        void bindData(User user) {
            Picasso.get()
                    .load(user.getAvatarUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mImageViewAvar);
            mTextViewId.setText(String.valueOf(user.getId()));
            mTextViewName.setText(user.getLogin());
        }
    }
}
