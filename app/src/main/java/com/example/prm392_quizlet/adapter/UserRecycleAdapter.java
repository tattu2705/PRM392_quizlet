package com.example.projectprn392flashcard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.activity.user.UserDetailActivity;
import com.example.projectprn392flashcard.data.entity.User;

import java.util.List;

public class UserRecycleAdapter extends RecyclerView.Adapter<UserRecycleAdapter.ViewHolder> {

    private List<User> list;
    private final Context context;

    public UserRecycleAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<User> list) {
        this.list = list;
        notifyDataSetChanged(); // Thông báo cho adapter làm mới dữ liệu
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = list.get(position);
        holder.userName.setText(user.getUsername());
        holder.userEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView userName;
        private final TextView userEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("user", list.get(getAdapterPosition()));
                context.startActivity(intent);
            });
        }
    }
}
