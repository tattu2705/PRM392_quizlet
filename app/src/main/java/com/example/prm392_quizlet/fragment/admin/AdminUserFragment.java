package com.example.projectprn392flashcard.fragment.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.activity.user.AccountInfoActivity;
import com.example.projectprn392flashcard.adapter.UserRecycleAdapter;
import com.example.projectprn392flashcard.data.dao.UserDAO;
import com.example.projectprn392flashcard.data.entity.User;

import java.util.List;

public class AdminUserFragment extends Fragment {

    private EditText etSearchUser;
    private Button btnSearch, btnCreateUser;
    private RecyclerView rvUserList;
    private UserDAO userDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupUserDAO();
        setupRecyclerView();
        setupListeners();
    }

    private void initializeViews(View view) {
        rvUserList = view.findViewById(R.id.search_results);
        etSearchUser = view.findViewById(R.id.search_user);
        btnSearch = view.findViewById(R.id.search_button);
        btnCreateUser = view.findViewById(R.id.btn_create_user);
    }

    private void setupUserDAO() {
        userDAO = new UserDAO(getContext());
    }

    private void setupRecyclerView() {
        rvUserList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvUserList.setAdapter(new UserRecycleAdapter(getContext(), null)); // Khởi tạo adapter rỗng
    }

    private void setupListeners() {
        btnSearch.setOnClickListener(v -> {
            String query = etSearchUser.getText().toString();
            List<User> userList = userDAO.searchUser(query);
            updateRecyclerView(userList);
        });

        btnCreateUser.setOnClickListener(v -> {

             Intent intent = new Intent(getContext(), AccountInfoActivity.class);
             startActivity(intent);
        });
    }

    private void updateRecyclerView(List<User> userList) {
        UserRecycleAdapter adapter = (UserRecycleAdapter) rvUserList.getAdapter();
        if (adapter != null) {
            adapter.setList(userList);
        } else {
            adapter = new UserRecycleAdapter(getContext(), userList);
            rvUserList.setAdapter(adapter);
        }
    }
}
