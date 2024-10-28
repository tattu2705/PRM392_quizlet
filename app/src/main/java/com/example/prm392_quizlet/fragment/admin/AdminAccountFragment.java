package com.example.projectprn392flashcard.fragment.admin;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.activity.LoginActivity;
import com.example.projectprn392flashcard.activity.user.ChangePasswordActivity;
import com.example.projectprn392flashcard.activity.user.EditProfileActivity;
import com.example.projectprn392flashcard.data.dao.UserDAO;
import com.example.projectprn392flashcard.data.entity.User;

public class AdminAccountFragment extends Fragment {

    private static final int REQUEST_CODE_EDIT_PROFILE = 1; // Request code for startActivityForResult

    private TextView txtUsername, txtName, txtEmail, txtPhone;
    private Button btnEditProfile, btnChangePassword, btnLogout;

    private UserDAO userDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI components
        initializeViews(view);

        // Initialize UserDAO
        userDatabase = new UserDAO(getActivity());

        // Load user info from SharedPreferences and database
        UserInfoLoader userInfoLoader = new UserInfoLoader(userDatabase, getActivity());
        userInfoLoader.loadUserInfo(txtUsername, txtName, txtEmail, txtPhone);

        // Setup event listeners for buttons
        setupEventListeners();
    }

    private void initializeViews(@NonNull View view) {
        txtUsername = view.findViewById(R.id.usernameTextView);
        txtName = view.findViewById(R.id.name);
        txtEmail = view.findViewById(R.id.emailTextView);
        txtPhone = view.findViewById(R.id.phone);
        btnEditProfile = view.findViewById(R.id.editProfileButton);
        btnChangePassword = view.findViewById(R.id.editChangePasswordButton);
        btnLogout = view.findViewById(R.id.logoutButton);
    }

    private void setupEventListeners() {
        btnEditProfile.setOnClickListener(v -> {
            // Open EditProfileActivity for result
            Intent intent = new Intent(getActivity(), EditProfileActivity.class);
            startActivityForResult(intent, REQUEST_CODE_EDIT_PROFILE);
        });

        btnChangePassword.setOnClickListener(v -> {
            // Open ChangePasswordActivity for result
            Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
            startActivityForResult(intent, REQUEST_CODE_EDIT_PROFILE);
        });

        btnLogout.setOnClickListener(v -> {
            // Clear login state and redirect to LoginActivity
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginState", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EDIT_PROFILE && resultCode == RESULT_OK) {
            // Refresh user info when coming back from EditProfileActivity
            UserInfoLoader userInfoLoader = new UserInfoLoader(userDatabase, getActivity());
            userInfoLoader.loadUserInfo(txtUsername, txtName, txtEmail, txtPhone);
        }
    }
}

class UserInfoLoader {

    private UserDAO userDatabase;
    private Context context;

    public UserInfoLoader(UserDAO userDatabase, Context context) {
        this.userDatabase = userDatabase;
        this.context = context;
    }

    public void loadUserInfo(TextView txtUsername, TextView txtName, TextView txtEmail, TextView txtPhone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginState", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        User user = userDatabase.getUserById(userId);

        setUserInfo(user, txtUsername, txtName, txtEmail, txtPhone);
    }

    private void setUserInfo(User user, TextView txtUsername, TextView txtName, TextView txtEmail, TextView txtPhone) {
        if (user != null) {
            txtUsername.setText("Username: " + user.getUsername());
            txtName.setText("Name: " + user.getName());
            txtEmail.setText("Email: " + user.getEmail());
            txtPhone.setText("Phone: " + user.getPhone());
        }
    }
}
